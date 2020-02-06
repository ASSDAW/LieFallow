package com.example.demo.dao;

import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dto.HotUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotTopicDao {
    @Select("select * from article,user where article.articleAuthorId = user.userId  order by article.remarkNum desc limit 0,3")
    List<ArticleDetailDTO> findRemarkTop3();

    @Select("select user.userId,user.userName,user.userPhoto,a.articleNum " +
            "from user,article,(select articleAuthorId,count(articleAuthorId) as articleNum from article group by articleAuthorId order by count(articleAuthorId) desc limit 0,3) as a " +
            "where a.articleAuthorId = user.userId and article.articleAuthorId = user.userId group by user.userId order by a.articleNum desc" )
    List<HotUserDTO> findUserTop3();
}
