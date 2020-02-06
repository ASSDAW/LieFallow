package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DialogueDTO {
    private Long messageId;
    private String senderId;
    private String receiverId;
    private String sendMessage;
    private Date sendTime;
    private Integer isRead;
    private String senderName;
    private String senderPhoto;
    private String receiverName;
    private String receiverPhoto;
}
