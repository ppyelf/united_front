package com.iyundao.service.impl;

import com.iyundao.entity.Group;
import com.iyundao.repository.GroupRepository;
import com.iyundao.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: GroupServiceImpl
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/27 11:10
 * @Description: 实现 - 支部
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findBySubjectId(String subjectId) {
        return groupRepository.findBySubjectId(subjectId);
    }

    @Override
    public List<Group> getList() {
        return groupRepository.getList();
    }

    @Override
    public Group findById(String id) {
        return groupRepository.findByGroupId(id);
    }

    @Override
    @Transactional
    public Group save(Group groups) {
        return groupRepository.save(groups);
    }

    @Override
    public List<Group> findbyIds(String[] groupIds) {
        return groupRepository.findByIds(groupIds);
    }

    @Override
    @Transactional
    public List<Group> saveAll(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }

    @Override
    public List<Group> findSubjectIsNull() {
        return groupRepository.findSubjectIsNull();
    }

    @Override
    public List<Group> findByFatherId(String id) {
        return groupRepository.findByFatherId(id);
    }

    @Override
    public List<Group> getListByFatherIsNull() {
        return groupRepository.getListByFatherIsNull();
    }

    @Override
    public List<Group> findBySubjectIdAndFatherIsNull(String subjectId) {
        return groupRepository.findBySubjectIdAndFatherIsNull(subjectId);
    }

    @Override
    public boolean existsCode(String code) {
        Group groups = groupRepository.findByCode(code);
        return groups == null ? false : true;
    }

    @Override
    public Group findByCode(String code) {
        return groupRepository.findByCode(code);
    }

    @Override
    public List<Group> findByIds(String[] groupIds) {
        return groupRepository.findByIds(groupIds);
    }

}
