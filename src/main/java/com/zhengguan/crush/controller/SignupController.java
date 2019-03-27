package com.zhengguan.crush.controller;

import com.zhengguan.crush.entity.User;
import com.zhengguan.crush.service.UserService;
import com.zhengguan.crush.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;

    @GetMapping("/accounts/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("theCrmUser", new CrmUser());
        return "accounts/signup";
    }

    @PostMapping("/accounts/signup")
    public String signup(@Valid @ModelAttribute(name = "theCrmUser") CrmUser theCrmUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("invalidCredential", "invalid data");
            return "accounts/signup";
        }

        // check if username is valid
        String username = theCrmUser.getUsername();
        User theUser = userService.findByUsername(username);
        if (theUser != null) {
            model.addAttribute("theCrmUser", new CrmUser());
            model.addAttribute("errorMessageForUsername", "Username cannot be used. Please choose another username.");
            return "accounts/signup";
        }

        // check if email is valid
        String email = theCrmUser.getEmail();
        theUser = userService.findByEmail(email);
        if (theUser != null) {
            model.addAttribute("theCrmUser", new CrmUser());
            model.addAttribute("errorMessageForEmail", "A user is already registered with this e-mail address.");
            return "accounts/signup";
        }

        userService.saveUser(theCrmUser);
        return "accounts/login";
    }
}