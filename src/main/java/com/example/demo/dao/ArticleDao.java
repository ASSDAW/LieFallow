package com.example.demo.dao;

import com.example.demo.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleDao {

    @Insert("insert into article values(#{articleId},#{articleTitle},#{articleContent},#{articleTag},#{articleInputFileURL}," +
                                        "#{articleAuthorId},#{createTime},#{lastRemarkTime},#{remarkNum},#{hitNum})")
    void insert(Article article);

    @Select("select * from article")
    List<Article> findAll();

    @Select("select count(1) from article where articleTag = #{tag}")
    Integer countByTag(String tag);

    @Select("select count(1) from article where articleAuthorId = #{articleAuthorId}")
    Integer countByArticleAuthorId(String articleAuthorId);

    @Select("select count(1) from article")
    Integer countAll();

    @Select("select * from article where articleId = #{articleId} order by createTime desc")
    Article findByArticleId(Long articleId);

    @Select("select * from article where articleAuthorId = #{articleAuthorId} order by createTime desc")
    List<Article> findByArticleAuthorId(String articleAuthorId);

    @Select("select * from article where articleTag = #{articleTag} order by createTime desc")
    List<Article> findByArticleTag(String articleTag);

    @Select("select * from article order by createTime desc")
    List<Article> findByCreateTime();

    @Update("update article set hitNum = hitNum + 1 where articleId = #{articleId}")
    void hitNumAdd(Long articleId);

    @Update("update article set remarkNum = remarkNum + 1 where articleId = #{articleId}")
    void remarkNumAdd(Long articleId);

    @Delete("delete from article where articleId = #{articleId}")
    void deleteArticle(Long articleId);

    @Delete("delete from remark where articleId = #{articleId}")
    void deleteArticleRemark(Long articleId);


}
