package com.iyundao.service;

import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: PoliticsService
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
public interface PoliticsService {




    /**
     * 添加参政议政实体
     * @param title
     * @param content
     * @param startTime
     * @param endTime
     * @param state
     * @return
     */
    Politics add(String title, String content, String startTime, String endTime, int state);

    /**
     * 参政议政列表
     * @return
     */
    List<Politics> findAll();

    /**
     * 添加参与人员
     * @param politics
     * @param subjects
     * @param departs
     * @param groups
     * @param users
     */
    void addDeptionAndPeople(Politics politics, List<Subject> subjects, List<Depart> departs, List<Group> groups, List<User> users);

    /**
     * 通过id找到用户实体
     * @param politicsId
     * @return
     */
    Politics findPoliticsById(String politicsId);



    /**
     * 添加议题数据
     * @param politicsIssueData
     */
    void saveAllPoliticsIssueData(Politics politics, List<Map<String, String>> politicsIssueData);

    /**
     * 添加讨论数据
     * @param politics
     * @param politicsDiscussData
     */
    void saveAllPoliticsDiscussData(Politics politics, List<Map<String, String>> politicsDiscussData);

    /**
     * 添加决议数据
     * @param politics
     * @param politicsResolutionData
     */
    void saveAllPoliticsResolutionData(Politics politics, List<Map<String, String>> politicsResolutionData);

    /**
     * 根据参政议政实体找到所有部门
     * @param politics
     */
    List<PoliticsDeption> findDeptionByPolitics(Politics politics);

    /**
     * 根据参政议政实体找到所有讨论数据
     * @param politics
     */
    List<PoliticsDiscussData> findDiscussDataByPolitics(Politics politics);


    /**
     * 根据参政议政实体找到所有议题数据
     * @param politics
     */
    List<PoliticsIssueData> findIssueDataByPolitics(Politics politics);

    /**
     * 查询所有议题相关加上部门
     * @param politicsDeptions
     * @param politicsIssueData
     */
    JSONObject selectByIssueDataAddDeption(Politics politics, List<PoliticsDeption> politicsDeptions, List<PoliticsIssueData> politicsIssueData);

    /**
     * 删除参政议政所有
     * @param politics
     */
    void delete(Politics politics);



    /**
     * 通过议题数据ids找到实体
     * @param issueDataIds
     * @return
     */
    List<PoliticsIssueData> findIssueDataByIssueDataId(String[] issueDataIds);

    /**
     * 删除议题数据
     * @param politicsIssueData
     */
    void deleteIssueDatas(List<PoliticsIssueData> politicsIssueData);
}
