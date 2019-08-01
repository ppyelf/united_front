package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: PoliticsDeption
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参与部门组织机构
 * @Version: V1.0
 */
@Entity
@Table(name = "t_politics_deption")
public class PoliticsDeption extends BaseEntity<String>{

    private static final long serialVersionUID = -4494189785341967443L;

    /**
     * 参政议政id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLITICSID")
    private Politics politics;

    /**
     * 机构id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBJECTID")
    private Subject subject;

    /**
     * 部门id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTID")
    private Depart depart;

    /**
     * 组织id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUPID")
    private Group group;

    public Politics getPolitics() {
        return politics;
    }

    public void setPolitics(Politics politics) {
        this.politics = politics;
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
}
