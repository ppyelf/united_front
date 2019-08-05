package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: SpeechArticle
 * @project: //todo  需要添加类型，
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 发表文章
 * @Version: V1.0
 */
@Entity
@Table(name = "t_speech_article")
public class SpeechArticle extends BaseEntity<String>{

    private static final long serialVersionUID = 5853928231125635592L;

    /**
     * 标题
     */
    @Column(name = "TITLE",nullable = false,length = 50)
    private String title;

    /**
     * 文章code
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 正文
     */
    @OneToOne(mappedBy = "speechArticle",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RichText richText;

    /**
     * 路径
     */
    @Column(name = "URL",length = 50)
    private String url;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 排序
     */
    @Column(name = "SORT",length = 50)
    private int sort;

    /**
     * 发布时间
     */
    @Column(name = "TIME",length = 50)
    private String time;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RichText getRichText() {
        return richText;
    }

    public void setRichText(RichText richText) {
        this.richText = richText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
