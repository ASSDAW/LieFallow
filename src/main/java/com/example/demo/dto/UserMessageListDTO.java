package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserMessageListDTO {
    private String userId;
    private String userName;
    private String userPhoto;
    private Date lastSendTime;
    private String lastSendMessage;
    private String lastSenderName;
}
