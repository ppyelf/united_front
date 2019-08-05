package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: Position
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 15:26
 * @Description: 组织岗位
 * @Version: V1.0
 */
@Entity
@Table(name = "t_position")
public class Position extends BaseEntity<String> {

    private static final long serialVersionUID = 120940912830948123L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * 所属行业
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INDUSTRYID", nullable = false)
    private Industry industry;

    /**
     * 描述
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 所属部门
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEPARTID")
    private Depart depart;

    /**
     * 所属小组
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUPID")
    private Group group;

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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
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
