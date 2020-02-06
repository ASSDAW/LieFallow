package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface LoginService {
    public String toCheckLogin(String userId,String userPassword, HttpServletRequest request);
    public String toCheckRegister(User user, BindingResult bindingResult, HttpServletRequest request);
}
