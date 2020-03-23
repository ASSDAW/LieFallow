package com.example.demo.dao;

import com.example.demo.dto.ArticleDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDetailDTODao {
    @Select("select * from user,article where userId = articleAuthorId and isDel = 0 order by createTime desc")
    List<ArticleDetailDTO> findAll();

    @Select("select * from user,article where userId = articleAuthorId and articleId = #{articleId} and isDel = 0 order by createTime desc")
    ArticleDetailDTO findByArticleId(Long articleId);

    @Select("select * from user,article where userId = articleAuthorId and articleAuthorId = #{articleAuthorId} and isDel = 0 order by createTime desc limit #{page},#{pageSize}")
    List<ArticleDetailDTO> findByArticleAuthorId(String articleAuthorId, Integer page, Integer pageSize);

    @Select("select * from user,article where userId = articleAuthorId and articleTag = #{articleTag} and isDel = 0 order by createTime desc limit #{page},#{pageSize}")
    List<ArticleDetailDTO> findByArticleTag(String articleTag, Integer page, Integer pageSize);

    @Select("select * from user,article where userId = articleAuthorId and isDel = 0 order by createTime desc limit #{page},#{pageSize}")
    List<ArticleDetailDTO> findByCreateTime(Integer page, Integer pageSize);

    @Select("select concernPeopleId from concern where concernUserId = #{userId}")
    List<String> findUserConcern(String userId);
}
