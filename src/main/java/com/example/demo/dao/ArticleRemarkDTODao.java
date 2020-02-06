package com.example.demo.dao;

import com.example.demo.dto.ArticleRemarkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleRemarkDTODao {
    @Select("select * from remark,user where remark.articleId = #{articleId} and remark.remarkUserId = user.userId")
    List<ArticleRemarkDTO> findAllByArticleId(Long articleId);
}
