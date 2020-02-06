package com.example.demo.controller;

import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dao.ArticleDetailDTODao;
import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.User;
import com.example.demo.service.OtherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OtherInformationController {
    @Autowired
    OtherInformationService otherInformationService;

    @GetMapping("/otherInformation/{userId}")
    public String otherInformation(@PathVariable String userId, HttpServletRequest request, @RequestParam Integer page){
        return otherInformationService.toOtherInformation(userId,request,page);
    }
}
