package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.Category;
import com.zhengguan.crush.entity.Extra;
import com.zhengguan.crush.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MenuDaoImpl implements MenuDao {
    private EntityManager entityManager;

    @Autowired
    public MenuDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Category> getCategories() {
        TypedQuery<Category> theQuery = entityManager.createQuery("from Category ", Category.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Meal> getMeals(String category) {
        TypedQuery<Meal> theQuery = entityManager.createQuery("from Meal where category=:category", Meal.class);
        theQuery.setParameter("category", category);
        return theQuery.getResultList();
    }

    @Override
    public List<Extra> getExtras(String name) {
        TypedQuery<Extra> theQuery = entityManager.createQuery("from Extra where category=:name", Extra.class);
        theQuery.setParameter("name", name);
        return theQuery.getResultList();
    }

    @Override
    public Meal findById(int id) {
        return entityManager.find(Meal.class, id);
    }

    @Override
    public Meal save(Meal meal) {
        return entityManager.merge(meal);
    }

    @Override
    public void delete(Meal meal) {
        entityManager.remove(meal);
    }

    @Override
    public List<Meal> findAll() {
        TypedQuery<Meal> theQuery = entityManager.createQuery("from Meal", Meal.class);
        List<Meal> meals = null;
        try {
            meals = theQuery.getResultList();
        } catch (Exception e) {
            meals = null;
        }
        return meals;
    }
}
