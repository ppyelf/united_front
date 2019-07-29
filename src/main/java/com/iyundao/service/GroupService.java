package com.iyundao.service;

import com.iyundao.entity.Group;

import java.util.List;

/**
 * @ClassName: GroupService
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/27 11:06
 * @Description: 服务 - 支部
 * @Version: V2.0
 */
public interface GroupService {

    /**
     * 根据机构ID获取小组集合
     * @param subjectId
     * @return
     */
    List<Group> findBySubjectId(String subjectId);

    /**
     * 获取小组集合
     * @return
     */
    List<Group> getList();

    /**
     * 根据id获取实体
     * @param id
     * @return
     */
    Group findById(String id);

    /**
     * 保存实体
     * @param groups
     * @return
     */
    Group save(Group groups);

    /**
     * 根据IDS获取集合信息
     * @param groupIds
     * @return
     */
    List<Group> findbyIds(String[] groupIds);

    List<Group> saveAll(List<Group> groups);

    /**
     * 获取未分配的组织列表
     * @return
     */
    List<Group> findSubjectIsNull();

    /**
     * 获取子集小组
     * @param id
     * @return
     */
    List<Group> findByFatherId(String id);

    /**
     * 获取没有父级的机构列表
     * @return
     */
    List<Group> getListByFatherIsNull();

    /**
     * 根据subjectId获取所有父级实体集合
     * @param subjectId
     * @return
     */
    List<Group> findBySubjectIdAndFatherIsNull(String subjectId);

    /**
     * 检测编号是否存在
     * @param code
     * @return
     */
    boolean existsCode(String code);

    /**
     * 根据编号查询实体
     */
    Group findByCode(String code);

    /**
     * 查找部门集合
     * @param groupIds
     * @return
     */
    List<Group> findByIds(String[] groupIds);

}
