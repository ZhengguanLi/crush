package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.Category;
import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<Category> getCategories();

    public List<Meal> getMeals(String category);

    public List<Extra> getExtras(String name);

    public Meal findById(int id);

    public Meal save(Meal meal);

    public void delete(Meal meal);

    public List<Meal> findAll();
}
