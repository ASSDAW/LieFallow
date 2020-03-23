package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.util.ShiroEncryption;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private UserSerivice userSerivice;

    @Override
    public String toCheckLogin(User user, HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserId(),user.getUserPassword());
        //执行登录方法
        try{
            //执行login方法会跳到UserReaml的AuthenticationInfo逻辑认证方法
            subject.login(token);
            User my = userSerivice.findById(user.getUserId());
            if (my.getIsLock()==1){
                model.addAttribute("msg", "账号已被锁，请联系客服解封！");
                return "login";
            }
            session.setAttribute("user",my);
            if (my.getIsAdmin()==1){
                return "redirect:admin/userMG";
            }
            return "redirect:index?tag=recent&page=1";
        }catch (Exception e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }

    @Override
    public String toCheckRegister(User user, BindingResult bindingResult, HttpServletRequest request) {
        List msgs = new ArrayList();
        String msg = null;
        User userMapperById = userSerivice.findById(user.getUserId());
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
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            String encodedPassword = ShiroEncryption.shiroEncryption(user.getUserPassword(),salt);
            user.setSalt(salt);
            user.setUserPassword(encodedPassword);
            user.setIsAdmin(0);
            userSerivice.insert(user);
            request.getSession().setAttribute("user",user);
            return "redirect:index?tag=recent&page=1";
        }
    }
}
