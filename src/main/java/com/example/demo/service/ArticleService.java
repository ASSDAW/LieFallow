package com.example.demo.service;

import com.example.demo.pojo.Article;
import com.example.demo.pojo.Remark;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ArticleService {
    public String toIndex(String tag,Integer page, HttpServletRequest request);
    public String toArticleDetail(Long articleId, HttpServletRequest request);
    public String toRemark(Remark remark, HttpServletRequest request);
    public String fileUpload(Article article, @RequestParam MultipartFile articleInputFile, HttpServletRequest request);
}
