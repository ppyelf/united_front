package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Questionnaire
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:  问卷调查
 * @Version: V1.0
 */
@Entity
@Table(name = "t_questionnaire")
public class Questionnaire extends BaseEntity<String> {

    private static final long serialVersionUID = 8671995095409607560L;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 问卷描述
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 题目
     * @return
     */
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionnaireTitle> questionnaireTitle;

    /**
     * 问卷调查记录
     * @return
     */
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionnaireUser> questionnaireUsers;


    /**
     * 调查问卷得分
     * @return
     */
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<QuestionnaireScore> questionnaireScore;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<QuestionnaireTitle> getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(Set<QuestionnaireTitle> questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }

    public Set<QuestionnaireUser> getQuestionnaireUsers() {
        return questionnaireUsers;
    }

    public void setQuestionnaireUsers(Set<QuestionnaireUser> questionnaireUsers) {
        this.questionnaireUsers = questionnaireUsers;
    }

    public Set<QuestionnaireScore> getQuestionnaireScore() {
        return questionnaireScore;
    }

    public void setQuestionnaireScore(Set<QuestionnaireScore> questionnaireScore) {
        this.questionnaireScore = questionnaireScore;
    }
}
