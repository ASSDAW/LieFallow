package com.example.demo.dao;

import com.example.demo.dto.MyRemarkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyRemarkDTODao {
    @Select("select remark.*,articleTitle,articleAuthorId,userName \n" +
            "from remark,article,user where remark.articleId = article.articleId " +
            "and article.articleAuthorId = user.userId and remark.remarkUserId=#{remarkUserId} limit #{page},#{pageSize}")
    public List<MyRemarkDTO> findMyRemarkByUserId(Long remarkUserId, Integer page, Integer pageSize);

    @Select("select count(remarkUserId) from remark where remarkUserId = #{remarkUserId}")
    public Integer countMyRemarkByUserId(Long remarkUserId);
}
