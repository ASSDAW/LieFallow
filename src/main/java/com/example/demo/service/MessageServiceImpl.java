package com.example.demo.service;

import com.example.demo.dao.MessageDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.DialogueDTO;
import com.example.demo.dto.MessageListDTO;
import com.example.demo.dto.UserMessageListDTO;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    UserDao userDao;
    @Autowired
    MessageDao messageDao;

    List<UserMessageListDTO> dialogueLists = new ArrayList<>();

    @Override
    public String toMyMessage(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String userId = sessionUser.getUserId();
        List<MessageListDTO> messageList = messageDao.messageList(userId);
        Set<String> userList = new TreeSet<>();
        for (int i = 0; i < messageList.size(); i++) {
            if (!messageList.get(i).getReceiverId().equals(userId)) {
                userList.add(messageList.get(i).getReceiverId());
            }
            if (!messageList.get(i).getSenderId().equals(userId)) {
                userList.add(messageList.get(i).getSenderId());
            }
        }
        List<UserMessageListDTO> userMessageLists = new ArrayList<>();
        for (String value:userList){
            UserMessageListDTO userMessageListDTO = new UserMessageListDTO();
            userMessageListDTO.setUserId(userDao.findById(value).getUserId());
            userMessageListDTO.setUserName(userDao.findById(value).getUserName());
            userMessageListDTO.setUserPhoto(userDao.findById(value).getUserPhoto());
            List<DialogueDTO> dialogueDTOs = messageDao.findDialogue(userId,value);
            userMessageListDTO.setLastSendTime(dialogueDTOs.get(dialogueDTOs.size()-1).getSendTime());
            userMessageListDTO.setLastSendMessage(dialogueDTOs.get(dialogueDTOs.size()-1).getSendMessage());
            userMessageListDTO.setLastSenderName(dialogueDTOs.get(dialogueDTOs.size()-1).getSenderName());
            userMessageLists.add(userMessageListDTO);
        }

        request.setAttribute("userMessageLists",userMessageLists);
        dialogueLists = userMessageLists;
        return "myMessage";
    }

    @Override
    public String dialogue(String userId, HttpServletRequest request) {
        User receiver = userDao.findById(userId);
        User sessionUser = (User) request.getSession().getAttribute("user");
        List<DialogueDTO> dialogueDTOs = messageDao.findDialogue(sessionUser.getUserId(), userId);

        request.setAttribute("receiver", receiver);
        request.setAttribute("dialogueDTOs", dialogueDTOs);
        request.setAttribute("dialogueLists",dialogueLists);
        return "dialogue";
    }

    @Override
    public String sendMessgaes(String senderId, String receiverId, String sendMessgae) {
        Timestamp sendTime = new Timestamp(System.currentTimeMillis());//当前时间
        int isRead = 0;
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setSendMessage(sendMessgae);
        message.setSendTime(sendTime);
        message.setIsRead(isRead);
        message.setIsDel(0);
        messageDao.insertMessage(message);
        return "redirect:dialogue/" + receiverId;
    }
}
