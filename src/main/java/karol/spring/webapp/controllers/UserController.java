package karol.spring.webapp.controllers;

import karol.spring.webapp.models.User;
import karol.spring.webapp.services.SecurityService;
import karol.spring.webapp.services.UserService;
import karol.spring.webapp.validators.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model){
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String processRegistrationnForm(@Validated  @ModelAttribute("userForm") User userForm, BindingResult result){
        userValidator.validate(userForm, result);

        if(result.hasErrors()){
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());


        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/user/details")
    public String getUserDetails(Model model){
        User user = userService.findByUsername(securityService.getUsernameOfLoggedUser());

        model.addAttribute("user", user);
        return "user/userDetails";
    }

}
