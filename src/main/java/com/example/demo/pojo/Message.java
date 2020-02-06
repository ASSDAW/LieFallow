package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private Long messageId;
    private String senderId;
    private String receiverId;
    private String sendMessage;
    private Date sendTime;
    private Integer isRead;
}
