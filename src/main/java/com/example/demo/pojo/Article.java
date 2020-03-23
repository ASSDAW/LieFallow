package com.example.demo.pojo;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Long articleId;
    private String articleTitle;
    private String articleContent;
    private String articleTag;
    private String articleInputFileURL;
    private String articleAuthorId;
    private Date createTime;
    private Date lastRemarkTime;
    private Integer remarkNum;
    private Integer hitNum;
    private Integer isDel;

}
