package com.example.demo.dao;

import com.example.demo.dto.MyConcernDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConcernDao {
    @Insert("insert into concern values(#{concernId},#{concernUserId},#{concernPeopleId})")
    void insertConcern(Long concernId,String concernUserId,String concernPeopleId);

    @Delete("delete from concern where concernUserId = #{concernUserId} and concernPeopleId = #{concernPeopleId}")
    void deleteConcern(String concernUserId,String concernPeopleId);

    @Select("select count(1) from concern where concernUserId = #{concernUserId} and concernPeopleId = #{concernPeopleId}")
    Integer isConcern(String concernUserId,String concernPeopleId);

    @Select("select concernUserId,concernPeopleId,userName as concernPeopleName,userPhoto as concernPeoplePhoto" +
            " from user,concern where userId = concernPeopleId and concernUserId = #{concernUserId}")
    List<MyConcernDTO> myConcernList(String concernUserId);
}
