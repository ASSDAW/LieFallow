package com.example.demo.dao;

import com.example.demo.pojo.Remark;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RemarkDao {
    @Insert("insert into remark values(#{remarkId},#{remarkUserId},#{remarkContent},#{remarkTime},#{articleId})")
    void insert(Remark remark);

    @Delete("delete from remark where remarkId = #{remarkId}")
    void delete(Long remarkId);
}
