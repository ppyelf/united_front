package com.iyundao.entity;

import com.iyundao.base.BaseEntity;
import com.iyundao.base.annotation.Excel;

import javax.persistence.*;

/**
 * @ClassName: EvaluationOrganize
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description: 组织评价
 * @Version: V1.0
 */
@Entity
@Table(name = "t_evaluation_organize")
public class EvaluationOrganize extends BaseEntity<String> {

    private static final long serialVersionUID = 7307273280798125063L;

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
     * 评价的积分范围月份(年份)
     */
    @Excel(name = "评价的积分范围", sort = 2)
    @Column(name = "TIMEHORIZON")
    private String timeHorizon;

    /**
     * 评价时的积分
     * @return
     */
    @Excel(name = "评价时的积分", sort = 3)
    @Column(name = "SCORE")
    private int score;

    /**
     * 机构code
     */
    @Excel(name = "机构code", sort = 4)
    @Column(name = "SUBJECTCODE")
    private String subjectCode;


    /**
     * 部门code
     */
    @Excel(name = "部门code", sort = 5)
    @Column(name = "DEPARTCODE")
    private String departCode;


    /**
     * 组织code
     */
    @Excel(name = "组织code", sort = 6)
    @Column(name = "GROUPCODE")
    private String groupCode;

    /**
     * 评价人code
     */
    @Excel(name = "评价人code", sort = 7)
   @Column(name = "USERCODE")
   private String userCode;

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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
