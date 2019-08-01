package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: PoliticsResolutionData
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 决议数据
 * @Version: V1.0
 */
@Entity
@Table(name = "t_politics_resolution_data")
public class PoliticsResolutionData extends BaseEntity<String>{

    private static final long serialVersionUID = -995581881242865321L;

    /**
     * 决议内容详情
     */
    @Column(name = "CONTENT",length = 500)
    private String content;

    /**
     * 参政议政id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLITICSID")
    private Politics politics;

    /**
     * 议题数据id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLITICSISSUEDATAID")
    private PoliticsIssueData politicsIssueData;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Politics getPolitics() {
        return politics;
    }

    public void setPolitics(Politics politics) {
        this.politics = politics;
    }

    public PoliticsIssueData getPoliticsIssueData() {
        return politicsIssueData;
    }

    public void setPoliticsIssueData(PoliticsIssueData politicsIssueData) {
        this.politicsIssueData = politicsIssueData;
    }
}
