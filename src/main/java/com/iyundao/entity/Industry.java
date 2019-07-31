package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: Industry
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/29 15:14
 * @Description: 行业
 * @Version: V1.0
 */
@Entity
@Table(name = "t_industry")
public class Industry extends BaseEntity<String> {

    private static final long serialVersionUID = -123412433124809890L;

    /**
     * 编号
     */
    @Column(name = "CODE", nullable = false, unique = true, length = 10)
    private String code;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * 父级行业
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FATHERID")
    private Industry father;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Industry getFather() {
        return father;
    }

    public void setFather(Industry father) {
        this.father = father;
    }
}
