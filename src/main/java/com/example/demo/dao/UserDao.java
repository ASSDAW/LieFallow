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


    @Insert("insert into user values(#{userId},#{userPassword},#{salt}," +
            "#{userName},#{userAge},#{userSex},#{userPhoto},#{isLock},#{isAdmin})")
    void insert(User user);

    @Update("update user set userPassword = #{userPassword},salt = #{salt},userName=#{userName}," +
            "userAge=#{userAge},userSex=#{userSex},userPhoto=#{userPhoto},isLock=#{isLock}," +
            "isAdmin=#{isAdmin} where userId=#{userId}")
    void update(User user);

    @Update("update user set isLock = 1 where userId = #{userId}")
    void lock(String userId);

    @Update("update user set isLock = 0 where userId = #{userId}")
    void unlock(String userId);
}
