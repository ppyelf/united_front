package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Learn
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习表
 * @Version: V1.0
 */
@Entity
@Table(name = "t_learn")
public class Learn extends BaseEntity<String>{

    private static final long serialVersionUID = 124451925439831526L;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private  String title;

    /**
     *  描述
     */
    @Column(name = "CONTENT")
    private String content;


    /**
     * 学习情况
     */
    @OneToMany(mappedBy = "learn",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<LearnDetails> learnDetailsSet;

    /**
     * 学习文件
     */
    @OneToMany(mappedBy = "learn",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<LearnFile> learnFiles;

    /**
     * 学习参与
     * @return
     */
    @OneToMany(mappedBy = "learn",cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<LearnParticipation> learnParticipation;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<LearnDetails> getLearnDetailsSet() {
        return learnDetailsSet;
    }

    public void setLearnDetailsSet(Set<LearnDetails> learnDetailsSet) {
        this.learnDetailsSet = learnDetailsSet;
    }

    public Set<LearnFile> getLearnFiles() {
        return learnFiles;
    }

    public void setLearnFiles(Set<LearnFile> learnFiles) {
        this.learnFiles = learnFiles;
    }

    public Set<LearnParticipation> getLearnParticipation() {
        return learnParticipation;
    }

    public void setLearnParticipation(Set<LearnParticipation> learnParticipation) {
        this.learnParticipation = learnParticipation;
    }
}
