package com.example.demo.controller;

import com.example.demo.dto.DialogueDTO;
import com.example.demo.dto.MessageListDTO;
import com.example.demo.dto.UserMessageListDTO;
import com.example.demo.dao.MessageDao;
import com.example.demo.dao.UserDao;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @GetMapping("/myMessage")
    public String myMessage(HttpServletRequest request) {
        return  messageService.toMyMessage(request);
    }

    @GetMapping("/dialogue/{userId}")
    public String dialogue(@PathVariable String userId, HttpServletRequest request) {
        return messageService.dialogue(userId,request);
    }

    @PostMapping("/sendMessgae")
    public String sendMessgaes(@RequestParam String senderId, @RequestParam String receiverId,
                               @RequestParam String sendMessgae) {
        return  messageService.sendMessgaes(senderId,receiverId,sendMessgae);
    }
}
