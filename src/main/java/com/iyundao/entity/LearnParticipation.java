package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: LearnParticipation
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习参与
 * @Version: V1.0
 */
@Entity
@Table(name = "t_learn_participation")
public class LearnParticipation extends BaseEntity<String> {

    private static final long serialVersionUID = -5667568516153337740L;

    /**
     * 学习任务
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARNID")
    private Learn learn;


    /**
     * 机构
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECTID")
    private Subject subject;

    /**
     * 部门
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPART")
    private Depart depart;


    /**
     * 组织
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUPID")
    private Group group;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;


    public Learn getLearn() {
        return learn;
    }

    public void setLearn(Learn learn) {
        this.learn = learn;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
