package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.ArticleDetailDTODao;
import com.example.demo.dao.ConcernDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class OtherInformationServiceImpl implements OtherInformationService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleDetailDTODao articleDetailDTODao;
    @Autowired
    UserDao userDao;
    @Autowired
    ConcernDao concernDao;

    @Override
    public String toOtherInformation(String userId, HttpServletRequest request, Integer page) {
        User SessionUser = (User)request.getSession().getAttribute("user");
        boolean flag = false;
        if (concernDao.isConcern(SessionUser.getUserId(),userId) == 1){
            flag = true;
        }
        request.setAttribute("flag",flag);

        int pageSize = 5;//每页显示数据量
        int totalPage = 0;//总页数
        int totalCount = 0;//总数据量
        User user = userDao.findById(userId);
        List<ArticleDetailDTO> theArticleDTOs;
        totalCount = articleDao.countByArticleAuthorId(userId);
        totalPage = totalCount % pageSize == 0?totalCount / pageSize:totalCount / pageSize + 1;	//总页数
        if (page<=0){
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        if (totalCount == 0){
            theArticleDTOs = null;
        }else {
            theArticleDTOs = articleDetailDTODao.findByArticleAuthorId(userId,(page-1)*pageSize,pageSize);
        }
        request.setAttribute("user",user);
        request.setAttribute("theArticleDTOs",theArticleDTOs);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        return "otherInformation";
    }
}
