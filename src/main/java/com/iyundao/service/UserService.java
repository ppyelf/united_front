package com.iyundao.service;

import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.entity.*;

import java.util.List;


/**
 * @ClassName: UserService
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/17 14:06
 * @Description: 服务层 -- 用户
 * @Version: V2.0
 */
public interface UserService {

    /**
     * 查询用户名是否存在
     * @param account
     * @return
     */
    User findByAccount(String account);

    /**
     * 根据账号,密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    User findByAccountAndPassword(String username, String password);

    /**
     * 用户搜索
     * @return
     */
    Page<User> findByKey(Pageable pageable);

    /**
     * 根据ID获取实体信息
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 添加用户
     * @param user
     * @param permissions
     */
    JsonResult save(User user, Subject subject, String departId, String groupsId, List<Role> roles, List<Permission> permissions, JsonResult jsonResult);

    /**
     * 获取用户详情的json
     * @param user
     * @return
     */
    JSONObject getUserInfoJson(User user);

    List<User> findAll();

    /**
     * 查询组织用户分页
     * @return
     */
    List<User> findByGroupIdForPage(String groupId);

    /**
     * 查询部门用户分页
     */
    List<User> findByDepartIdForPage(String departId);

    /**
     * 检测编号是否存在
     * @param code
     * @return
     */
    boolean existsCode(String code);

    /**
     * 根据编号查询实体
     */
    User findByCode(String code);

    /**
     * 保存实体
     * @param user
     */
    User save(User user);

    /**
     * 通过用户id找到所有实体
     */
    List<User> findbyIds(String[] userids);

    /**
     * 通过机构id获得所有的用户
     */
    List<User> findBySubjectIdForPage(String id);

    /**
     * 获取父类为空行业集合
     * @return
     */
    List<Industry> findByFatherIsNull();

    /**
     * 根据父类ID获取集合
     * @param id
     * @return
     */
    List<Industry> findByFatherId(String id);

    /**
     * 保存个人培训经历
     * @param name
     * @param startTime
     * @param endTime
     * @param honor
     * @param remark
     * @param user
     * @return
     */
    UserTrain saveUserTrain(String name, String startTime, String endTime, String honor, String remark, User user);

    /**
     * 根据ID查询用户培训实体
     * @param id
     * @return
     */
    UserTrain findUserTrainById(String id);

    /**
     * 删除用户培训实体
     * @param train
     */
    void deleteUserTrain(UserTrain train);

    /**
     * 根据行业ID查询实体信息
     * @param industryId
     * @return
     */
    Industry findIndustryById(String industryId);

    /**
     * 保存用户工作履历
     * @param companyName
     * @param industry
     * @param positionName
     * @param startTime
     * @param endTime
     * @param user
     * @return
     */
    UserWork saveUserWork(String companyName, Industry industry, String positionName, String startTime, String endTime, User user);

    /**
     * 根据ID查询用户工作履历
     * @param id
     * @return
     */
    UserWork findUserWorkById(String id);

    /**
     * 删除用户工作履历
     * @param work
     */
    void deleteUserWork(UserWork work);
}
