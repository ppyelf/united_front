package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ScoreDeption
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_score_deption")
public class ScoreDeption extends BaseEntity<String>{

    private static final long serialVersionUID = -4157752793864957363L;

    /**
     * 积分
     */
    @Column(name = "SCORE",columnDefinition = "int default 0")
    private int score;

    /**
     * 时间
     */
    @Column(name = "DATATIME")
    private String dataTime;

    /**
     * 机构
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECTID")
    private Subject subject;

    /**
     * 部门
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTID")
    private Depart depart;

    /**
     * 组织
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUPID")
    private Group group;

//    /**
//     * 来源
//     */
//    @Column(name = "SOURCE")
//    private String source;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
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

//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
}
