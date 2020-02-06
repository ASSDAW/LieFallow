package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    UserDao userDao;

    @Override
    public String toCheckLogin(String userId, String userPassword, HttpServletRequest request) {
        if (userDao.check(userId,userPassword) == 1){
            request.getSession().setAttribute("user", userDao.findById(userId));
            return "redirect:index?tag=recent&page=1";
        }else
            request.setAttribute("msg","用户名或密码错误，请重新输入！");
        return "login";
    }

    @Override
    public String toCheckRegister(User user, BindingResult bindingResult, HttpServletRequest request) {
        List msgs = new ArrayList();
        String msg = null;
        User userMapperById = userDao.findById(user.getUserId());
        if (userMapperById != null){
            msg = "账号已存在";
            msgs.add(msg);
        }
        if (bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError objectError:errors) {
                msg = objectError.getDefaultMessage();
                msgs.add(msg);
            }
        }
        if (msgs.size() != 0){
            request.setAttribute("msgs",msgs);
            return "register";
        }else{
            user.setUserPhoto("/static/uploadFile/girl.jpg");
            userDao.insert(user);
            request.getSession().setAttribute("user",user);
            return "redirect:index?tag=recent&page=1";
        }
    }
}
