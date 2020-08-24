package karol.spring.webapp.controllers;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.commands.UserCommand;
import karol.spring.webapp.converters.UserToUserCommand;
import karol.spring.webapp.models.User;
import karol.spring.webapp.repositories.UserRepository;
import karol.spring.webapp.services.RoleService;
import karol.spring.webapp.services.SecurityService;
import karol.spring.webapp.services.UserService;
import karol.spring.webapp.validators.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserToUserCommand userToUserCommand;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, UserRepository userRepository, RoleService roleService, UserToUserCommand userToUserCommand) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userToUserCommand = userToUserCommand;
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

        String loggedUserUsername = securityService.getUsernameOfLoggedUser();
        Collection loggedUserAuthorities = securityService.getLoggerUser();

        if(loggedUserAuthorities.stream().findFirst().get().toString().equals("ADMIN")){ //sprwdzanie czy zalogowany uzytkownik ma prawa admina, jesli tak moze edytowac innych
            model.addAttribute("user", userService.findById(id));
            return "user/editUserName";
        }
        else {
            if (loggedUserUsername.equals(userService.findById(id).getUsername())) {
                model.addAttribute("user", userService.findById(id));
                return "user/editUserName";
            } else {

                User userSaved = userService.findByUsername(securityService.getUsernameOfLoggedUser());

                model.addAttribute("user", userSaved);
                System.out.println("NIE MOZNA EDYTOWC INNCH"); //todo trzeba to zrobic inaczej
                return "redirect:/user/" + userSaved.getId() + "/details/edit/username";
            }
        }
    }

    @PostMapping("/user/{id}/details/edit/username")
    public String processEditUsername(@PathVariable Long id, @ModelAttribute User user, BindingResult result, Model model){


        if(result.hasErrors()){
            System.out.println("Problem during updating username");
            return "redirect:/user/editUserName";
        }
        Collection loggedUserAuthorities = securityService.getLoggerUser();

        User savedUser = userService.findById(id);
        savedUser.setUsername(user.getUsername());
        userService.saveUserAfterChangeUsername(savedUser);

        if(loggedUserAuthorities.stream().findFirst().get().toString().equals("ADMIN")){
            return "redirect:/users";
        }
        else {
            return "redirect:/logout";
        }
    }

    @GetMapping("/users")
    public String getUsersList(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "user/showUsersList";
    }
    @GetMapping("/user/{id}/detail")
    public String getUsersDetailsForAdmin(@PathVariable Long id, Model model){

        model.addAttribute("user", userService.findById(id));

        return "user/userDetails";
    }

    @GetMapping("/user/{id}/details/edit/role")
    public String getEditUserRole(Model model, @PathVariable Long id){

        model.addAttribute("user", userToUserCommand.convert(userService.findById(id)));
        model.addAttribute("roles", roleService.getAllRoles());

        return "user/editRole";
    }

    @PostMapping("/user/{id}/details/edit/role")
    public String processEditUserRole(@Validated UserCommand user, Model model, @PathVariable Long id,  BindingResult result){

        user.setUsername(userService.findById(id).getUsername());
        user.setPassword(userService.findById(id).getPassword());

        if(result.hasErrors()){
            System.out.println("Problem during updating user role");
            return "redirect:/user/" + id  + "/details/edit/role";
        }else{
            userService.saveUserCommand(user);
            return "redirect:/user/" + id + "/detail";
        }
    }
}
