package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Subject
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/16 14:32
 * @Description: 机构实体
 * @Version: V2.0
 */
@Entity
@Table(name = "t_subject")
public class Subject extends BaseEntity<String> {

    private static final long serialVersionUID = -129079014789123784L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    /**
     * 编号
     */
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    /**
     * 机构类型
     */
    @Column(name = "SUBJECT_TYPE")
    private SUBJECT_TYPE subjectType;

    /**
     * 机构关系
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRelation> userRelations;

    /**
     * 行政/部门关系
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Depart> departs;

    /**
     * 组织/小组关系
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Group> groups;

    /**
     * 参政议政关系
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PoliticsDeption> politicsDeption;

    /**
     * 机构积分记录
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ScoreDeption> scoreDeption;

    /**
     * 学习参与
     */
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LearnParticipation> learnParticipation;



    public enum SUBJECT_TYPE{
        /**
         * 总院
         */
        head(0, "总院"),

        /**
         * 分院
         */
        part(1, "分院"),

        /**
         * 其他
         */
        etc(2, "其他");

        private int index;

        private String name;

        SUBJECT_TYPE(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SUBJECT_TYPE getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SUBJECT_TYPE subjectType) {
        this.subjectType = subjectType;
    }

    public Set<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(Set<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }

    public Set<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(Set<Depart> departs) {
        this.departs = departs;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
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

    public Set<LearnParticipation> getLearnParticipation() {
        return learnParticipation;
    }

    public void setLearnParticipation(Set<LearnParticipation> learnParticipation) {
        this.learnParticipation = learnParticipation;
    }
}
