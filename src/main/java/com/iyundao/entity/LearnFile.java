package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: LearnFile
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_learn_file")
public class LearnFile extends BaseEntity<String> {

    private static final long serialVersionUID = 4381692960053108928L;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;


    /**
     * url
     */
    @Column(name = "URL")
    private String url;



    /**
     * 后缀名
     */
    @Column(name = "SUFFIX")
    private String suffix;

    /**
     * 学习任务
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARNID")
    private Learn learn;


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

    public Learn getLearn() {
        return learn;
    }

    public void setLearn(Learn learn) {
        this.learn = learn;
    }
}
