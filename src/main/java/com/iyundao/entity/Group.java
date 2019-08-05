package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Group
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/16 14:44
 * @Description: 小组(组织)实体
 * @Version: V2.0
 */
@Entity
@Table(name = "t_group")
public class Group extends BaseEntity<String> {

    private static final long serialVerisonUID = -10927349812794379L;

    /**
     * 名称
     */
    @Column(name = "NAME", length = 50)
    private String name;

    /**
     * 编号
     */
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    /**
     * 描述
     */
    @Column(name = "REMARK", length = 500)
    private String remark;

    /**
     * 父级--部门
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FATHERID")
    private Group father;


    /**
     * 所属机构
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECTID")
    private Subject subject;

    /**
     * 负责人
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 组织/小组关系
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRelation> userRelations;

    /**
     * 参政议政关系
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PoliticsDeption> politicsDeption;

    /**
     * 组织积分记录
     */
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ScoreDeption> scoreDeption;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Group getFather() {
        return father;
    }

    public void setFather(Group father) {
        this.father = father;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(Set<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<PoliticsDeption> getPoliticsDeption() {
        return politicsDeption;
    }

    public void setPoliticsDeption(Set<PoliticsDeption> politicsDeption) {
        this.politicsDeption = politicsDeption;
    }

    public Set<ScoreDeption> getScoreDeption() {
        return scoreDeption;
    }

    public void setScoreDeption(Set<ScoreDeption> scoreDeption) {
        this.scoreDeption = scoreDeption;
    }



}
