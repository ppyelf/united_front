package com.iyundao.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.base.utils.TimeUtils;
import com.iyundao.entity.*;
import com.iyundao.repository.*;
import com.iyundao.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionnaireServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:    调查研究
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionnaireTitleRepository questionnaireTitleRepository;

    @Autowired
    private QuestionnaireAnswerRepository questionnaireAnswerRepository;

    @Autowired
    private QuestionnaireUserRepository questionnaireUserRepository;

    @Autowired
    private QuestionnaireScoreRepository questionnaireScoreRepository;

    @Override
    public Questionnaire saveQuestionnaire(String title, String content, List<Map<String, Object>> titleAnswer) {
        //先放入调查研究实体
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setCreatedDate(new Date());
        questionnaire.setLastModifiedDate(new Date());
        questionnaire.setTitle(title);
        questionnaire.setContent(content);
        questionnaire = questionnaireRepository.save(questionnaire);
        //再放入题目和答案
        QuestionnaireTitle questionnaireTitle;
        QuestionnaireAnswer questionnaireAnswer;
        for (Map<String, Object> map : titleAnswer) {
            questionnaireTitle = new QuestionnaireTitle();
            questionnaireTitle.setCreatedDate(new Date());
            questionnaireTitle.setLastModifiedDate(new Date());
            questionnaireTitle.setContent(map.get("title").toString());
            questionnaireTitle.setScore(map.get("score").toString());
            questionnaireTitle.setQuestionnaire(questionnaire);
            QuestionnaireTitle qt = questionnaireTitleRepository.save(questionnaireTitle);
            List<Map<String,String>> answerAll =(List<Map<String,String>>)map.get("answerAll");
            for (Map<String, String> stringMap : answerAll) {
                questionnaireAnswer = new QuestionnaireAnswer();
                questionnaireAnswer.setCreatedDate(new Date());
                questionnaireAnswer.setLastModifiedDate(new Date());
                questionnaireAnswer.setContent(stringMap.get("content"));
                questionnaireAnswer.setIstrue(stringMap.get("istrue"));
                questionnaireAnswer.setQuestionnaireTitle(qt);
                questionnaireAnswerRepository.save(questionnaireAnswer);
            }
        }

        return questionnaire;
    }

    @Override
    public List<Questionnaire> findByIds(String[] questionnaireIds) {
        return questionnaireRepository.findByIds(questionnaireIds);
    }

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Questionnaire findById(String questionnaireId) {

        return questionnaireRepository.find(questionnaireId);
    }

    @Override
    public List<QuestionnaireTitle> findTitleByQuestionnaire(Questionnaire questionnaire) {
        return questionnaireTitleRepository.findTitleByQuestionnaire(questionnaire);
    }

    @Override
    public JSONObject findView(Questionnaire questionnaire, List<QuestionnaireTitle> titles) {
        JSONObject obj = new JSONObject();
        obj.put("questionnaire", JsonUtils.getJson(questionnaire));
        JSONArray arr = new JSONArray();
        for (QuestionnaireTitle title : titles) {
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            object.put("title",title.getContent());
            object.put("score",title.getScore());
            List<QuestionnaireAnswer> questionnaireAnswer = questionnaireAnswerRepository.findByTitle(title);
            for (QuestionnaireAnswer answer : questionnaireAnswer) {
                JSONObject answerAll = new JSONObject();
                answerAll.put("answer",JsonUtils.getJson(answer));
                array.add(answerAll);
            }
            object.put("answerAll",array);
            arr.add(object);
        }
        obj.put("titleAnswer",arr);
        return obj;
    }

    @Override
    public QuestionnaireScore saveQuestionnaireUser(Questionnaire questionnaire, User user, List<Map<String, String>> answerAll) {
        QuestionnaireUser questionnaireUser ;
        int number =0;
        for (Map<String, String> map : answerAll) {
            String answerId = map.get("answerId");
            QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerRepository.find(answerId);
            QuestionnaireTitle questionnaireTitle = questionnaireTitleRepository.find(map.get("titleId"));
            questionnaireUser = new QuestionnaireUser();
            questionnaireUser.setCreatedDate(new Date());
            questionnaireUser.setLastModifiedDate(new Date());
            questionnaireUser.setIstrue(questionnaireAnswer.getIstrue());
            if ("1".equals(questionnaireAnswer.getIstrue())){
                number+=Integer.parseInt(questionnaireTitle.getScore());
            }
            questionnaireUser.setUser(user);
            questionnaireUser.setAnswerId(answerId);
            questionnaireUser.setQuestionnaire(questionnaire);
            questionnaireUser.setQuestionnaireTitle(questionnaireTitleRepository.find(map.get("titleId")));
            questionnaireUserRepository.save(questionnaireUser);
        }
        QuestionnaireScore questionnaireScore = new QuestionnaireScore();
        questionnaireScore.setCreatedDate(new Date());
        questionnaireScore.setLastModifiedDate(new Date());
        questionnaireScore.setUser(user);
        questionnaireScore.setQuestionnaire(questionnaire);
        questionnaireScore.setScore(number+"");
        questionnaireScore = questionnaireScoreRepository.save(questionnaireScore);
      return questionnaireScore;
    }

    @Override
    public List<QuestionnaireScore> findScoreByQuestionnaire(Questionnaire questionnaire) {
        return questionnaireScoreRepository.findScoreByQuestionnaire(questionnaire);
    }

    @Override
    public List<QuestionnaireScore> findScoreByUser(User user) {
        return questionnaireScoreRepository.findScoreByUser(user);
    }

    @Override
    public List<Map<String,Object>> findAllByType(int type) {
        String startTime = convertTime(type, true);
        String endTime = convertTime(type, false);
        List<Map<String,Object>>  map =questionnaireScoreRepository.findAllByType(startTime,endTime);
        return map;
    }

    @Override
    public void delete(List<Questionnaire> questionnaire) {
            questionnaireRepository.deleteAll(questionnaire);
    }


    private String convertTime(int type, boolean start) {
        switch (type) {
            case  0://今日
                return start
                        ? TimeUtils.convertTime(TimeUtils.getDayBegin(), TimeUtils.yyyyMMddHHmmss)
                        : TimeUtils.convertTime(TimeUtils.getDayEnd(), TimeUtils.yyyyMMddHHmmss);
            case  1://本周
                return start
                        ? TimeUtils.convertTime(TimeUtils.getBeginDayOfWeek(), TimeUtils.yyyyMMddHHmmss)
                        : TimeUtils.convertTime(TimeUtils.getEndDayOfWeek(), TimeUtils.yyyyMMddHHmmss);
            case  2://本月
                return start
                        ? TimeUtils.convertTime(TimeUtils.getBeginDayOfMonth(), TimeUtils.yyyyMMddHHmmss)
                        : TimeUtils.convertTime(TimeUtils.getEndDayOfMonth(), TimeUtils.yyyyMMddHHmmss);
            case  3://本年
                return start
                        ? TimeUtils.convertTime(TimeUtils.getBeginDayOfYear(), TimeUtils.yyyyMMddHHmmss)
                        : TimeUtils.convertTime(TimeUtils.getEndDayOfYear(), TimeUtils.yyyyMMddHHmmss);
        }
        return "";
    }
}
