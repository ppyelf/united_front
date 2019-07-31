package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: UserLabel
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 17:37
 * @Description: 用户标签
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_label")
public class UserLabel extends BaseEntity<String> {

    private static final long serialVersionUID = 1809182034809218034L;

    /**
     * 所属用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 所属标签
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LABELID")
    private Label label;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
