package com.zhengguan.crush.service;

import com.zhengguan.crush.dao.MenuDao;
import com.zhengguan.crush.entity.Category;
import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    @Transactional
    public List<Category> getCategories() {
        return menuDao.getCategories();
    }

    @Override
    @Transactional
    public List<Meal> getMeals(String category) {
        return menuDao.getMeals(category);
    }

    @Override
    @Transactional
    public List<Extra> getExtras(String name) {
        return menuDao.getExtras(name);
    }

    @Override
    public Meal findById(int id) {
        return menuDao.findById(id);
    }

    @Override
    @Transactional
    public Meal saveMeal(Meal meal) {
        return menuDao.save(meal);
    }

    @Override
    @Transactional
    public void update(Meal Meal) {
        Meal theMeal = menuDao.findById(Meal.getId());
        Meal.setName(theMeal.getName());
        Meal.setPrice(theMeal.getPrice());
        Meal.setCategory(theMeal.getCategory());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Meal Meal = menuDao.findById(id);
        menuDao.delete(Meal);
    }

    @Override
    @Transactional
    public List<Meal> findAll() {
        return menuDao.findAll();
    }
}