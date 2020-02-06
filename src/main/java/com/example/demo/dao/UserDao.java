package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where userId = #{userId}")
    User findById(String userId);

    @Select("select count(userId) from user where userId = #{userId} and userPassword = #{userPassword}")
    Integer check(String userId, String userPassword);


    @Insert("insert into user values(#{userId},#{userPassword},#{userName},#{userAge},#{userSex},#{userPhoto})")
    void insert(User user);

    @Update("update user set userPassword = #{userPassword},userName=#{userName},userAge=#{userAge},userSex=#{userSex},userPhoto=#{userPhoto} where userId=#{userId}")
    void update(User user);
}
