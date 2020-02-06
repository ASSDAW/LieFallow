package com.example.demo.dto;

import lombok.Data;

@Data
public class HotUserDTO {
    private String userId;
    private String userName;
    private String userPhoto;
    private Integer articleNum;
}
