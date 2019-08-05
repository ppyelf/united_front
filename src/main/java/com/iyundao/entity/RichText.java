package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: RichText
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/5
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_rich_text")
public class RichText extends BaseEntity<String> {

    private static final long serialVersionUID = -2922891737932103147L;


    /**
     * 类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TYPE")
    private RichText_TYPE type;

    /**
     * 文章正文
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPEECHARTICLE")
    private SpeechArticle speechArticle;

    /**
     * 理论验证正文
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPEECHSTUDY")
    private SpeechStudy speechStudy;

    /**
     * 详情
     */
    @Column(name = "CONTENT", length = 4096)
    private String content;

    public RichText_TYPE getType() {
        return type;
    }

    public void setType(RichText_TYPE type) {
        this.type = type;
    }

    public SpeechArticle getSpeechArticle() {
        return speechArticle;
    }

    public void setSpeechArticle(SpeechArticle speechArticle) {
        this.speechArticle = speechArticle;
    }

    public SpeechStudy getSpeechStudy() {
        return speechStudy;
    }

    public void setSpeechStudy(SpeechStudy speechStudy) {
        this.speechStudy = speechStudy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum RichText_TYPE{
        /**
         * 文章
         */
        SpeechArticle,

        /**
         *理论研究
         */
        SpeechStudy
    }
}
