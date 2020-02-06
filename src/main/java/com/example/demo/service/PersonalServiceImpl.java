package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dto.MyRemarkDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService{
    @Autowired
    UserDao userDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleDetailDTODao articleDetailDTODao;
    @Autowired
    MyRemarkDTODao myRemarkDTODao;
    @Autowired
    RemarkDao remarkDao;

    @Override
    public String toMyArticle(HttpServletRequest request, Integer page) {
        int pageSize = 5;//每页显示数据量
        int totalPage = 0;//总页数
        int totalCount = 0;//总数据量
        User sessionUser = (User)request.getSession().getAttribute("user");
        List<ArticleDetailDTO> myArticleDTOs;
        totalCount = articleDao.countByArticleAuthorId(sessionUser.getUserId());
        totalPage = totalCount % pageSize == 0?totalCount / pageSize:totalCount / pageSize + 1;	//总页数
        if (page<=0){
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        if (totalCount == 0){
            myArticleDTOs = null;
        }else {
            myArticleDTOs = articleDetailDTODao.findByArticleAuthorId(sessionUser.getUserId(),(page-1)*pageSize,pageSize);
        }
        request.setAttribute("myArticleDTOs",myArticleDTOs);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        return "myArticle";
    }

    @Override
    public String toMyRemark(HttpServletRequest request, Integer page) {
        int pageSize = 5;//每页显示数据量
        int totalPage = 0;//总页数
        int totalCount = 0;//总数据量
        User sessionUser = (User)request.getSession().getAttribute("user");
        Long remarkUserId = Long.valueOf(sessionUser.getUserId());
        List<MyRemarkDTO> myRemarkDTOMappers;
        totalCount = myRemarkDTODao.countMyRemarkByUserId(remarkUserId);
        totalPage = totalCount % pageSize == 0?totalCount / pageSize:totalCount / pageSize + 1;	//总页数
        if (page<=0){
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        if (totalCount == 0){
            myRemarkDTOMappers = null;
        }else {
            myRemarkDTOMappers = myRemarkDTODao.findMyRemarkByUserId(remarkUserId,(page-1)*pageSize,pageSize);
        }
        request.setAttribute("myRemarkDTOMappers",myRemarkDTOMappers);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        return "myRemark";
    }
}
