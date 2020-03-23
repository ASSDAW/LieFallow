package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSeriviceImpl implements UserSerivice {
    @Autowired
    private UserDao userDao;
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User findById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void update(User user) {
         userDao.update(user);
    }

    @Override
    public void lock(String userId) {
        userDao.lock(userId);
    }

    @Override
    public void unlock(String userId) {
        userDao.unlock(userId);
    }
}
