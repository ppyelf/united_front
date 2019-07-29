package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ActivityImage
 * @project: ayundao
 * @author: 念
 * @Date: 2019/6/5 8:48
 * @Description: 实体 - 活动图片
 * @Version: V2.0
 */
@Entity
@Table(name = "t_activity_image")
public class ActivityImage extends BaseEntity<String> {

    private final static long serialVersionUID = -13274981793471984L;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     * url
     */
    @Column(name = "URL", nullable = false, length = 100)
    private String url;

    /**
     * 后缀名
     */
    @Column(name = "SUFFIX", nullable = false, length = 4)
    private String suffix;

    /**
     * 浏览次数
     */
    @Column(name = "HOTS", columnDefinition = "tinyint default 0")
    private int hots;

    /**
     * 所属活动
     */
    @ManyToOne(fetch = FetchType.EAGER)
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

    public int getHots() {
        return hots;
    }

    public void setHots(int hots) {
        this.hots = hots;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
