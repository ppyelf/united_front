package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: QuestionnaireAnswer
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_questionnaire_answer")
public class QuestionnaireAnswer extends BaseEntity<String> {

    private static final long serialVersionUID = 1486254941040414750L;

    /**
     * 题目内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 是否正确
     */
    @Column(name = "ISTRUE")
    private String istrue;

    /**
     * 题目
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTIONNAIRETITLEID")
    private QuestionnaireTitle questionnaireTitle;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIstrue() {
        return istrue;
    }

    public void setIstrue(String istrue) {
        this.istrue = istrue;
    }

    public QuestionnaireTitle getQuestionnaireTitle() {
        return questionnaireTitle;
    }

    public void setQuestionnaireTitle(QuestionnaireTitle questionnaireTitle) {
        this.questionnaireTitle = questionnaireTitle;
    }


}

