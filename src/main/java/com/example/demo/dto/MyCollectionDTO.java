package com.example.demo.dto;

import lombok.Data;

@Data
public class MyCollectionDTO {
    private String userId;
    private String userName;
    private String userPhoto;
    private Long articleId;
    private String articleTitle;
    private Long collectionArticleId;
}
