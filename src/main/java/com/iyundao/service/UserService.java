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
}
