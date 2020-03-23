package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserSerivice {
    void insert(User user);
    User findById(String userId);
    List<User> findAll();
    void update(User user);
    void lock(String userId);
    void unlock(String userId);
}
