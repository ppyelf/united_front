package com.iyundao.service;

import com.iyundao.entity.Permission;

import java.util.List;

/**
 * @ClassName: PermissionService
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/8 9:07
 * @Description: 服务 - 权限
 * @Version: V2.0
 */
public interface PermissionService {

    /**
     * 根据ID集合查询实体集合
     * @param permissionIds
     * @return
     */
    List<Permission> findByIds(String[] permissionIds);
}
