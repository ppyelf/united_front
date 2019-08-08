package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaName;

/**
 * @ClassName: QuestionnaireScore
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_questionnaire_score")
public class QuestionnaireScore extends BaseEntity<String> {

    private static final long serialVersionUID = -6007100128491173325L;

    /**
     * 得分
     */
    @Column(name = "SCORE")
    private  String score;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;


    /**
     * 问卷调查
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRE")
    private Questionnaire questionnaire;




    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}
