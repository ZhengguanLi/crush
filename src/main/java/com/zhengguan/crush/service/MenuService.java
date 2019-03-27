package com.zhengguan.crush.service;

import com.zhengguan.crush.entity.Category;
import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {
    public List<Category> getCategories();

    public List<Meal> getMeals(String category);

    public List<Extra> getExtras(String name);

    public Meal findById(int id);

    public Meal saveMeal(Meal meal);

    public void update(Meal meal);

    public void deleteById(int id);

    public List<Meal> findAll();
}