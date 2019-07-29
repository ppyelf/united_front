package com.iyundao.service.impl;

import com.iyundao.entity.Role;
import com.iyundao.repository.RoleRepository;
import com.iyundao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/23 23:42
 * @Description: 实现 - 角色
 * @Version: V2.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getList() {
        return roleRepository.getList();
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findByRoleId(id);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findByRoleId(String roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public List<Role> findByRoleIds(String[] roleIds) {
        return roleRepository.findByRoleIds(roleIds);
    }
}
