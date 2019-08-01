package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: PoliticsUser
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政关联人员表
 * @Version: V1.0
 */
@Entity
@Table(name = "t_politics_user")
public class PoliticsUser extends BaseEntity<String>{

    private static final long serialVersionUID = 946845362374839761L;

    /**
     * 接收状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATE",columnDefinition = "int default 0")
    private POLITICSUSER_TYPE state;

    /**
     * 参政议政id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLITICSID")
    private Politics politics;

    /**
     * 参与用户id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    public POLITICSUSER_TYPE getState() {
        return state;
    }

    public void setState(POLITICSUSER_TYPE state) {
        this.state = state;
    }

    public Politics getPolitics() {
        return politics;
    }

    public void setPolitics(Politics politics) {
        this.politics = politics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum POLITICSUSER_TYPE{
        /**
         * 未查看
         */
        notsee,

        /**
         * 已查看
         */
        havetosee
    }
}
