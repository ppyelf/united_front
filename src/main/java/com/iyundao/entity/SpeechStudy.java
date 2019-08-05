package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: SpeechStudy
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 理论研究
 * @Version: V1.0
 */
@Entity
@Table(name = "t_speech_study")
public class SpeechStudy extends BaseEntity<String>{

    private static final long serialVersionUID = -3576221364889602927L;

    /**
     * 研究标题
     */
    @Column(name = "TITLE",length = 50)
    private String title;

    /**
     * code
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 研究正文
     */
    @OneToOne(mappedBy = "speechStudy",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RichText richText;

    /**
     * 研究人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private User user;

    /**
     * 研究状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATE")
    private SPEECHSTUDY_TYPE state;

    /**
     * 研究开始时间
     */
    @Column(name = "STARTTIME",length = 50)
    private String startTime;

    /**
     * 研究结束时间
     */
    @Column(name = "ENDTIME",length = 50)
    private String endTime;

    /**
     * 研究结果
     */
    @Column(name = "RESULT",length = 9999)
    private  String result;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SPEECHSTUDY_TYPE getState() {
        return state;
    }

    public void setState(SPEECHSTUDY_TYPE state) {
        this.state = state;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public enum SPEECHSTUDY_TYPE{
        /**
         * 未开始
         */
        notStarted,

        /**
         * 进行中
         */
        underWay,

        /**
         * 已结束
         */
        finished
    }
}
