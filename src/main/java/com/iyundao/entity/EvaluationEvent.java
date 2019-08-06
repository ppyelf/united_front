package com.iyundao.entity;

import com.iyundao.base.BaseEntity;
import com.iyundao.base.annotation.Excel;

import javax.persistence.*;

/**
 * @ClassName: EvaluationEvent
 * @project:
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:    事件评价
 * @Version: V1.0
 */
@Entity
@Table(name = "t_evaluation_event")
public class EvaluationEvent extends BaseEntity<String> {

    private static final long serialVersionUID = 4405268614183051544L;

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
     * 事件code
     */
    @Excel(name = "事件code", sort = 4)
    @Column(name = "INCIDENTCODE")
    private String incidentCode;

    /**
     * 机构code
     */
    @Excel(name = "机构code", sort = 5)
    @Column(name = "SUBJECTCODE")
    private String subjectCode;


    /**
     * 部门code
     */
    @Excel(name = "部门code", sort = 6)
    @Column(name = "DEPARTCODE")
    private String departCode;


    /**
     * 组织code
     */
    @Excel(name = "组织code", sort = 7)
    @Column(name = "GROUPCODE")
    private String groupCode;

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

    public String getIncidentCode() {
        return incidentCode;
    }

    public void setIncidentCode(String incidentCode) {
        this.incidentCode = incidentCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
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
}
