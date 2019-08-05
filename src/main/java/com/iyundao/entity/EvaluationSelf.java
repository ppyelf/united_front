package com.iyundao.entity;

import com.iyundao.base.BaseEntity;
import com.iyundao.base.annotation.Excel;

import javax.persistence.*;

/**
 * @ClassName: EvaluationSelf
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:  个人评价
 * @Version: V1.0
 */
@Entity
@Table(name = "t_evaluation_self")
public class EvaluationSelf extends BaseEntity<String>{

    private static final long serialVersionUID = 8065488098432973833L;

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
    @Column(name = "EVALUATEUSERCODE")
    private String evaluateUserCode;

    /**
     * 被评价人
     */
    @Excel(name = "被评价人", sort = 3)
    @Column(name = "BEEVALUATEUSERCODE")
    private String beEvaluateUserCode;

    /**
     * 评价的积分范围月份(年份)
     */
    @Excel(name = "评价的积分范围", sort = 4)
    @Column(name = "TIMEHORIZON")
    private String timeHorizon;

    /**
     * 评价时的积分
     * @return
     */
    @Excel(name = "评价时的积分", sort = 5)
    @Column(name = "SCORE")
    private int score;

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

    public String getEvaluateUserCode() {
        return evaluateUserCode;
    }

    public void setEvaluateUserCode(String evaluateUserCode) {
        this.evaluateUserCode = evaluateUserCode;
    }

    public String getBeEvaluateUserCode() {
        return beEvaluateUserCode;
    }

    public void setBeEvaluateUserCode(String beEvaluateUserCode) {
        this.beEvaluateUserCode = beEvaluateUserCode;
    }

    public String getTimeHorizon() {
        return timeHorizon;
    }

    public void setTimeHorizon(String timeHorizon) {
        this.timeHorizon = timeHorizon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
