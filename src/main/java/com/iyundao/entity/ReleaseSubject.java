package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ReleaseSubject
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 9:16
 * @Description: 实体 - 发布机构
 * @Version: V2.0
 */
@Entity
@Table(name = "t_release_subject")
public class ReleaseSubject extends BaseEntity<String> {

    private final static long serialVersionUID = -1324810928430218409L;

    /**
     * 机构ID
     */
    @Column(name = "SUBJECTID", nullable = false)
    private String subjectId;

    /**
     * 活动ID
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITYID", nullable = false)
    private Activity activity;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
