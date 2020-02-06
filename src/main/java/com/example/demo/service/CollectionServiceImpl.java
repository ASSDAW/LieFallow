package com.example.demo.service;

import com.example.demo.dao.CollectionDao;
import com.example.demo.dto.MyCollectionDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;

    int pageSize = 5;//每页显示数据量
    int totalPage = 0;//总页数
    int totalCount = 0;//总数据量

    @Override
    public String toMyCollection(HttpServletRequest request, Integer page) {
        User sessionUser = (User)request.getSession().getAttribute("user");
        List<MyCollectionDTO> myCollectionDTOs;
        totalCount = collectionDao.countAllMyCollection(Long.valueOf(sessionUser.getUserId()));
        totalPage = totalCount % pageSize == 0?totalCount / pageSize:totalCount / pageSize + 1;	//总页数
        if (page<=0){
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        if (totalCount == 0){
            myCollectionDTOs = null;
        }else {
            myCollectionDTOs = collectionDao.findMyCollection(sessionUser.getUserId(),(page-1)*pageSize,pageSize);
        }
        request.setAttribute("myCollectionDTOs",myCollectionDTOs);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        return "myCollection";
    }
}
