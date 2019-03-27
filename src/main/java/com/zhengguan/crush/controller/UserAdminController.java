package com.zhengguan.crush.controller;

import com.zhengguan.crush.entity.User;
import com.zhengguan.crush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, BindingResult theResult) {
//        user.setRoles((Collection<Role>) new Role(Arrays.asList(user)));
        userService.saveUser(user);
        return "redirect:/admin/users/list";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user, BindingResult theResult) {
        userService.update(user);
        return "redirect:/admin/users/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int id, Model model) {
        userService.deleteById(id);
        return "redirect:/admin/users/list";
    }
}