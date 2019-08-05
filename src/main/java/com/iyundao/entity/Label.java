package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Label
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 15:05
 * @Description: 用户标签
 * @Version: V1.0
 */
@Entity
@Table(name = "t_label")
public class Label extends BaseEntity<String> {

    private static final long serialVersionUID = 9132041012843019284L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * 编码
     */
    @Column(name = "CODE", nullable = false, unique = true, length = 10)
    private String code;

    /**
     * 描述
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 标签关系
     */
    @OneToMany(mappedBy = "label", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserLabel> userLabels;

    /**
     * 标签类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TYPE", nullable = false, length = 1, columnDefinition = "tinyint(1) default '0'")
    private LABEL_TYPE type;

    public enum LABEL_TYPE{
        /**
         * 用户类型
         */
        user,

        /**
         * 活动类型
         */
        activity
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<UserLabel> getUserLabels() {
        return userLabels;
    }

    public void setUserLabels(Set<UserLabel> userLabels) {
        this.userLabels = userLabels;
    }

    public LABEL_TYPE getType() {
        return type;
    }

    public void setType(LABEL_TYPE type) {
        this.type = type;
    }
}
