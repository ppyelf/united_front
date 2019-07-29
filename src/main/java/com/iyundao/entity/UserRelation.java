package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: UserRelation
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/16 14:31
 * @Description: 用户与机构实体
 * @Version: V2.0
 */
@Entity
@Table(name = "t_user_relations")
public class UserRelation extends BaseEntity<String> {

    private static final long serialVersionUID = -1293749127349L;

    /**
     * 所属机构
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBJECTID")
    private Subject subject;

    /**
     * 所属部门
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTID")
    private Depart depart;

    /**
     * 所属小组
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUPID")
    private Group group;

    /**
     * 所属用户组
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERGROUPID")
    private UserGroup userGroup;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

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

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }


}
