package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserDao userDao;

    @PostMapping("/userAdmin")
    public String admin(HttpServletRequest request){
        List<User> users = userDao.findAll();
        request.setAttribute("users",users);
        return "admin/userAdmin";
    }
}
