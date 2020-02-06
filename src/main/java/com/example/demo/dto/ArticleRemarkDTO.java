package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleRemarkDTO {
    private Long remarkId;
    private String remarkUserId;
    private String remarkContent;
    private Date remarkTime;
    private Long articleId;
    private String userName;
    private String userPhoto;
}
