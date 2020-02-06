package com.example.demo.controller;

import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dto.ArticleRemarkDTO;
import com.example.demo.dto.HotUserDTO;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.Remark;
import com.example.demo.pojo.User;
import com.example.demo.dao.*;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/index")
    public String indexTag(@RequestParam String tag,@RequestParam Integer page,HttpServletRequest request){
        return articleService.toIndex(tag,page,request);
    }

    @GetMapping("/articleDetail/{articleId}")
    public String articleDetail(@PathVariable Long articleId, HttpServletRequest request){
        return articleService.toArticleDetail(articleId,request);
    }

    @PostMapping("/remark")
    public String remark(Remark remark,HttpServletRequest request){
        return articleService.toRemark(remark,request);
    }

    @PostMapping("/fileUpload")
    public String fileUpload(Article article,@RequestParam MultipartFile articleInputFile,HttpServletRequest request){
        return articleService.fileUpload(article,articleInputFile,request);
    }


}
