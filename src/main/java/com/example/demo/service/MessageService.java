package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {
    public String toMyMessage(HttpServletRequest request);
    public String dialogue(String userId, HttpServletRequest request);
    public String sendMessgaes(String senderId,String receiverId, String sendMessgae);
}
