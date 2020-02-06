package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:index?tag=recent&page=1";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam String userId,String userPassword, HttpServletRequest request){
       return loginService.toCheckLogin(userId,userPassword,request);
    }

    @PostMapping("/checkRegister")
    public String checkRegister(@Valid User user, BindingResult bindingResult, HttpServletRequest request){
        return loginService.toCheckRegister(user,bindingResult,request);
    }



}
