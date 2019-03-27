package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public User findById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where username=:username", User.class);
        theQuery.setParameter("username", username);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where email=:email", User.class);
        theQuery.setParameter("email", email);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public User save(User user) {
        User theUser = entityManager.merge(user);
        return theUser;
    }

    public void update(User user) {
        entityManager.refresh(user);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);
        List<User> users = null;
        try {
            users = theQuery.getResultList();
        } catch (Exception e) {
            users = null;
        }
        return users;
    }
}
