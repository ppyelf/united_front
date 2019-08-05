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
     * @param labels
     */
    JsonResult save(User user, Subject subject, String departId, String groupsId, List<Role> roles, List<Permission> permissions, List<Label> labels, JsonResult jsonResult);

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

    /**
     * 添加用户详情
     * @param user
     * @param name
     * @param nation
     * @param age
     * @param political
     * @param nativePlace
     * @param university
     * @param major
     * @param education
     * @param birthDay
     * @param address
     * @param tel
     * @param email
     * @param wx
     * @param qq
     * @param iCard
     * @param selfEvaluation
     * @return
     */
    UserInfo saveUserInfo(User user, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation);

    /**
     * 根据ID查询用户详情
     * @param id
     * @return
     */
    UserInfo findUserInfoById(String id);

    /**
     * 修改用户详情
     * @param userInfo
     * @param name
     * @param nation
     * @param age
     * @param political
     * @param nativePlace
     * @param university
     * @param major
     * @param education
     * @param birthDay
     * @param address
     * @param tel
     * @param email
     * @param wx
     * @param qq
     * @param iCard
     * @param selfEvaluation
     * @return
     */
    UserInfo modifyUserInfo(UserInfo userInfo, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation);

    /**
     * 根据用户ID查询用户详情实体
     * @param id
     * @return
     */
    UserInfo findUserInfoByUserId(String id);

    /**
     * 查询用户培训经历集合
     * @param id
     * @return
     */
    List<UserTrain> findUserTrainByUserId(String id);

    /**
     * 查询用户工作履历集合
     * @param userId
     * @return
     */
    List<UserWork> findUserWorkByUserId(String userId);

}
