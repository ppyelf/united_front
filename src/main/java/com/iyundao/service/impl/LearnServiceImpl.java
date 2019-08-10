package com.iyundao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.utils.FileUtils;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.repository.LearnDetailsRepository;
import com.iyundao.repository.LearnFileRepository;
import com.iyundao.repository.LearnParticipationRepository;
import com.iyundao.repository.LearnRepository;
import com.iyundao.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LearnServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LearnServiceImpl implements LearnService{

    @Value("${server.upload}")
    private String uploadPath;

    @Autowired
    private LearnRepository learnRepository;

    @Autowired
    private LearnFileRepository learnFileRepository;

    @Autowired
    private LearnDetailsRepository learnDetailsRepository;

    @Autowired
    private LearnParticipationRepository learnParticipationRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private DepartService departService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;





    @Override
    public JsonResult saveAll(MultipartFile file, String title, String content, String[] subjects, String[] departs, String[] groups, String[] users) {
        //先存入学习实体
        Learn learn = new Learn();
        learn.setCreatedDate(new Date());
        learn.setLastModifiedDate(new Date());
        learn.setTitle(title);
        learn.setContent(content);
        learn = learnRepository.save(learn);
        //存入学习相关文件
        LearnFile learnFile = new LearnFile();
        learnFile.setCreatedDate(new Date());
        learnFile.setLastModifiedDate(new Date());
        learnFile.setLearn(learn);
        Map<String,String> map = FileUtils.uploadFile(file,learnFile,uploadPath);
        if (map == null){
            return JsonResult.failure(601,"上传失败");
        }
        learnFile.setUrl(map.get("url"));
        learnFile.setSuffix(map.get("suffix"));
        learnFile.setName(map.get("name"));
        learnFileRepository.save(learnFile);
        //存入参与部门
        if (CollectionUtils.isNotEmpty(subjectService.findbyIds(subjects))){
            saveDeption(0,subjectService.findbyIds(subjects),null,null,null,learn);
        }
        if (CollectionUtils.isNotEmpty(departService.findbyIds(departs))){
            saveDeption(1,null,departService.findbyIds(departs),null,null,learn);
        }
        if (CollectionUtils.isNotEmpty(groupService.findbyIds(groups))){
            saveDeption(2,null,null,groupService.findbyIds(groups),null,learn);
        }
        if (CollectionUtils.isNotEmpty(userService.findbyIds(users))){
            saveDeption(3,null,null,null,userService.findbyIds(users),learn);
        }
        //存入参与用户
        if (subjects.length>0 &&subjects!=null){
            for(int i= 0;i<subjects.length;i++){
                List<User> us = userService.findBySubjectIdForPage(subjects[i]);
                saveUser(us,learn);
            }
        }
        if (departs.length>0 && departs!=null){
            for (int j = 0;j<departs.length;j++){
                List<User> us = userService.findByDepartIdForPage(departs[j]);
                saveUser(us,learn);
            }
        }
        if (groups.length>0 && groups!=null){
            for (int k = 0;k<groups.length;k++){
                List<User> us = userService.findByGroupIdForPage(groups[k]);
                saveUser(us,learn);
            }
        }
        if (users.length>0 && users!=null){
                List<User> us = userService.findbyIds(users);
                saveUser(us,learn);
        }
        JsonResult jsonResult = JsonResult.success();
        jsonResult.setData(JsonUtils.getJson(learn));
        return jsonResult;
    }

    @Override
    public List<Learn> findAll() {
        return learnRepository.findAllDesc();
    }

    @Override
    public Learn findById(String learnId) {
        return learnRepository.find(learnId);
    }

    @Override
    public JSONObject findView(Learn learn) {
        List<LearnFile> learnFileList =learnFileRepository.findByLearn(learn);
        List<LearnDetails> learnDetailsList = learnDetailsRepository.findByLearn(learn);
        List<LearnParticipation> learnParticipationList = learnParticipationRepository.findByLearn(learn);
        JSONObject obj = new JSONObject();
        JSONArray arrayFile = new JSONArray();
        JSONArray arrayDetails = new JSONArray();
        JSONObject object;
        JSONArray arrayParticipation = new JSONArray();
        if (CollectionUtils.isNotEmpty(learnFileList)){
            for (LearnFile learnFile : learnFileList) {
                arrayFile.add(JsonUtils.getJson(learnFile));
            }
        }
        if (CollectionUtils.isNotEmpty(learnDetailsList)){
            for (LearnDetails learnDetails : learnDetailsList) {
                object= new JSONObject();
                object.put("state",learnDetails.getState());
                object.put("id",learnDetails.getId());
                object.put("userId",learnDetails.getUser().getId());
                object.put("userName",learnDetails.getUser().getName());
                arrayDetails.add(object);
            }
        }
        if (CollectionUtils.isNotEmpty(learnParticipationList)){
            for (LearnParticipation learnParticipation : learnParticipationList) {
                object = new JSONObject();
                object.put("id",learnParticipation.getId());
                if (learnParticipation.getSubject()!=null){
                    object.put("subecjtId",learnParticipation.getSubject().getId());
                    object.put("subecjtName",learnParticipation.getSubject().getName());
                }
                if (learnParticipation.getDepart()!=null){
                    object.put("departId",learnParticipation.getDepart().getId());
                    object.put("departName",learnParticipation.getDepart().getName());
                }
                if (learnParticipation.getGroup()!=null){
                    object.put("groupId",learnParticipation.getGroup().getId());
                    object.put("groupName",learnParticipation.getGroup().getName());
                }
                if (learnParticipation.getUser()!=null){
                    object.put("userId",learnParticipation.getUser().getId());
                    object.put("userName",learnParticipation.getUser().getName());
                }
                arrayParticipation.add(object);
            }
        }
        obj.put("learn",JsonUtils.getJson(learn));
        obj.put("learnFile",arrayFile);
        obj.put("learnDetails",arrayDetails);
        obj.put("learnParticipation",arrayParticipation);

        return obj;

    }

    @Override
    public void delLearn(Learn learn) {
        learnRepository.delete(learn);
    }


    /**
     * 添加部门
     * @param i
     * @param subjects
     * @param departs
     * @param groups
     * @param users
     * @param learn
     */
    private void saveDeption(int i, List<Subject> subjects, List<Depart> departs, List<Group> groups, List<User> users, Learn learn) {
            switch (i){
                case 0:
                    for (Subject subject : subjects) {
                        LearnParticipation learnParticipation = new LearnParticipation();
                        learnParticipation.setCreatedDate(new Date());
                        learnParticipation.setLastModifiedDate(new Date());
                        learnParticipation.setLearn(learn);
                        learnParticipation.setSubject(subject);
                        learnParticipationRepository.save(learnParticipation);
                    }
                    break;
                case 1:
                    for (Depart depart : departs) {
                        LearnParticipation learnParticipation = new LearnParticipation();
                        learnParticipation.setCreatedDate(new Date());
                        learnParticipation.setLastModifiedDate(new Date());
                        learnParticipation.setLearn(learn);
                        learnParticipation.setDepart(depart);
                        learnParticipationRepository.save(learnParticipation);
                    }
                    break;
                 case 2:
                     for (Group group : groups) {
                         LearnParticipation learnParticipation = new LearnParticipation();
                         learnParticipation.setCreatedDate(new Date());
                         learnParticipation.setLastModifiedDate(new Date());
                         learnParticipation.setLearn(learn);
                         learnParticipation.setGroup(group);
                         learnParticipationRepository.save(learnParticipation);
                     }
                     break;
                case 3:
                    for (User user : users) {
                        LearnParticipation learnParticipation = new LearnParticipation();
                        learnParticipation.setCreatedDate(new Date());
                        learnParticipation.setLastModifiedDate(new Date());
                        learnParticipation.setLearn(learn);
                        learnParticipation.setUser(user);
                        learnParticipationRepository.save(learnParticipation);
                    }
                    break;

                 default:
                     break;
            }
    }

    /**
     * 添加用户详情
     * @param us
     * @param learn
     */
    private void saveUser(List<User> us, Learn learn) {
        for (User user : us) {
            LearnDetails learnDetails = new LearnDetails();
            learnDetails.setCreatedDate(new Date());
            learnDetails.setLastModifiedDate(new Date());
            learnDetails.setUser(user);
            learnDetails.setLearn(learn);
            for (LearnDetails.LEARNDETAILS_TYPE type : LearnDetails.LEARNDETAILS_TYPE.values()){
                if (type.ordinal()==0){
                    learnDetails.setState(type);
                }
            }
            learnDetailsRepository.save(learnDetails);
            
        }
    }
}
