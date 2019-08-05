package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Incident
 * @project: //todo 少类型没数据先不写
 * @author: 13620
 * @Date: 2019/8/3
 * @Description: 事件
 * @Version: V1.0
 */
@Entity
@Table(name = "t_incident")
public class Incident extends BaseEntity<String>{

    private static final long serialVersionUID = -9117311136528057612L;

    /**
     * 标题
     */
    @Column(name = "TITLE",length = 50)
    private String title;

    /**
     * code
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 正文
     */
    @Column(name = "CONTENT",length = 9999)
    private String content;

    /**
     * 发生时间
     */
    @Column(name = "DATATIME")
    private String dataTime;




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

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
