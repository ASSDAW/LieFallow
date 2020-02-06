package com.example.demo.controller;

import com.example.demo.dao.ConcernDao;
import com.example.demo.dto.MyConcernDTO;
import com.example.demo.pojo.User;
import com.example.demo.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ConcernController {
    @Autowired
    ConcernDao concernDao;
    @Autowired
    ConcernService concernService;
    @GetMapping("/myConcern")
    public String myConcern(HttpServletRequest request){
        return concernService.toMyConcern(request);
    }

    @PostMapping("/insertConcern")
    public String insertConcern(@RequestParam String concernUserId,@RequestParam String concernPeopleId){
        concernDao.insertConcern(null,concernUserId,concernPeopleId);
        return "redirect:otherInformation/"+concernPeopleId+"?page=1";
    }

    @PostMapping("/deleteConcern")
    public String deleteConcern(@RequestParam String concernUserId,@RequestParam String concernPeopleId){
        concernDao.deleteConcern(concernUserId,concernPeopleId);
        return "redirect:otherInformation/"+concernPeopleId+"?page=1";
    }

    @PostMapping("/deleteConcern2")
    public String deleteConcern2(@RequestParam String concernUserId,@RequestParam String concernPeopleId){
        concernDao.deleteConcern(concernUserId,concernPeopleId);
        return "redirect:myConcern";
    }
}
