package karol.spring.webapp.controllers;

import karol.spring.webapp.models.User;
import karol.spring.webapp.repositories.UserRepository;
import karol.spring.webapp.services.SecurityService;
import karol.spring.webapp.services.UserService;
import karol.spring.webapp.validators.UserValidator;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    private final UserRepository userRepository;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, UserRepository userRepository) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.userRepository = userRepository;
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

    @GetMapping("/user/{id}/details/edit/username")
    public String getEditUsername(@PathVariable Long id, Model model, @ModelAttribute User user){

        if(securityService.getUsernameOfLoggedUser().equals(userService.findById(id).getUsername())) {
            model.addAttribute("user", userService.findById(id));
            return "user/editUserName";
        }
        else{

            User userSaved = userService.findByUsername(securityService.getUsernameOfLoggedUser());

            model.addAttribute("user", userSaved);
            System.out.println("NIE MOZNA EDYTOWC INNCH"); //todo trzeba to zrobic inaczej
            return "redirect:/user/" + userSaved.getId() + "/details/edit/username";
        }
    }

    @PostMapping("/user/{id}/details/edit/username")
    public String processEditUsername(@PathVariable Long id, @ModelAttribute User user, BindingResult result, Model model){


        if(result.hasErrors()){
            System.out.println("Problem during updating username");
            return "redirect:/user/editUserName";
        }

        User savedUser = userService.findById(id);
        savedUser.setUsername(user.getUsername());
        userService.saveUserAfterChangeUsername(savedUser);

        return "redirect:/logout";
    }

    @GetMapping("/users")
    public String getUsersList(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "user/showUsersList";
    }

}
