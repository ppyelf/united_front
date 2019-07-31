package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: UserTrain
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 8:47
 * @Description: 培训经历
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_train")
public class UserTrain extends BaseEntity<String> {

    private static final long serialVersionUID = 1181093284021830480L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * 开始时间
     */
    @Column(name = "STARTTIME", nullable = false, length = 50)
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "ENDTIME", nullable = false, length = 50)
    private String endTime;

    /**
     * 所获荣誉
     */
    @Column(name = "HONOR", length = 100)
    private String honor;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 所属用户
     * @return
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
