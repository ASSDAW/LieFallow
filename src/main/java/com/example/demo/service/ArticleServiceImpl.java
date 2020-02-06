package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.ArticleDetailDTO;
import com.example.demo.dto.ArticleRemarkDTO;
import com.example.demo.dto.HotUserDTO;
import com.example.demo.pojo.Article;
import com.example.demo.pojo.Remark;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleDao articleDao;
    @Autowired
    ArticleDetailDTODao articleDetailDTODao;
    @Autowired
    UserDao userDao;
    @Autowired
    RemarkDao remarkDao;
    @Autowired
    ArticleRemarkDTODao articleRemarkDTODao;
    @Autowired
    HotTopicDao hotTopicDao;
    @Autowired
    CollectionDao collectionDao;

    @Override
    public String toIndex(String tag, @PathVariable Integer page, HttpServletRequest request) {
        List<ArticleDetailDTO> articleDetailDTOS = new ArrayList<>();
        List<ArticleDetailDTO> hotTopics = hotTopicDao.findRemarkTop3();
        List<HotUserDTO> hotUsers = hotTopicDao.findUserTop3();
        int totalCount;
        if (tag.equals("recent")){
            totalCount = articleDao.countAll();//总数据数
        }else {
            totalCount = articleDao.countByTag(tag);//相应tag总数据数
        }
        int pageSize = 5;//每页显示数据量
        int totalPage = totalCount % pageSize == 0?totalCount / pageSize:totalCount / pageSize + 1;	//总页数

        if (page<1) page = 1;
        if(page > totalPage) page = totalPage;

        if (tag.equals("recent")){
            if(totalCount == 0) {
                articleDetailDTOS = null;
            }else
            articleDetailDTOS = articleDetailDTODao.findByCreateTime((page-1)*pageSize,pageSize);
        }
        else if (tag.equals("attention")){
            User sessionUser = (User)request.getSession().getAttribute("user");
            List<String> userConcern = articleDetailDTODao.findUserConcern(sessionUser.getUserId());
            if (userConcern.size()==0){
                articleDetailDTOS =null;
            }
            else {
                for (int i = 0; i < userConcern.size(); i++) {
                    String s = userConcern.get(i);
                    List<ArticleDetailDTO> list = articleDetailDTODao.findByArticleAuthorId(s,0,100);
                    //????分页有问题
                    articleDetailDTOS.addAll(list);
                }
            }
        }
        else{
            if(totalCount == 0) {
                articleDetailDTOS = null;
            }else
                articleDetailDTOS = articleDetailDTODao.findByArticleTag(tag,(page-1)*pageSize,pageSize);
        }

        request.setAttribute("articleDetailDTOS",articleDetailDTOS);
        request.setAttribute("hotTopics",hotTopics);
        request.setAttribute("hotUsers",hotUsers);
        request.setAttribute("tag",tag);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        return "index";
    }

    @Override
    public String toArticleDetail(Long articleId, HttpServletRequest request) {
        User SessionUser = (User)request.getSession().getAttribute("user");
        boolean flag = false;
        if (collectionDao.selectCollection(Long.valueOf(SessionUser.getUserId()),articleId) == 1){
            flag = true;
        }
        request.setAttribute("flag",flag);
        articleDao.hitNumAdd(articleId);
        ArticleDetailDTO articleDetailDTO = articleDetailDTODao.findByArticleId(articleId);
        List<ArticleRemarkDTO> articleRemarkDTOs = articleRemarkDTODao.findAllByArticleId(articleId);
        request.setAttribute("articleDetailDTO",articleDetailDTO);
        request.setAttribute("articleRemarkDTOs",articleRemarkDTOs);
        return "articleDetail";
    }

    @Autowired
    private DataSourceTransactionManager txManager;
    @Override
    public String toRemark(Remark remark, HttpServletRequest request) {
        User SessionUser = (User)request.getSession().getAttribute("user");
        Timestamp remarkTime = new Timestamp(System.currentTimeMillis());//当前时间
        remark.setRemarkUserId(SessionUser.getUserId());
        remark.setRemarkTime(remarkTime);

        TransactionDefinition tf = new DefaultTransactionDefinition();
        TransactionStatus ts = txManager.getTransaction(tf);

        try{
            remarkDao.insert(remark);
            articleDao.remarkNumAdd(remark.getArticleId());
            txManager.commit(ts);
        } catch (Exception e) {
            txManager.rollback(ts);
            e.printStackTrace();
        }
        return "redirect:articleDetail/"+remark.getArticleId();
    }

    @Override
    public String fileUpload(Article article, MultipartFile articleInputFile, HttpServletRequest request) {
        User SessionUser = (User)request.getSession().getAttribute("user");
        String path = null;
        Timestamp createTime = new Timestamp(System.currentTimeMillis());//当前时间
        Timestamp lastRemarkTime = new Timestamp(System.currentTimeMillis());//当前时间
        int remarkNum = 0;
        int hitNum = 0;

        article.setArticleInputFileURL(path);
        article.setArticleAuthorId(SessionUser.getUserId());
        article.setCreateTime(createTime);
        article.setLastRemarkTime(lastRemarkTime);
        article.setRemarkNum(remarkNum);
        article.setHitNum(hitNum);
        String fileName ="";
        try {
            if (articleInputFile.isEmpty()) {
                articleDao.insert(article);
                return "redirect:index?tag=recent&page=1";
            }
            // 获取文件名
            fileName = articleInputFile.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 设置文件存储路径
            //String filePath = "/static/uploadFile/";
            String filePath = "D:\\IntelliJ IDEA 2019.1.3\\project\\LieFallow\\src\\main\\resources\\static\\uploadFile/";
            path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            articleInputFile.transferTo(dest);// 文件写入
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        article.setArticleInputFileURL("/static/uploadFile/"+fileName);
        articleDao.insert(article);
        return "redirect:index?tag=recent&page=1";
    }
}
