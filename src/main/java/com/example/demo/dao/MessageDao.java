package com.example.demo.dao;

import com.example.demo.dto.DialogueDTO;
import com.example.demo.dto.MessageListDTO;
import com.example.demo.pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MessageDao {

    @Select("select * from message")
    List<Message> findAll();

    @Insert("insert into message values(#{messageId},#{senderId},#{receiverId},#{sendMessage},#{sendTime},#{isRead},#{isDel})")
     void insertMessage(Message message);

    @Select("select message.*,user1.userName as senderName,user1.userPhoto as senderPhoto," +
            "user2.userName as receiverName,user2.userPhoto as receiverPhoto from message," +
            "user as user1,user as user2 where ((senderId = #{senderId} and receiverId = #{receiverId} and " +
            "senderId = user1.userId and receiverId = user2.userId) or (senderId = #{receiverId} " +
            "and receiverId = #{senderId} and senderId = user1.userId and receiverId = user2.userId ))" +
            "and isDel = 0 order by sendTime")
     List<DialogueDTO> findDialogue(String senderId,String receiverId);


    @Select("select distinct senderId,receiverId from message where (senderId = #{userId} or receiverId = #{userId}) and isDel = 0")
    List<MessageListDTO> messageList(String userId);

    @Update("update message set isDel = 1 where messageId=#{messageId}")
    void lock(Long messageId);

    @Update("update message set isDel = 0 where messageId=#{messageId}")
    void unLock(Long messageId);
}
