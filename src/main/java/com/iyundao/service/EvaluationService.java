package com.iyundao.service;

import com.iyundao.entity.*;

import java.util.List;

/**
 * @ClassName: EvaluationService
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description: 评价
 * @Version: V1.0
 */
public interface EvaluationService {

    /**
     *查找所有言论评价
     * @return
     */
    List<EvaluationSpeech> findAllSpeech();

    /**
     * 添加事件评价
     * @param list
     * @return
     */
    List<EvaluationEvent> saveAllEvent(List<EvaluationEvent> list);

    /**
     * 事件评价列表排序
     * @return
     */
    List<EvaluationEvent> findAllEvent();

    /**
     * 根据事件名称模糊查询事件评价
     * @param likeIncidentTitle
     * @return
     */
    List<EvaluationEvent> findAllByLikeIncidentTitle(String likeIncidentTitle);

    /**
     * 添加所有组织评价
     * @param list
     * @return
     */
    List<EvaluationOrganize> saveAllOrganize(List<EvaluationOrganize> list);

    /**
     * 查找所有组织评价
     * @return
     */
    List<EvaluationOrganize> findAllOrganize();

    /**
     * 模糊查询机构名称找到机构评价
     * @param likeName
     * @return
     */
    List<EvaluationOrganize> findAllOrganizeBySubjectName(String likeName);

    /**
     * 模糊查询部门名称找到机构评价
     * @param likeName
     * @return
     */
    List<EvaluationOrganize> findAllOrganizeByDepartName(String likeName);

    /**
     * 模糊查询组织名称找到机构评价
     * @param likeName
     * @return
     */
    List<EvaluationOrganize> findAllOrganizeByGroupName(String likeName);

    /**
     * 添加个人评价
     * @param list
     * @return
     */
    List<EvaluationSelf> saveAllSelf(List<EvaluationSelf> list);

    /**
     * 个人评价分页列表
     * @return
     */
    List<EvaluationSelf> findAllSelf();

    /**
     * 根据用户名字模糊查询列表
     * @param likeName
     * @return
     */
    List<EvaluationSelf> findAllSelfByUserName(String likeName);

    /**
     * 添加言论评价
     * @param list
     * @return
     */
    List<EvaluationSpeech> saveAllSpeech(List<EvaluationSpeech> list);

    /**
     * 模糊查询文章标题查找言论评价实体
     * @param likeName
     * @return
     */
    List<EvaluationSpeech> findAllSpeechByArticleName(String likeName);


    /**
     * 模糊查询讨论信息标题查找言论评价实体
     * @param likeName
     * @return
     */
    List<EvaluationSpeech> findAllSpeechByDiscussionName(String likeName);

    /**
     * 模糊查询理论研究标题查找言论评价实体
     * @param likeName
     * @return
     */
    List<EvaluationSpeech> findAllSpeechByStudyName(String likeName);
}
