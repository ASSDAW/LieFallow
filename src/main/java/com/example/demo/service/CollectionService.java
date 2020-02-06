package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface CollectionService {
    public String toMyCollection(HttpServletRequest request, Integer page);
}
