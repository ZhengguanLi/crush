package com.zhengguan.crush.controller;

import com.zhengguan.crush.entity.Category;
import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import com.zhengguan.crush.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    private String getMenuList(Model model) {
        Map<String, List<Meal>> menuMealsMap = new LinkedHashMap<>();
        Map<Meal, List<Extra>> mealExtrasMap = new LinkedHashMap<>();

        // get categories, for each category, get the meals/extras
        List<Category> categories = menuService.getCategories();
        for (Category category : categories) {
            String name = category.getName();
            List<Meal> meals = menuService.getMeals(name);
            menuMealsMap.put(name, meals);

            if (category.getName().equals("Beverages")) {
                mealExtrasMap.put(new Meal(0, "2 Liter Sodas", 4.00, "Beverages"), menuService.getExtras("2 Liter Sodas"));
                mealExtrasMap.put(new Meal(0, "Drinks", 2.75, "Beverages"), menuService.getExtras("Drinks"));
            } else {
                List<Extra> extras = menuService.getExtras(name);
                addToMealExtrasMap(meals, extras, mealExtrasMap);
            }
        }

        model.addAttribute("menuMealsMap", menuMealsMap);
        model.addAttribute("mealExtrasMap", mealExtrasMap);
        return "home";
    }

    private void addToMealExtrasMap(List<Meal> meals, List<Extra> extras, Map<Meal, List<Extra>> mealExtrasMap) {
        for (Meal meal : meals) {
            mealExtrasMap.put(meal, extras);
        }
    }

    @GetMapping("/api")
    public String showAPIPage() {
        return "api";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
