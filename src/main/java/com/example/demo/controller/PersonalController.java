package com.example.demo.controller;

import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dto.MyRemarkDTO;
import com.example.demo.dao.*;
import com.example.demo.pojo.User;
import com.example.demo.service.PersonalService;
import com.example.demo.util.ShiroEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonalController {
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    RemarkDao remarkDao;
    @Autowired
    PersonalService personalService;

    @PutMapping("/update")
    public String update(User user, HttpServletRequest request){
        String salt = userDao.findById(user.getUserId()).getSalt();
        user.setSalt(salt);
        user.setIsLock(0);
        userDao.update(user);
        request.getSession().setAttribute("user",user);
        return "personal";
    }

    @GetMapping("/personal")
    public String personal( HttpServletRequest request){
        return "personal";
    }

    @GetMapping("/myArticle")
    public String myArticle(HttpServletRequest request,@RequestParam Integer page){
       return personalService.toMyArticle(request,page);
    }

    @DeleteMapping("/deleteArticle")
    public String deleteArticle(@RequestParam Long articleId){
        articleDao.deleteArticle(articleId);
        articleDao.deleteArticleRemark(articleId);
        return "redirect:myArticle?page=1";
    }

    @GetMapping("/myRemark")
    public String myRemark(HttpServletRequest request,@RequestParam Integer page){
        return personalService.toMyRemark(request,page);
    }

    @DeleteMapping("/deleteRemark")
    public String deleteRemark(@RequestParam Long remarkId){
        Long articleId = remarkDao.findById(remarkId).getArticleId();
        remarkDao.delete(remarkId);
        remarkDao.updateRemarkNum(articleId);
        return "redirect:myRemark?page=1";
    }
}
