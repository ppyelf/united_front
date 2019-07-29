package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: ReleaseGroups
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 9:23
 * @Description: 实体 - 发布组织
 * @Version: V2.0
 */
@Entity
@Table(name = "t_release_groups")
public class ReleaseGroups extends BaseEntity<String> {

    private final static long serialVersionUID = -1073289472189479821L;

    /**
     * 机构ID
     */
    @Column(name = "GROUPSID", nullable = false)
    private String groupsId;

    /**
     * 活动ID
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITYID", nullable = false)
    private Activity activity;

    public String getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(String groupsId) {
        this.groupsId = groupsId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
