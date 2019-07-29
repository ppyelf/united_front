package com.iyundao.service.impl;

import com.iyundao.entity.Permission;
import com.iyundao.repository.PermissionRepository;
import com.iyundao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PermissionServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/8 9:07
 * @Description: 实现 - 权限
 * @Version: V2.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupsService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findByIds(String[] permissionIds) {
        return permissionRepository.findByIds(permissionIds);
    }
}
