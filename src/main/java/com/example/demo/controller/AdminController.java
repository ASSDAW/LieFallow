package com.example.demo.controller;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.MessageDao;
import com.example.demo.dao.RemarkDao;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.Remark;
import com.example.demo.pojo.User;
import com.example.demo.service.UserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserSerivice userSerivice;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private RemarkDao remarkDao;
    @Autowired
    private MessageDao messageDao;

    @RequestMapping("/userMG")
    public String userMG(Model model){
        List<User> userList = userSerivice.findAll();
        model.addAttribute("userList",userList);

        model.addAttribute("tag","userMG");
        return "admin/userMG";
    }
    @RequestMapping("/articleMG")
    public String articleMG(Model model){
        List<Article> articleList = articleDao.adminFindAll();
        model.addAttribute("articleList",articleList);

        model.addAttribute("tag","articleMG");
        return "admin/articleMG";
    }

    @RequestMapping("/remarkMG")
    public String remarkMG(Model model){
        List<Remark> remarkList = remarkDao.adminFindAll();
        model.addAttribute("remarkList",remarkList);

        model.addAttribute("tag","remarkMG");
        return "admin/remarkMG";
    }
    @RequestMapping("/messageMG")
    public String messageMG(Model model){
        List<Message> messageList = messageDao.findAll();

        model.addAttribute("messageList",messageList);
        model.addAttribute("tag","messageMG");
        return "admin/messageMG";
    }


    @RequestMapping("/userUnlock")
    public String adminUnlock(String userId){
        userSerivice.unlock(userId);
        return "redirect:/admin/userMG";
    }
    @RequestMapping("/userLock")
    public String adminLock(String userId){
        userSerivice.lock(userId);
        return "redirect:/admin/userMG";
    }

    @RequestMapping("/articleLock")
    public String Articlelock(Long articleId){
        System.out.println(articleId);
        articleDao.adminDelete(articleId);
        return "redirect:/admin/articleMG";
    }
    @RequestMapping("/articleUnlock")
    public String ArticleUnlock(Long articleId){
        articleDao.adminUnDelete(articleId);
        return "redirect:/admin/articleMG";
    }
    @RequestMapping("/remarkLock")
    public String Remarklock(Long remarkId){
        remarkDao.adminLock(remarkId);
        return "redirect:/admin/remarkMG";
    }
    @RequestMapping("/remarkUnlock")
    public String RemarkUnlock(Long remarkId){
        remarkDao.adminUnLock(remarkId);
        return "redirect:/admin/remarkMG";
    }

    @RequestMapping("/messageLock")
    public String messageLock(Long messageId){
        messageDao.lock(messageId);
        return "redirect:/admin/messageMG";
    }
    @RequestMapping("/messageUnlock")
    public String messageUnlock(Long messageId){
        messageDao.unLock(messageId);
        return "redirect:/admin/messageMG";
    }
}
