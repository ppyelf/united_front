package com.iyundao.service.impl;

import com.iyundao.entity.Subject;
import com.iyundao.entity.User;
import com.iyundao.entity.UserRelation;
import com.iyundao.repository.DepartRepository;
import com.iyundao.repository.GroupRepository;
import com.iyundao.repository.SubjectRepository;
import com.iyundao.repository.UserRelationRepository;
import com.iyundao.service.UserRelationService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserRelationServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/20 14:44
 * @Description: 服务实现 - 机构关系
 * @Version: V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRelationServiceImpl implements UserRelationService {

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<UserRelation> findByUser(User user) {
        return userRelationRepository.findByUser(user);
    }

    @Override
    public List<UserRelation> findByUserId(String id) {
        return userRelationRepository.findByUserId(id);
    }

    @Override
    public UserRelation findByUserIdAndSubject(User user, String subject) {
        Subject s = subjectRepository.find(subject);
        return userRelationRepository.findByUserIdAndSubject(user.getId(), s.getId());
    }

    @Override
    public List<UserRelation> getAll() {
        return userRelationRepository.getAll();
    }

    @Override
    public List<UserRelation> findBySubjectAndDepartOrGroup(String subjectId, String departId, String groupsId) {
        List<UserRelation> userRelations = userRelationRepository.findBySubjectAndDepartOrGroup(subjectId, departId, groupsId);
        return CollectionUtils.isEmpty(userRelations)
                ? new ArrayList<>()
                : userRelations;
    }

    @Override
    public List<UserRelation> findBySubjectAndDepartIdsOrGroupIds(String subjectId, String[] departIds, String[] groupsIds) {
        List<UserRelation> userRelations = userRelationRepository.findBySubjectAndDepartIdsOrGroupIds(subjectId, departIds, groupsIds);
        return CollectionUtils.isEmpty(userRelations)
                ? new ArrayList<>()
                : userRelations;
    }

    @Override
    public UserRelation findByUserIdAndDepartIdOrGroupId(String userId, String departId, String groupsId) {
        return userRelationRepository.findByUserIdAndDepartIdOrGroupId(userId, departId, groupsId);
    }

    @Override
    public List<UserRelation> findByUserIds(String[] userIds) {
        return userRelationRepository.findByUserIds(userIds);
    }

    @Override
    public List<UserRelation> findByDepartIdOrGroupIdAndUserIds(String departId, String groupId, String[] userIds) {
        return userRelationRepository.findByDepartIdOrGroupIdAndUserIds(departId, groupId, userIds);
    }
}
