package com.example.demo.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Remark {
    private Long remarkId;
    private String remarkUserId;
    private String remarkContent;
    private Date remarkTime;
    private Long articleId;
}


