package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: UserGroup
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/16 14:51
 * @Description: 用户组
 * @Version: V2.0
 */
@Entity
@Table(name = "t_user_group")
public class UserGroup extends BaseEntity<String> {

    private static final long serialVerisonUID = -129374981273498L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * 负责人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 父级--用户组
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FATHERID")
    private UserGroup father;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserGroup getFather() {
        return father;
    }

    public void setFather(UserGroup father) {
        this.father = father;
    }

}
