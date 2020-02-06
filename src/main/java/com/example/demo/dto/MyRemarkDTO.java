package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MyRemarkDTO {
    private Long remarkId;
    private String remarkContent;
    private Date remarkTime;
    private Long articleId;
    private String articleTitle;
    private String articleAuthorId;
    private String userName;//articleAuthorName
}
