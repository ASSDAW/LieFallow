package com.example.demo.service;

import com.example.demo.pojo.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public interface LoginService {
    public String toCheckLogin(User user, HttpSession session, Model model);
    public String toCheckRegister(User user, BindingResult bindingResult, HttpServletRequest request);
}
