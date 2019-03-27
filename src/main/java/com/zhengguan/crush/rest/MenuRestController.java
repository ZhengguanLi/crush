package com.zhengguan.crush.rest;

import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import com.zhengguan.crush.service.ExtraService;
import com.zhengguan.crush.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuRestController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private ExtraService extraService;

    @GetMapping("/menu")
    public List<Meal> getMenu() {
        List<Meal> meals = menuService.findAll();
        return meals;
    }

    @GetMapping("/menu/{mealId}")
    public Meal getMeal(@PathVariable int mealId) {
        Meal meal = menuService.findById(mealId);
        return meal;
    }

    @GetMapping("/extras")
    public List<Extra> getExtra() {
        List<Extra> extras = extraService.findAll();
        return extras;
    }

    @GetMapping("/extras/{extraId}")
    public Extra getExtra(@PathVariable int extraId) {
        Extra extra = extraService.findById(extraId);
        return extra;
    }

    // add a new meal, return the updated meal object
    @PostMapping("/menu")
    public Meal saveMeal(@RequestBody Meal meal) {
        System.out.println("Yes");
        meal.setId(0); // means null, so insert as a new user
        Meal theMeal = menuService.saveMeal(meal);
        return theMeal;
    }

    // update user
    @PutMapping("")
    public void updateUser(@RequestBody Meal meal) {
        // check if th user id is valid
        Meal theMeal = menuService.findById(meal.getId());
        if (theMeal == null) {
            throw new UserNotFoundException("Meal not found");
        }

        menuService.update(meal);
    }

    @DeleteMapping("/menu/{mealId}")
    public String deleteMeal(@PathVariable int mealId) {
        menuService.deleteById(mealId);
        return "Delete meal with meal id - " + mealId;
    }
}
