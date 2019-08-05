package com.iyundao.entity;

import com.iyundao.base.BaseEntity;
import com.iyundao.base.annotation.Excel;

import javax.persistence.*;

/**
 * @ClassName: EvaluationSpeech
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:    言论评价
 * @Version: V1.0
 */
@Entity
@Table(name = "t_evaluation_speech")
public class EvaluationSpeech extends BaseEntity<String>{

    private static final long serialVersionUID = -6531422955429512341L;


    /**
     * 评价详情
     */
    @Excel(name = "评价详情", sort = 0)
    @Column(name = "CONTENT",length = 200)
    private String content;

    /**
     * 评价时间
     */
    @Excel(name = "评价时间", sort = 1)
    @Column(name = "DATA")
    private String data;

    /**
     * 评价人
     */
    @Excel(name = "评价人", sort = 2)
   @Column(name = "USERCODE")
   private String userCode;


    /**
     * 文章code
     */
    @Excel(name = "评价详情", sort = 3)
    @Column(name = "SPEECHARTICLECODE")
    private String speechArticleCode;

    /**
     * 参与讨论信息code
     */
    @Excel(name = "评价详情", sort = 4)
    @Column(name = "SPEECHDISCUSSIONCODE")
    private String speechDiscussionCode;


    /**
     *理论研究code
     */
    @Excel(name = "评价详情", sort = 5)
    @Column(name = "SPEECHSTUDYCODE")
    private String speechStudyCode;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSpeechArticleCode() {
        return speechArticleCode;
    }

    public void setSpeechArticleCode(String speechArticleCode) {
        this.speechArticleCode = speechArticleCode;
    }

    public String getSpeechDiscussionCode() {
        return speechDiscussionCode;
    }

    public void setSpeechDiscussionCode(String speechDiscussionCode) {
        this.speechDiscussionCode = speechDiscussionCode;
    }

    public String getSpeechStudyCode() {
        return speechStudyCode;
    }

    public void setSpeechStudyCode(String speechStudyCode) {
        this.speechStudyCode = speechStudyCode;
    }
}
