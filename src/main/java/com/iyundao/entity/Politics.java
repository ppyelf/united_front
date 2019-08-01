package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Politics
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
@Entity
@Table(name = "t_politics")
public class Politics extends BaseEntity<String>{

    private static final long serialVersionUID = 7361471922861298003L;

    /**
     * 标题
     */
    @Column(name = "TITLE", length = 50,nullable = false)
    private String title;

    /**
     * 内容
     */
    @Column(name = "CONTENT", length = 2000)
    private String content;

    /**
     * 开始时间
     */
    @Column(name = "STARTTIME", length = 50)
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "ENDTIME", length = 50)
    private String endTime;

    /**
     * 审核状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATE")
    private POLITICS_TYPE state;

    /**
     * 议题数据
     */
    @OneToMany(mappedBy = "politics", cascade ={CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<PoliticsIssueData> politicsIssueData;

    /**
     * 讨论数据
     */
    @OneToMany(mappedBy = "politics", cascade ={CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<PoliticsDiscussData> politicsDiscussData;

    /**
     * 决议数据
     */
    @OneToMany(mappedBy = "politics", cascade ={CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<PoliticsResolutionData> politicsResolutionData;

    /**
     * 参与部门组织机构
     * @return
     */
    @OneToMany(mappedBy = "politics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PoliticsDeption> politicsDeptions;

    /**
     * 参与人员
     * @return
     */
    @OneToMany(mappedBy = "politics", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PoliticsUser> politicsUser;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public POLITICS_TYPE getState() {
        return state;
    }

    public void setState(POLITICS_TYPE state) {
        this.state = state;
    }

    public Set<PoliticsIssueData> getPoliticsIssueData() {
        return politicsIssueData;
    }

    public void setPoliticsIssueData(Set<PoliticsIssueData> politicsIssueData) {
        this.politicsIssueData = politicsIssueData;
    }

    public Set<PoliticsDeption> getPoliticsDeptions() {
        return politicsDeptions;
    }

    public void setPoliticsDeptions(Set<PoliticsDeption> politicsDeptions) {
        this.politicsDeptions = politicsDeptions;
    }

    public Set<PoliticsDiscussData> getPoliticsDiscussData() {
        return politicsDiscussData;
    }

    public void setPoliticsDiscussData(Set<PoliticsDiscussData> politicsDiscussData) {
        this.politicsDiscussData = politicsDiscussData;
    }

    public Set<PoliticsResolutionData> getPoliticsResolutionData() {
        return politicsResolutionData;
    }

    public void setPoliticsResolutionData(Set<PoliticsResolutionData> politicsResolutionData) {
        this.politicsResolutionData = politicsResolutionData;
    }

    public Set<PoliticsUser> getPoliticsUser() {
        return politicsUser;
    }

    public void setPoliticsUser(Set<PoliticsUser> politicsUser) {
        this.politicsUser = politicsUser;
    }

    public enum POLITICS_TYPE{
        /**
         * 未审核
         */
        unreviewed,

        /**
         * 审核不通过
         */
        notApproved,

        /**
         * 审核通过
         */
        verified
    }
}
