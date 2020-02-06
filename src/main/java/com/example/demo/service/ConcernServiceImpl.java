package com.example.demo.service;

import com.example.demo.dao.ConcernDao;
import com.example.demo.dto.MyConcernDTO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ConcernServiceImpl implements ConcernService{
    @Autowired
    ConcernDao concernDao;

    @Override
    public String toMyConcern(HttpServletRequest request) {
        User sessionUser = (User)request.getSession().getAttribute("user");
        List<MyConcernDTO> myConcernDTOS = concernDao.myConcernList(sessionUser.getUserId());
        request.setAttribute("myConcernDTOS",myConcernDTOS);
        return "myConcern";
    }
}
