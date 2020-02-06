package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface PersonalService {
    public String toMyArticle(HttpServletRequest request, Integer page);
    public String toMyRemark(HttpServletRequest request, Integer page);
}
