package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: DoorFunction
 * @project:  //todo 常用功能不知道有哪些
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 常用功能设置
 * @Version: V1.0
 */
@Entity
@Table(name = "t_door_function")
public class DoorFunction extends BaseEntity<String>{

    private static final long serialVersionUID = 4068189233618982928L;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TPPE")
    private DOORFUNCTION_TYPE type;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DOORFUNCTION_TYPE getType() {
        return type;
    }

    public void setType(DOORFUNCTION_TYPE type) {
        this.type = type;
    }

    public enum DOORFUNCTION_TYPE{
        /**
         *  未知功能
         */
        one,
    }
}
