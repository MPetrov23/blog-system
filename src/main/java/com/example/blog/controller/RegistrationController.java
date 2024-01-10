package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.EmailService;
import com.example.blog.service.UserServiceImpl;
import com.example.blog.validation.DataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
        @Autowired
        UserServiceImpl userService;
        @Autowired
        EmailService email;

    @GetMapping("/goToRegistrationPage")
    public String registrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registrationPage";
    }
    @PostMapping("register")
    public String register(@ModelAttribute("user") User user, BindingResult result, Model model){
        User existingEmail = userService.findUserByEmail(user.getEmail());
        User existingUsername = userService.findUserByUsername(user.getUsername());

        if(user.getUsername() == null || user.getUsername().isEmpty()){
            result.rejectValue("username", "EMPTY",
                    "Username can't be empty!");
        }
        if(DataValidation.isValidUsername(user.getUsername())){
            result.rejectValue("username", "NOT_VALID",
                    "Username not valid!");
        }
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()) {
            result.rejectValue("username", "IN_USE",
                    "Username already in use!");
        }
        if(user.getFirstName() == null || user.getFirstName().isEmpty()){
            result.rejectValue("firstName", "EMPTY",
                    "First name can't be empty!");
        }
        if(DataValidation.isValidFirstName(user.getFirstName())){
            result.rejectValue("firstName", "NOT_VALID",
                    "First name not valid!");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()){
            result.rejectValue("lastName", "EMPTY",
                    "Last name can't be empty!");
        }
        if(DataValidation.isValidLastName(user.getLastName())){
            result.rejectValue("lastName", "NOT_VALID",
                    "Last name not valid!");
        }
        if(existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty()){
            result.rejectValue("email", "IN_USE",
                    "Email already in use!");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            result.rejectValue("email", "EMPTY",
                    "Email can't be empty!");
        }
        if(DataValidation.isValidEmail(user.getEmail())){
            result.rejectValue("email", "NOT_VALID",
                    "Email not valid!");
        }
        if(user.getPassword() ==null || user.getPassword().isEmpty()){
            result.rejectValue("password", "EMPTY",
                    "Password can't be empty!");
        }
        if(DataValidation.isValidPassword(user.getPassword())){
            result.rejectValue("password", "NOT_VALID",
                    "Password must contain at least 1 lower case, 1 upper case and 1 numeric character!\n" +
                                "Password must be 8 to 20 characters long!");
        }


        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registrationPage";
        }

        userService.createUser(user);
        email.confirmRegistrationMail(user.getEmail(),user.getFirstName()+" "+user.getLastName());
        return "redirect:/goToRegistrationPage?success";
    }
}
