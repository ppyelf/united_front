package com.iyundao.service;

import com.iyundao.entity.PositionRelation;

import java.util.List;

/**
 * @ClassName: PositionRealtionService
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/2 16:32
 * @Description: 服务 - 岗位关系
 * @Version: V1.0
 */
public interface PositionRelationService {

    /**
     * 根据岗位id,部门ids,小组ids查询实体集合
     * @param id
     * @param departIds
     * @param groupIds
     * @return
     */
    List<PositionRelation> findByPositionIdAndDepartIdsOrGroupIds(String id, String[] departIds, String[] groupIds);
}
