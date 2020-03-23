package com.example.demo.dao;

import com.example.demo.pojo.Remark;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RemarkDao {
    @Insert("insert into remark values(#{remarkId},#{remarkUserId},#{remarkContent},#{remarkTime},#{articleId},#{isDel})")
    void insert(Remark remark);

    @Update("update remark set isDel = 1 where remarkId = #{remarkId}")
    void delete(Long remarkId);

    @Update("update remark set isDel = 2 where remarkId = #{remarkId}")
    void adminLock(Long remarkId);

    @Update("update remark set isDel = 0 where remarkId = #{remarkId}")
    void adminUnLock(Long remarkId);

    @Update("update article set remarkNum = remarkNum - 1 where articleId = #{articleId}")
    void updateRemarkNum(Long articleId);

    @Select("select * from remark where remarkId = #{remarkId} and isDel = 0")
    Remark findById(Long remarkId);

    @Select("select * from remark")
    List<Remark> adminFindAll();
}
