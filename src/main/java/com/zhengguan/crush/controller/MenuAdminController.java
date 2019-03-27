package com.zhengguan.crush.controller;

import com.zhengguan.crush.entity.Meal;
import com.zhengguan.crush.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class MenuAdminController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public String listMenu(Model model) {
        List<Meal> meals = menuService.findAll();
        model.addAttribute("meals", meals);
        return "menu/list-menu";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Meal meal = new Meal();
        model.addAttribute("meal", meal);
        return "menu/meal-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("mealId") int id, Model model) {
        Meal meal = menuService.findById(id);
        model.addAttribute("meal", meal);
        return "menu/meal-form";
    }

    @PostMapping("/save")
    public String saveMeal(@ModelAttribute("meal") Meal meal, BindingResult theResult) {
        menuService.saveMeal(meal);
        return "redirect:/admin/menu/list";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("meal") Meal meal, BindingResult theResult) {
        menuService.update(meal);
        return "redirect:/admin/menu/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int id, Model model) {
        menuService.deleteById(id);
        return "redirect:/admin/menu/list";
    }

}
