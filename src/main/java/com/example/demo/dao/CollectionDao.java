package com.example.demo.dao;

import com.example.demo.dto.MyCollectionDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionDao {

    @Select("select count(1) from collection where collectionUserId = #{collectionUserId} and collectionArticleId = #{collectionArticleId}")
    Integer selectCollection(Long collectionUserId,Long collectionArticleId);

    @Insert("insert into collection values(#{collectionId},#{collectionUserId},#{collectionArticleId})")
    void insertCollection(Long collectionId,Long collectionUserId,Long collectionArticleId);

    @Delete("delete from collection where collectionUserId = #{collectionUserId} and collectionArticleId = #{collectionArticleId}")
    void deleteCollection(Long collectionUserId,Long collectionArticleId);

    @Select("select count(1) from collection where collectionUserId = #{collectionUserId}")
    int countAllMyCollection(Long collectionUserId);

    @Select("select userId,userName,userPhoto,articleId,articleTitle,articleAuthorId,collectionArticleId " +
            "from user,article,collection where collectionArticleId = articleId and userId = articleAuthorId " +
            "and collectionUserId = #{userId} limit #{page},#{pageSize}")
    List<MyCollectionDTO> findMyCollection(String userId,Integer page,Integer pageSize);
}
