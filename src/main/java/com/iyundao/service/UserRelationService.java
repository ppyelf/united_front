package com.iyundao.service;

import com.iyundao.entity.User;
import com.iyundao.entity.UserRelation;

import java.util.List;

/**
 * @ClassName: UserRelationService
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/20 14:43
 * @Description: 服务-机构关系
 * @Version: V2.0
 */
public interface UserRelationService {

    /**
     * 查找用户本人的机构关系
     * @param user
     * @return
     */
    List<UserRelation> findByUser(User user);

    /**
     * 根据ID超找用户机构关系
     * @param id
     * @return
     */
    List<UserRelation> findByUserId(String id);

    /**
     * 根据机构和用户获取菜单等信息
     * @param user
     * @param subject
     * @return
     */
    UserRelation findByUserIdAndSubject(User user, String subject);

    /**
     * 获取所有机构关系
     * @return
     */
    List<UserRelation> getAll();

    /**
     * 根据机构,部门,小组查找用户关系
     * @param subjectId
     * @param departId
     * @param groupsId
     * @return
     */
    List<UserRelation> findBySubjectAndDepartOrGroup(String subjectId, String departId, String groupsId);

    /**
     * 根据机构,部门IDS,小组IDS查找用户关系
     * @param subjectId
     * @param departId
     * @param groupsId
     * @return
     */
    List<UserRelation> findBySubjectAndDepartIdsOrGroupIds(String subjectId, String[] departId, String[] groupsId);

    /**
     * 根据用户ID和所属机构/组织ID查询所属关系
     * @param userId
     * @param departId
     * @param groupsId
     * @return
     */
    UserRelation findByUserIdAndDepartIdOrGroupId(String userId, String departId, String groupsId);

    /**
     * 根据用户ID
     * @param shareUserIds
     * @return
     */
    List<UserRelation> findByUserIds(String[] shareUserIds);
}
