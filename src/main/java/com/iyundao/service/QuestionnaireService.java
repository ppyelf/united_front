package com.iyundao.service;

import com.alibaba.fastjson.JSONObject;
import com.iyundao.entity.Questionnaire;
import com.iyundao.entity.QuestionnaireScore;
import com.iyundao.entity.QuestionnaireTitle;
import com.iyundao.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionnaireService
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description: 调查研究
 * @Version: V1.0
 */
public interface QuestionnaireService {

    /**
     * 添加调查研究实体
     * @param title
     * @param content
     * @param titleAnswer
     * @return
     */
    Questionnaire saveQuestionnaire(String title, String content, List<Map<String, Object>> titleAnswer);



    /**
     * 删除实体
     * @param questionnaire
     */
    void delete(List<Questionnaire> questionnaire);

    /**
     * 根据ids找到实体
     * @param questionnaireIds
     * @return
     */
    List<Questionnaire> findByIds(String[] questionnaireIds);

    /**
     * 找到所有问卷调查列表
     * @return
     */
    List<Questionnaire> findAll();

    /**
     * 根据id查找实体
     * @param questionnaireId
     * @return
     */
    Questionnaire findById(String questionnaireId);

    /**
     * 根据问卷调查实体查找所有题目
     * @param questionnaire
     * @return
     */
    List<QuestionnaireTitle> findTitleByQuestionnaire(Questionnaire questionnaire);

    /**
     * 查看详情
     * @param questionnaire
     * @param titles
     */
    JSONObject findView(Questionnaire questionnaire, List<QuestionnaireTitle> titles);


    /**
     * 添加问卷调查详情
     * @param questionnaire
     * @param answerAll
     */
    QuestionnaireScore saveQuestionnaireUser(Questionnaire questionnaire, User user, List<Map<String, String>> answerAll);

    /**
     * 根据问卷调查实体找到所有分数
     * @param questionnaire
     * @return
     */
    List<QuestionnaireScore> findScoreByQuestionnaire(Questionnaire questionnaire);

    /**
     * 根据用户实体查找所有分数
     * @param user
     * @return
     */
    List<QuestionnaireScore> findScoreByUser(User user);

    /**
     * 根据类型判断排行的规矩，返回实体
     * @param type
     * @return
     */
    List<Map<String,Object>> findAllByType(int type);
}
