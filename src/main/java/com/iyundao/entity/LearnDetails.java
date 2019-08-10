package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: LearnDetails
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习情况
 * @Version: V1.0
 */
@Entity
@Table(name = "t_learn_details")
public class LearnDetails extends BaseEntity<String> {

    private static final long serialVersionUID = 4050890572925933001L;

    /**
     *  学习状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATE")
    private LEARNDETAILS_TYPE state;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     *  学习任务
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARNID")
    private Learn learn;

    public LEARNDETAILS_TYPE getState() {
        return state;
    }

    public void setState(LEARNDETAILS_TYPE state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Learn getLearn() {
        return learn;
    }

    public void setLearn(Learn learn) {
        this.learn = learn;
    }

    public enum LEARNDETAILS_TYPE{
        /**
         * 未学习
         */
        didNotLearn,

        /**
         * 已学习
         */
        hasBeenLearning
    }

}
