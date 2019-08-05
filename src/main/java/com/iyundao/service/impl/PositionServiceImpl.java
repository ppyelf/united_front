package com.iyundao.service.impl;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.*;
import com.iyundao.repository.PositionRelationRepository;
import com.iyundao.repository.PositionRepository;
import com.iyundao.service.PositionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: PositionServiceImpl
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:15
 * @Description: 实现 - 岗位
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository repository;

    @Autowired
    private PositionRelationRepository positionRelationRepository;

    @Override
    public Position create(String name, String remark, Industry industry, Subject subject, Depart depart, Group group) {
        Position position = new Position();
        position.setCreatedDate(new Date());
        position.setLastModifiedDate(new Date());
        position.setName(name);
        position.setRemark(remark);
        position.setIndustry(industry);
        position.setDepart(depart);
        position.setGroup(group);
        position = repository.save(position);
        return position;
    }


    @Override
    public Page<Position> findPage(Pageable pageable) {
        return repository.findPage(pageable);
    }

    @Override
    public Position find(String id) {
        return repository.find(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Position position) {
        repository.delete(position);
    }

    @Override
    public List<PositionRelation> addRelation(Position position, List<User> users) {
        List<PositionRelation> list = new ArrayList<>();
        for (User user : users) {
            PositionRelation pr = new PositionRelation();
            pr.setCreatedDate(new Date());
            pr.setLastModifiedDate(new Date());
            pr.setUser(user);
            pr.setPosition(position);
            pr = positionRelationRepository.save(pr);
            list.add(pr);
        }
        return list;
    }

    @Override
    public List<Position> findByDepartId(String departId) {
        return repository.findByDepartId(departId);
    }

    @Override
    public List<Position> findByGroupId(String groupId) {
        return repository.findByGroupId(groupId);
    }

}
