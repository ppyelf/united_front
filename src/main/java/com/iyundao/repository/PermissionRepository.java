package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Permission;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: PermissionRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/26 8:58
 * @Description: 仓库 - 权限
 * @Version: V2.0
 */
@Repository
public interface PermissionRepository extends BaseRepository<Permission, String> {

}
