package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ActivityText
 * @project:
 * @author: 13620
 * @Date: 2019/8/6
 * @Description:    现场文字
 * @Version: V1.0
 */
@Entity
@Table(name = "t_activity_text")
public class ActivityText extends BaseEntity<String> {

    private static final long serialVersionUID = -8863937285746503365L;

    /**
     * 文字详情
     */
    @Column(name = "CONTENT")
    private String  content;

    /**
     * 发表人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 所属活动
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITYID")
    private Activity activity;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
