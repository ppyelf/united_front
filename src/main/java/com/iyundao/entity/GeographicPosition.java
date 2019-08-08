package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: GeographicPosition
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Entity
@Table(name = "t_geographic_position")
public class GeographicPosition extends BaseEntity<String>{

    private static final long serialVersionUID = -5507051442698507688L;

    /**
     * 坐标描述
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 经度
     */
    @Column(name = "LONGITUDE")
    private String longitude;

    /**
     * 纬度
     */
    @Column(name = "LATITUDE")
    private String latitude;


    /**
     * 坐标类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TYPE")
    private XY_TYPE type;


    /**
     * 活动
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITYID")
    private Activity activity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public XY_TYPE getType() {
        return type;
    }

    public void setType(XY_TYPE type) {
        this.type = type;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public enum  XY_TYPE{

        /**
         * 固定场所
         */
        fixedPlace,

        /**
         *活动场所
         */
        activityVenue
    }



}
