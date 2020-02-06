package com.example.demo.controller;

import com.example.demo.dto.MyCollectionDTO;
import com.example.demo.dao.CollectionDao;
import com.example.demo.pojo.User;
import com.example.demo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CollectionController {
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    CollectionService collectionService;

    @PostMapping("/addCollection")
    public String addCollection(@RequestParam Long collectionUserId,@RequestParam Long collectionArticleId){
        collectionDao.insertCollection(null,collectionUserId,collectionArticleId);
        return "redirect:articleDetail/"+collectionArticleId;
    }
    @PostMapping("/deleteCollection")
    public String deleteCollection(@RequestParam Long collectionUserId,@RequestParam Long collectionArticleId){
        collectionDao.deleteCollection(collectionUserId,collectionArticleId);
        return "redirect:articleDetail/"+collectionArticleId;
    }

    @PostMapping("/deleteCollection2")
    public String deleteCollection2(@RequestParam Long collectionUserId,@RequestParam Long collectionArticleId){
        collectionDao.deleteCollection(collectionUserId,collectionArticleId);
        return "redirect:myCollection?page=1";
    }

    @GetMapping("/myCollection")
    public String myCollection(HttpServletRequest request,Integer page){
        return collectionService.toMyCollection(request,page);
    }
}
