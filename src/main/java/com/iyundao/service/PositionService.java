package com.iyundao.service;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.*;

import java.util.List;

/**
 * @ClassName: PositionService
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:15
 * @Description: 服务 - 岗位
 * @Version: V1.0
 */
public interface PositionService {

    /**
     * 创建岗位
     * @param name
     * @param remark
     * @param industry
     * @param subject
     * @param depart
     * @param group
     * @return
     */
    Position create(String name, String remark, Industry industry, Subject subject, Depart depart, Group group);

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<Position> findPage(Pageable pageable);

    /**
     * 根据ID查询实体信息
     * @param id
     * @return
     */
    Position find(String id);

    /**
     * 删除实体
     * @param position
     */
    void delete(Position position);

    /**
     * 添加用户和岗位的关系
     * @param position
     * @param users
     * @return
     */
    List<PositionRelation> addRelation(Position position, List<User> users);

    /**
     * 根据部门ID获取岗位集合信息
     * @param departId
     * @return
     */
    List<Position> findByDepartId(String departId);

    /**
     * 根小组ID获取岗位集合信息
     * @param groupId
     * @return
     */
    List<Position> findByGroupId(String groupId);
}
