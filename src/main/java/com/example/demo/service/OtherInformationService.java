package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface OtherInformationService {
    public String toOtherInformation(String userId,HttpServletRequest request,Integer page);
}
