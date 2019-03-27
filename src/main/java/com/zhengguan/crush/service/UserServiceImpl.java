package com.zhengguan.crush.service;

import com.zhengguan.crush.dao.RoleDao;
import com.zhengguan.crush.dao.UserDao;
import com.zhengguan.crush.entity.User;
import com.zhengguan.crush.user.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public User saveUser(CrmUser theCrmUser) {
        User user = new User();
        user.setUsername(theCrmUser.getUsername());
        user.setPassword(passwordEncoder.encode(theCrmUser.getPassword()));
        user.setEmail(theCrmUser.getEmail());
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        User theUser = userDao.findById(user.getId());
        theUser.setUsername(user.getUsername());
        theUser.setPassword(user.getPassword());
        theUser.setEmail(user.getEmail());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        User user = userDao.findById(id);
        userDao.delete(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }
}
