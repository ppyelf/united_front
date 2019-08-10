package com.iyundao.service;

import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: LearnService
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习
 * @Version: V1.0
 */
public interface LearnService {

    /**
     * 添加学习
     * @param file
     * @param title
     * @param content
     * @param subjects
     * @param departs
     * @param groups
     * @param users
     * @return
     */
    JsonResult saveAll(MultipartFile file, String title, String content, String[] subjects, String[] departs, String[] groups, String[] users);

    /**
     * 列表
     * @return
     */
    List<Learn> findAll();

    /**
     * 根据id查找实体
     * @param learnId
     * @return
     */
    Learn findById(String learnId);

    /**
     * 查看详情
     * @param learn
     */
    JSONObject findView(Learn learn);

    /**
     * 删除详情
     * @param learn
     */
    void delLearn(Learn learn);
}
