package com.example.demo.dao;

import com.example.demo.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Insert("insert into article values(#{articleId},#{articleTitle},#{articleContent},#{articleTag},#{articleInputFileURL}," +
                                        "#{articleAuthorId},#{createTime},#{lastRemarkTime},#{remarkNum},#{hitNum},#{isDel})")
    void insert(Article article);

    @Select("select * from article where isDel = 0")
    List<Article> findAll();

    @Select("select * from article")
    List<Article> adminFindAll();

    @Select("select count(1) from article where articleTag = #{tag} and isDel = 0")
    Integer countByTag(String tag);

    @Select("select count(1) from article where articleAuthorId = #{articleAuthorId} and isDel = 0")
    Integer countByArticleAuthorId(String articleAuthorId);

    @Select("select count(1) from article where isDel = 0")
    Integer countAll();

    @Select("select * from article where articleId = #{articleId} and isDel = 0 order by createTime desc ")
    Article findByArticleId(Long articleId);

    @Select("select * from article where articleAuthorId = #{articleAuthorId} and isDel = 0 order by createTime desc")
    List<Article> findByArticleAuthorId(String articleAuthorId);

    @Select("select * from article where articleTag = #{articleTag} and isDel = 0 order by createTime desc")
    List<Article> findByArticleTag(String articleTag);

    @Select("select * from article where isDel = 0 order by createTime desc")
    List<Article> findByCreateTime();

    @Update("update article set hitNum = hitNum + 1 where articleId = #{articleId}")
    void hitNumAdd(Long articleId);

    @Update("update article set remarkNum = remarkNum + 1 where articleId = #{articleId}")
    void remarkNumAdd(Long articleId);

    @Update("update article set isDel = 1 where articleId = #{articleId}")
    void deleteArticle(Long articleId);

    @Update("update remark set isDel = 1 where articleId = #{articleId}")
    void deleteArticleRemark(Long articleId);

    @Update("update article set isDel = 2 where articleId = #{articleId}")
    void adminDelete(Long articleId);

    @Update("update article set isDel = 0 where articleId = #{articleId}")
    void adminUnDelete(Long articleId);

}
