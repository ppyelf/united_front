package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: DoorMessage
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 门户信息
 * @Version: V1.0
 */
@Entity
@Table(name = "t_door_message")
public class DoorMessage extends BaseEntity<String>{

    private static final long serialVersionUID = 8487554129616672872L;

    /**
     * 标题
     */
    @Column(name = "TITLE" )
    private String title;

    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATE")
    private DOORMESSAGE_TYPE state;


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

    public DOORMESSAGE_TYPE getState() {
        return state;
    }

    public void setState(DOORMESSAGE_TYPE state) {
        this.state = state;
    }

    public enum  DOORMESSAGE_TYPE{
        /**
         * 未展示
         */
        didNotShow,

        /**
         * 展示中
         */
        inTheShow

        }


}
