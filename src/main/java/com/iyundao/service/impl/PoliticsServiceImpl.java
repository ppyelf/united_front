package com.iyundao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.repository.*;
import com.iyundao.service.PoliticsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PoliticsServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PoliticsServiceImpl implements PoliticsService {

    @Autowired
    private PoliticsRepository politicsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PoliticsDeptionRepository politicsDeptionRepository;

    @Autowired
    private PoliticsUserRepository politicsUserRepository;

    @Autowired
    private PoliticsIssueDataRepository politicsIssueDataRepository;

    @Autowired
    private PoliticsDiscussDataRepository politicsDiscussDataRepository;

    @Autowired
    private PoliticsResolutionDataRepository politicsResolutionDataRepository;

    @Override
    public Politics add(String title, String content, String startTime, String endTime, int state) {
        Politics politics = new Politics();
        politics.setCreatedDate(new Date());
        politics.setLastModifiedDate(new Date());
        politics.setTitle(title);
        politics.setContent(content);
        politics.setStartTime(startTime);
        politics.setEndTime(endTime);
        for (Politics.POLITICS_TYPE type : Politics.POLITICS_TYPE.values()){
            if (type.ordinal() == state){
                politics.setState(type);
            }
        }
        return politicsRepository.save(politics);
    }

    @Override
    public List<Politics> findAll() {
        return politicsRepository.findAll();
    }

    @Override
    public void addDeptionAndPeople(Politics politics, List<Subject> subjects, List<Depart> departs, List<Group> groups, List<User> users) {
            PoliticsDeption pd ;
        if (CollectionUtils.isNotEmpty(subjects)){
            for (Subject subject : subjects) {
                pd = new PoliticsDeption();
                pd.setCreatedDate(new Date());
                pd.setLastModifiedDate(new Date());
                pd.setPolitics(politics);
                pd.setSubject(subject);
                politicsDeptionRepository.save(pd);
                List<User> us = userRepository.findBySubjectIdForPage(subject.getId());
                userSaveAll(us,politics);
            }
        }
        if (CollectionUtils.isNotEmpty(departs)){
            for (Depart depart : departs) {
                pd = new PoliticsDeption();
                pd.setCreatedDate(new Date());
                pd.setLastModifiedDate(new Date());
                pd.setPolitics(politics);
                pd.setDepart(depart);
                politicsDeptionRepository.save(pd);
                List<User> us = userRepository.findByDepartIdForPage(depart.getId());
                userSaveAll(us,politics);
            }
        }
        if (CollectionUtils.isNotEmpty(groups)){
            for (Group group : groups) {
                pd = new PoliticsDeption();
                pd.setCreatedDate(new Date());
                pd.setLastModifiedDate(new Date());
                pd.setPolitics(politics);
                pd.setGroup(group);
                politicsDeptionRepository.save(pd);
                List<User> us = userRepository.findByGroupIdForPage(group.getId());
                userSaveAll(us,politics);
            }
        }
        if (CollectionUtils.isNotEmpty(users)){
            userSaveAll(users,politics);
        }
    }

    @Override
    public Politics findPoliticsById(String politicsId) {

        return politicsRepository.find(politicsId);
    }

    @Override
    public void saveAllPoliticsIssueData(Politics politics, List<Map<String, String>> politicsIssueData) {
        PoliticsIssueData pid;
        for (Map<String, String> map : politicsIssueData) {
            pid = new PoliticsIssueData();
            pid.setCreatedDate(new Date());
            pid.setLastModifiedDate(new Date());
            pid.setPolitics(politics);
            pid.setTitle(map.get("title"));
            pid.setContent(map.get("content"));
            pid.setSort(Integer.parseInt(map.get("sort")));
            politicsIssueDataRepository.save(pid);
        }
    }

    @Override
    public void saveAllPoliticsDiscussData(Politics politics, List<Map<String, String>> politicsDiscussData) {
        PoliticsDiscussData pdd;
        for (Map<String, String> map : politicsDiscussData) {
            pdd = new PoliticsDiscussData();
            pdd.setCreatedDate(new Date());
            pdd.setLastModifiedDate(new Date());
            pdd.setPolitics(politics);
            pdd.setPoliticsIssueData(politicsIssueDataRepository.find(map.get("politicsIssueDataId")));
            pdd.setUser(userRepository.find(map.get("userId")));
            pdd.setContent(map.get("content"));
            politicsDiscussDataRepository.save(pdd);
        }
    }

    @Override
    public void saveAllPoliticsResolutionData(Politics politics, List<Map<String, String>> politicsResolutionData) {
        PoliticsResolutionData prd;
        for (Map<String, String> map : politicsResolutionData) {
            prd = new PoliticsResolutionData();
            prd.setCreatedDate(new Date());
            prd.setLastModifiedDate(new Date());
            prd.setPolitics(politics);
            prd.setPoliticsIssueData(politicsIssueDataRepository.find(map.get("politicsIssueDataId")));
            prd.setContent(map.get("content"));
            politicsResolutionDataRepository.save(prd);
        }
    }

    @Override
    public List<PoliticsDeption> findDeptionByPolitics(Politics politics) {
        return politicsDeptionRepository.findDeptionByPolitics(politics);
    }

    @Override
    public List<PoliticsDiscussData> findDiscussDataByPolitics(Politics politics) {
        return politicsDiscussDataRepository.findDiscussDataByPolitics(politics);
    }

    @Override
    public List<PoliticsIssueData> findIssueDataByPolitics(Politics politics) {

        return politicsIssueDataRepository.findIssueDataByPolitics(politics);
    }

    @Override
    public JSONObject selectByIssueDataAddDeption(Politics politics,List<PoliticsDeption> politicsDeptions, List<PoliticsIssueData> politicsIssueData) {
        JSONObject obj = new JSONObject();
        JSONArray arr  = new JSONArray();
        obj.put("politics", JsonUtils.getJson(politics));
        if (CollectionUtils.isNotEmpty(politicsIssueData)){
            //遍历拿到讨论数据和决议数据
            for (PoliticsIssueData pid : politicsIssueData) {
                JSONObject object = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                object.put("issueData",JsonUtils.getJson(pid));
                List<PoliticsDiscussData> pdd = politicsDiscussDataRepository.findByIssueData(pid);
                    if (CollectionUtils.isNotEmpty(pdd)){
                        for (PoliticsDiscussData discussData : pdd) {
                            jsonArray.add(JsonUtils.getJson(discussData));
                        }
                    }
                object.put("discussData",jsonArray);
                PoliticsResolutionData prd = politicsResolutionDataRepository.findByIssueData(pid);
                object.put("resolutionData",JsonUtils.getJson(prd));
                arr.add(object);
            }
        }
        obj.put("all",arr);
        return obj;
    }

    @Override
    public void delete(Politics politics) {
        politicsRepository.delete(politics);
    }

    @Override
    public List<PoliticsIssueData> findIssueDataByIssueDataId(String[] issueDataIds) {
        return politicsIssueDataRepository.findByIds(issueDataIds);
    }

    @Override
    public void deleteIssueDatas(List<PoliticsIssueData> politicsIssueData) {
        for (PoliticsIssueData politicsIssueDatum : politicsIssueData) {
            politicsDiscussDataRepository.deleteByIssueDataId(politicsIssueDatum.getId());
            politicsResolutionDataRepository.deleteByIssueDataId(politicsIssueDatum.getId());
            politicsIssueDataRepository.deleteByIssueDataId(politicsIssueDatum.getId());
        }
    }


    /**
     * 添加参与人员
     * @param us
     * @param politics
     */
    private void userSaveAll(List<User> us, Politics politics) {
        PoliticsUser pu;
        for (User u : us) {
            pu = new PoliticsUser();
            pu.setCreatedDate(new Date());
            pu.setLastModifiedDate(new Date());
            pu.setPolitics(politics);
            pu.setUser(u);
            politicsUserRepository.save(pu);
        }
    }
}
