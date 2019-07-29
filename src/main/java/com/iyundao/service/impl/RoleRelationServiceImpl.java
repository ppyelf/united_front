package com.iyundao.service.impl;

import com.iyundao.entity.RoleRelation;
import com.iyundao.repository.RoleRelationRepository;
import com.iyundao.service.RoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @ClassName: RoleRelationServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/10 11:27
 * @Description: 实现 - 用户权限
 * @Version: V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleRelationServiceImpl implements RoleRelationService {

    @Autowired
    private RoleRelationRepository repository;

    @Override
    public Set<RoleRelation> findRolesByUserId(String id) {
        return repository.findRolesByUserId(id);
    }
}
