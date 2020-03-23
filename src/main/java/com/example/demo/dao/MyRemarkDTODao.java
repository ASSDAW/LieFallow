package com.example.demo.dao;

import com.example.demo.dto.MyRemarkDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyRemarkDTODao {
    @Select("select remark.*,articleTitle,articleAuthorId,userName \n" +
            "from remark,article,user where remark.articleId = article.articleId " +
            "and article.articleAuthorId = user.userId and remark.remarkUserId=#{remarkUserId} " +
            "and remark.isDel = 0 limit #{page},#{pageSize}")
    public List<MyRemarkDTO> findMyRemarkByUserId(Long remarkUserId, Integer page, Integer pageSize);

    @Select("select count(remarkUserId) from remark where remarkUserId = #{remarkUserId} and isDel = 0")
    public Integer countMyRemarkByUserId(Long remarkUserId);
}
