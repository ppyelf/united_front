package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ActivityVideo
 * @project:
 * @author: 13620
 * @Date: 2019/8/6
 * @Description:   活动小视频
 * @Version: V1.0
 */
@Entity
@Table(name = "t_activity_video")
public class ActivityVideo extends BaseEntity<String>{

    private static final long serialVersionUID = 2697678284475943731L;


    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * url
     */
    @Column(name = "URL", length = 100)
    private String url;

    /**
     * 后缀名
     */
    @Column(name = "SUFFIX", length = 4)
    private String suffix;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
