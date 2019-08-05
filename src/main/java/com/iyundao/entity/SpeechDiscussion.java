package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: SpeechDiscussion
 * @project: //todo 要添加外键关系讨论实例
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 参与讨论信息
 * @Version: V1.0
 */
@Entity
@Table(name = "t_speech_discussion")
public class SpeechDiscussion extends BaseEntity<String>{

    private static final long serialVersionUID = 4101041210221471689L;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 讨论人
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * code
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 讨论正文
     */
    @Column(name = "CONTENT" ,length = 5000)
    private String content;

    /**
     * 讨论时间
     */
    @Column(name = "TIME",length = 50)
    private String time;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
