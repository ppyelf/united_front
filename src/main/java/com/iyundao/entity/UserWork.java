package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName UserWork
 * @project unitedfront
 * @author 念
 * @Date 2019/7/31 1134
 * @Description 用户工作经历
 * @Version V1.0
 */
@Entity
@Table(name = "t_user_work")
public class UserWork extends BaseEntity<String> {

    private static final long serialVersionUID = 1938091803810293804L;

    /**
     * 单位名称
     */
    @Column(name = "COMPANYNAME", nullable = false, length = 50)
    private String companyName;

    /**
     * 所属行业
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INDUSTRYID", nullable = false)
    private Industry industry;

    /**
     * 职位名称
     */
    @Column(name = "POSITIONNAME", nullable = false, length = 50)
    private String positionName;

    /**
     * 开始时间
     */
    @Column(name = "STARTTIME", nullable = false, length = 20)
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "ENDTIME", nullable = false, length = 20)
    private String endTime;

    /**
     * 所属用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID", nullable = false)
    private User user;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
