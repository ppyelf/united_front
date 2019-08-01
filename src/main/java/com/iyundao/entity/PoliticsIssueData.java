package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: PoliticsIssueData
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 议题数据
 * @Version: V1.0
 */
@Entity
@Table(name = "t_politics_issue_data")
public class PoliticsIssueData  extends BaseEntity<String>{

    private static final long serialVersionUID = -108199728536586524L;

    /**
     * 标题
     */
    @Column(name = "TITLE", length = 50,nullable = false)
    private String title;

    /**
     * 正文
     */
    @Column(name = "CONTENT", length = 500)
    private String content;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private int sort;

    /**
     * 参政议政id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLITICSID")
    private Politics politics;

    /**
     * 讨论数据id
     * @return
     */
    @OneToMany(mappedBy = "politicsIssueData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PoliticsDiscussData> politicsDiscussData;

    /**
     * 决议数据id
     * @return
     */
    @OneToMany(mappedBy = "politicsIssueData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PoliticsResolutionData> politicsResolutionData;

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Politics getPolitics() {
        return politics;
    }

    public void setPolitics(Politics politics) {
        this.politics = politics;
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
}
