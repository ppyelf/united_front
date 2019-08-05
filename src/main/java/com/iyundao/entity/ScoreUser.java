package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ScoreUser
 * @project: //todo 来源暂时不知不填写
 * @author: 13620
 * @Date: 2019/8/3
 * @Description: 个人积分记录表
 * @Version: V1.0
 */
@Entity
@Table(name = "t_score_user")
public class ScoreUser extends BaseEntity<String> {

    private static final long serialVersionUID = 5032771450499330898L;

    /**
     * 积分
     */
    @Column(name = "SCORE",columnDefinition = "int default 0")
    private int score;

    /**
     * 时间
     */
    @Column(name = "DATATIME")
    private String dataTime;

    /**
     * 用户id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

//    /**
//     * 来源
//     */
//    @Column(name = "SOURCE")
//    private String source;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }

}
