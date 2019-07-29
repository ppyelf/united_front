package com.iyundao.service.impl;

import com.iyundao.entity.Depart;
import com.iyundao.entity.Group;
import com.iyundao.entity.Subject;
import com.iyundao.repository.SubjectRepository;
import com.iyundao.service.DepartService;
import com.iyundao.service.GroupService;
import com.iyundao.service.SubjectService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: SubjectServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/23 16:29
 * @Description: 服务层--机构
 * @Version: V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupsService;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findList();
    }

    @Override
    public Subject find(String id) {
        return subjectRepository.find(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject saveDepartAndGroup(Subject subject, String[] departIds, String[] groupIds) {
        List<Depart> departs = departService.findByIds(departIds);
        List<Group> groups = groupsService.findbyIds(groupIds);
        if (CollectionUtils.isNotEmpty(departs)) {
            for (Depart depart : departs) {
                depart.setSubject(subject);
            }
            departService.saveAll(departs);
        } 
        
        if (CollectionUtils.isNotEmpty(groups)) {
            for (Group group : groups) {
                group.setSubject(subject);
            }
            groupsService.saveAll(groups);
        }
        Set<Depart> departSet = new HashSet<>();
        departSet.addAll(departs);
        Set<Group> groupSet = new HashSet<>();
        groupSet.addAll(groups);
        subject.setDeparts(departSet);
        subject.setGroups(groupSet);
        subject = subjectRepository.save(subject);
        return subject;
    }

    @Override
    public boolean exists(String id) {
        return subjectRepository.exists(id);
    }

    @Override
    public boolean existsCode(String code) {
        Subject subject = subjectRepository.findByCode(code);
        return subject == null ? false : true;
    }

    @Override
    public Subject findByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    @Override
    public List<Subject> findbyIds(String[] subjectIds) {

        return subjectRepository.findByIds(subjectIds);
    }


}
