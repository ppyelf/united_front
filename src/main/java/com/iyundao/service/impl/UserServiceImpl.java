package com.iyundao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.repository.*;
import com.iyundao.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: UserServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/17 14:07
 * @Description: 服务实现 - 用户
 * @Version: V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private RoleRelationRepository roleRelationRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private UserTrainRepository userTrainRepository;

    @Autowired
    private UserWorkRepository userWorkRepository;

    @Autowired
    private UserLabelRepository userLabelRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User findByAccountAndPassword(String username, String password) {
        return userRepository.findByAccountAndPassword(username, password);
    }

    @Override
    public Page<User> findByKey(Pageable pageable) {
        return userRepository.findPage(pageable);
    }

    @Override
    public User findById(String id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findByUserId(id);
        userRepository.delete(user);
    }

    @Override
    public JsonResult save(User user, Subject subject, String departId, String groupsId, List<Role> roles, List<Permission> permissions, List<Label> labels, JsonResult jsonResult) {
        if (StringUtils.isBlank(departId)
                    || StringUtils.isBlank(groupsId)) {
            JsonResult.failure(601, "用户必须有所属的机构/部门/组织");
        }
        Depart depart = departRepository.findByDepartId(departId);
        Group groups = groupRepository.findByGroupId(groupsId);
        if (subject == null) {
            return JsonResult.failure(604, "机构不存在");
        }
        if (depart == null && groups == null) {
            return JsonResult.failure(603, "部门/组织不存在");
        }
        UserRelation userRelation = new UserRelation();
        userRelation.setCreatedDate(new Date());
        userRelation.setLastModifiedDate(new Date());
        userRelation.setSubject(subject);
        userRelation.setDepart(depart);
        userRelation.setGroup(groups);
        userRelation.setUser(user);
        user = userRepository.save(user);
        if (CollectionUtils.isNotEmpty(roles)) {
            Set<RoleRelation> set = new HashSet<>();
            for (Role role : roles) {
                for (Permission permission : permissions) {
                    RoleRelation ur = new RoleRelation();
                    ur.setCreatedDate(new Date());
                    ur.setLastModifiedDate(new Date());
                    ur.setUser(user);
                    ur.setRole(role);
                    ur.setPermission(permission);
                    ur = roleRelationRepository.save(ur);
                    set.add(ur);
                }
            }
            user.setRoleRelations(set);
        }
        userRelationRepository.save(userRelation);
        Set<UserRelation> userRelations = new HashSet<>();
        userRelations.add(userRelation);
        user.setUserRelations(userRelations);
        //设置用户标签
        Set<UserLabel> userLabels = new HashSet<>();
        for (Label l : labels) {
            UserLabel userLabel = new UserLabel();
            userLabel.setCreatedDate(new Date());
            userLabel.setLastModifiedDate(new Date());
            userLabel.setUser(user);
            userLabel.setLabel(l);
            userLabel = userLabelRepository.save(userLabel);
            userLabels.add(userLabel);
        }
        user.setLabels(userLabels);
        user = userRepository.save(user);
        jsonResult.setData(getUserInfoJson(user));
        return jsonResult;
    }

    @Override
    public JSONObject getUserInfoJson(User user) {
        JSONObject userJson = JsonUtils.getJson(user);
        if (CollectionUtils.isNotEmpty(user.getUserRelations())) {
            JSONArray arr = new JSONArray();
            for (UserRelation ur : user.getUserRelations()) {
                JSONObject json = new JSONObject();
                JSONObject subJson = new JSONObject();
                subJson.put("id", ur.getSubject().getId());
                subJson.put("name", ur.getSubject().getName());
                json.put("subject", subJson);
                if (ur.getDepart() != null) {
                    JSONObject deJson = new JSONObject();
                    deJson.put("id", ur.getDepart().getId());
                    deJson.put("name", ur.getDepart().getName());
                    json.put("depart", deJson);
                }
                if (ur.getGroup() != null) {
                    JSONObject groupJson = new JSONObject();
                    groupJson.put("id", ur.getGroup().getId());
                    groupJson.put("name", ur.getGroup().getName());
                    json.put("groups", groupJson);
                }
                arr.add(json);
            }
            userJson.put("userRelations", arr);
        }
        if (CollectionUtils.isNotEmpty(user.getRoleRelations())) {
            JSONArray arr = new JSONArray();
            for (RoleRelation ur : user.getRoleRelations()) {
                JSONObject json = new JSONObject();
                json.put("id", ur.getRole().getId());
                json.put("name", ur.getRole().getName());
                arr.add(json);
            }
            userJson.put("userRoles", arr);
        }
        return userJson;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> findByGroupIdForPage(String groupId) {
        return userRepository.findByGroupIdForPage(groupId);
    }

    @Override
    public List<User> findByDepartIdForPage(String departId) {
        return userRepository.findByDepartIdForPage(departId);
    }

    @Override
    public boolean existsCode(String code) {
        User user = userRepository.findByCode(code);
        return user == null ? false : true;
    }

    @Override
    public User findByCode(String code) {
        return userRepository.findByCode(code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findbyIds(String[] userids) {
        return userRepository.findByIds(userids);
    }

    @Override
    public List<User> findBySubjectIdForPage(String id) {
        return userRepository.findBySubjectIdForPage(id);
    }

    @Override
    public List<Industry> findByFatherIsNull() {
        return industryRepository.findByFatherIsNull();
    }

    @Override
    public List<Industry> findByFatherId(String id) {
        return industryRepository.findByFatherId(id);
    }

    @Override
    public UserTrain saveUserTrain(String name, String startTime, String endTime, String honor, String remark, User user) {
        UserTrain train = new UserTrain();
        train.setCreatedDate(new Date());
        train.setLastModifiedDate(new Date());
        train.setStartTime(startTime);
        train.setEndTime(endTime);
        train.setHonor(honor);
        train.setName(name);
        train.setRemark(remark);
        train.setUser(user);
        train = userTrainRepository.save(train);
        return train;
    }

    @Override
    public UserTrain findUserTrainById(String id) {
        return userTrainRepository.find(id);
    }

    @Override
    public void deleteUserTrain(UserTrain train) {
        userTrainRepository.delete(train);
    }

    @Override
    public Industry findIndustryById(String industryId) {
        return industryRepository.find(industryId);
    }

    @Override
    public UserWork saveUserWork(String companyName, Industry industry, String positionName, String startTime, String endTime, User user) {
        UserWork work = new UserWork();
        work.setCreatedDate(new Date());
        work.setLastModifiedDate(new Date());
        work.setCompanyName(companyName);
        work.setStartTime(startTime);
        work.setEndTime(endTime);
        work.setIndustry(industry);
        work.setPositionName(positionName);
        work.setUser(user);
        work = userWorkRepository.save(work);
        return work;
    }

    @Override
    public UserWork findUserWorkById(String id) {
        return userWorkRepository.find(id);
    }

    @Override
    public void deleteUserWork(UserWork work) {
        userWorkRepository.delete(work);
    }

    @Override
    public UserInfo saveUserInfo(User user, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        UserInfo info = new UserInfo();
        info.setCreatedDate(new Date());
        info.setLastModifiedDate(new Date());
        info.setUser(user);
        return saveUserInfo(info, name, nation, age, political, nativePlace, university, major, education, birthDay, address, tel, email, wx, qq, iCard, selfEvaluation);
    }

    @Override
    public UserInfo findUserInfoById(String id) {
        return userInfoRepository.find(id);
    }

    @Override
    public UserInfo modifyUserInfo(UserInfo userInfo, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        userInfo.setLastModifiedDate(new Date());
        return saveUserInfo(userInfo, name, nation, age, political, nativePlace, university, major, education, birthDay, address, tel, email, wx, qq, iCard, selfEvaluation);
    }

    @Override
    public UserInfo findUserInfoByUserId(String id) {
        return userInfoRepository.findUserInfoByUserId(id);
    }

    @Override
    public List<UserTrain> findUserTrainByUserId(String id) {
        return userTrainRepository.findUserTrainByUserId(id);
    }

    @Override
    public List<UserWork> findUserWorkByUserId(String userId) {
        return userWorkRepository.findUserWorkByUserId(userId);
    }

    private UserInfo saveUserInfo(UserInfo userInfo, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        userInfo.setName(name);
        userInfo.setNation(nation);
        userInfo.setAddress(address);
        userInfo.setAge(age);
        userInfo.setPolitical(political);
        userInfo.setNativePlace(nativePlace);
        userInfo.setUniversity(university);
        userInfo.setMajor(major);
        userInfo.setEducation(education);
        userInfo.setBirthDay(birthDay);
        userInfo.setTel(tel);
        userInfo.setEmail(email);
        userInfo.setWx(wx);
        userInfo.setQq(qq);
        userInfo.setiCard(iCard);
        userInfo.setSelfEvaluation(selfEvaluation);
        userInfo = userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo saveUserInfo(User user, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        UserInfo info = new UserInfo();
        info.setCreatedDate(new Date());
        info.setLastModifiedDate(new Date());
        info.setUser(user);
        return saveUserInfo(info, name, nation, age, political, nativePlace, university, major, education, birthDay, address, tel, email, wx, qq, iCard, selfEvaluation);
    }

    @Override
    public UserInfo findUserInfoById(String id) {
        return userInfoRepository.find(id);
    }

    @Override
    public UserInfo modifyUserInfo(UserInfo userInfo, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        userInfo.setLastModifiedDate(new Date());
        return saveUserInfo(userInfo, name, nation, age, political, nativePlace, university, major, education, birthDay, address, tel, email, wx, qq, iCard, selfEvaluation);
    }

    private UserInfo saveUserInfo(UserInfo userInfo, String name, String nation, int age, String political, String nativePlace, String university, String major, String education, String birthDay, String address, long tel, String email, String wx, String qq, String iCard, String selfEvaluation) {
        userInfo.setName(name);
        userInfo.setNation(nation);
        userInfo.setAddress(address);
        userInfo.setAge(age);
        userInfo.setPolitical(political);
        userInfo.setNativePlace(nativePlace);
        userInfo.setUniversity(university);
        userInfo.setMajor(major);
        userInfo.setEducation(education);
        userInfo.setBirthDay(birthDay);
        userInfo.setTel(tel);
        userInfo.setEmail(email);
        userInfo.setWx(wx);
        userInfo.setQq(qq);
        userInfo.setiCard(iCard);
        userInfo.setSelfEvaluation(selfEvaluation);
        userInfo = userInfoRepository.save(userInfo);
        return userInfo;
    }
}
