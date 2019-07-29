package com.iyundao.entity;

import com.iyundao.base.BaseEntity;
import com.iyundao.base.utils.TimeUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Attendance
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/4 16:49
 * @Description: 实体 - 出勤时间
 * @Version: V2.0
 */
@Entity
@Table(name = "t_attendance")
public class Attendance extends BaseEntity<String> {

    private final static long serialVersionUID = -123782108301832L;

    /**
     * 活动
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITYID")
    private Activity activity;

    /**
     * 开始时间
     */
    @Column(name = "STARTTIME", nullable = false)
    private String startTime;

    /**
     * 结束时间
     */
    @Column(name = "ENDTIME", nullable = false)
    private String endTime;

    /**
     * 天数
     */
    @Column(name = "DAY")
    private int day;

    /**
     * 类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TYPE")
    private ATTENDANCE_TYPE attendanceType;

    /**
     * 位置X
     */
    @Column(name = "AXISX", length = 50)
    private String axisx;

    /**
     * 位置Y
     */
    @Column(name = "AXISY", length = 50)
    private String axisy;

    /**
     * 范围
     */
    @Column(name = "AREA", length = 50)
    private String area;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public void setEndTime(Date endTime) {
        this.endTime = TimeUtils.convertTime(endTime, "yyyyMMddHHmmss");
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public ATTENDANCE_TYPE getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(ATTENDANCE_TYPE attendanceType) {
        this.attendanceType = attendanceType;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public enum ATTENDANCE_TYPE{
        /**
         * 日常出勤
         */
        daily(0, "日常出勤"),

        /**
         * 考核出勤
         */
        assessment(1, "考核出勤"),

        /**
         * 学习出勤
         */
        learn(2, "学期出勤"),

        /**
         * 活动出勤
         */
        activity(3, "活动出勤"),

        /**
         * 其他
         */
        etc(4, "其他");

        private int index;

        private String name;

        ATTENDANCE_TYPE(int index, String name) {
            this.index = index;
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
