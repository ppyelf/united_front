package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: QuestionnaireUser
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:    问卷调查人员记录
 * @Version: V1.0
 */
@Entity
@Table(name = "t_questionnaire_user")
public class QuestionnaireUser extends BaseEntity<String> {

    private static final long serialVersionUID = 4931215429606814655L;

    /**
     * 答案选择内容
     */
    @Column(name = "ANSWERID")
    private String answerId;


    /**
     * 是否正确
     */
    @Column(name = "ISTRUE")
    private String istrue;

    /**
     * 问卷调查
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIREID")
    private Questionnaire questionnaire;


    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;


    /**
     * 题目
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRETITLEID")
    private QuestionnaireTitle questionnaireTitle;


    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getIstrue() {
        return istrue;
    }

    public void setIstrue(String istrue) {
        this.istrue = istrue;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionnaireTitle getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(QuestionnaireTitle questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }
}
