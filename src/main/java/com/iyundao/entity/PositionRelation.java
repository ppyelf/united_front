package com.iyundao.entity;

import com.iyundao.base.BaseComponent;
import com.iyundao.base.BaseEntity;
import com.iyundao.base.BaseRepository;

import javax.persistence.*;

/**
 * @ClassName: PositionRelation
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/3 14:28
 * @Description: 实体 - 岗位关系
 * @Version: V1.0
 */
@Entity
@Table(name = "t_position_relation")
public class PositionRelation extends BaseEntity<String> {

    private static final long serialVerisonUID = 1840921384082109438L;

    /**
     * 所属岗位
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POSITIONID", nullable = false)
    private Position position;

    /**
     * 所属用户
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID", nullable = false)
    private User user;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
