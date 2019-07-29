package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ReleaseDepart
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 9:21
 * @Description: 实体 - 发布部门
 * @Version: V2.0
 */
@Entity
@Table(name = "t_release_depart")
public class ReleaseDepart extends BaseEntity<String> {

    private final static long serialVersionUID = -82109384098320480L;

    /**
     * 机构ID
     */
    @Column(name = "DEPARTID", nullable = false)
    private String departId;

    /**
     * 活动ID
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITYID", nullable = false)
    private Activity activity;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
