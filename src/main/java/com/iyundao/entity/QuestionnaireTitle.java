package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: QuestionnaireTitle
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:    问卷调查题目
 * @Version: V1.0
 */
@Entity
@Table(name = "t_questionnaire_title")
public class QuestionnaireTitle extends BaseEntity<String> {

    private static final long serialVersionUID = -6799240512625337936L;

    /**
     * 题目内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 本题分数
     */
    @Column(name = "SCORE")
    private  String score;


    /**
     * 问卷调查
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIREID")
    private Questionnaire questionnaire;



    /**
     * 答案
     * @return
     */
    @OneToMany(mappedBy = "questionnaireTitle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionnaireAnswer> questionnaireAnswers;


    /**
     * 问卷调查人员记录
     * @return
     */
    @OneToMany(mappedBy = "questionnaireTitle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionnaireUser> questionnaireUsers;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Set<QuestionnaireAnswer> getQuestionnaireAnswers() {
        return questionnaireAnswers;
    }

    public void setQuestionnaireAnswers(Set<QuestionnaireAnswer> questionnaireAnswers) {
        this.questionnaireAnswers = questionnaireAnswers;
    }

    public Set<QuestionnaireUser> getQuestionnaireUsers() {
        return questionnaireUsers;
    }

    public void setQuestionnaireUsers(Set<QuestionnaireUser> questionnaireUsers) {
        this.questionnaireUsers = questionnaireUsers;
    }
}
