package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.User;

import java.util.List;

public interface UserDao {
    public User findById(int id);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User save(User user);

    public void delete(User user);

    public List<User> findAll();
}
