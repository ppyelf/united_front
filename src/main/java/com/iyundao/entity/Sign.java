package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: Sign
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/4 17:09
 * @Description: 实体 - 签到
 * @Version: V2.0
 */
@Entity
@Table(name = "t_sign")
public class Sign extends BaseEntity<String> {

    private final static long serialVersionUID = -83209802184309821L;

    /**
     * 签到时间
     */
    @Column(name = "SIGNTIME", nullable = false)
    private String signTime;

    /**
     * 签到状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TYPE")
    private SIGN_TYPE signType;

    /**
     * 所属活动
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACTIVITYID", nullable = false)
    private Activity activity;

    /**
     * 用户ID
     */
    @Column(name = "USERID", nullable = false)
    private String userId;

    /**
     * 位置X
     */
    @Column(name = "AXISX", nullable = false)
    private String axisx;

    /**
     * 位置Y
     */
    @Column(name = "AXISY", nullable = false)
    private String axisy;

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public SIGN_TYPE getSignType() {
        return signType;
    }

    public void setSignType(SIGN_TYPE signType) {
        this.signType = signType;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAxisx() {
        return axisx;
    }

    public void setAxisx(String axisx) {
        this.axisx = axisx;
    }

    public String getAxisy() {
        return axisy;
    }

    public void setAxisy(String axisy) {
        this.axisy = axisy;
    }

    public enum SIGN_TYPE{
        /**
         * 正常
         */
        normal,

        /**
         * 超出范围
         */
        out,

        /**
         * 异常
         */
        abnormal
    }
}
