package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Activity
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/4 16:35
 * @Description: 实体 - 活动
 * @Version: V2.0
 */
@Entity
@Table(name = "t_activity")
public class Activity extends BaseEntity<String> {

    private final static long serialVersionUID = -19841293471923748L;

    /**
     * 标题
     */
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    /**
     * 内容
     */
    @Column(name = "CONTENT", length = 500)
    private String content;

    /**
     * 成员数
     */
    @Column(name = "NUMBER", columnDefinition = "tinyint default 0")
    private int number;

    /**
     * 总分
     */
    @Column(name = "TOTAL", columnDefinition = "varchar(50) default 'blank'", length = 50, nullable = false)
    private String total;

    /**
     * 活动类型
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "TYPE")
    private ACTIVITY_TYPE type;

    /**
     * 出勤时间
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Attendance> attendances;

    /**
     * 个人签到
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Sign> signs;

    /**
     * 活动文件
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ActivityFile> activityFiles;

    /**
     * 活动图片
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ActivityImage> activityImages;

    /**
     * 发布机构
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReleaseSubject> releaseSubjects;

    /**
     * 发布部门
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReleaseDepart> releaseDeparts;

    /**
     * 发布组织
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ReleaseGroups> releaseGroups;

    /**
     * 现场文字
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ActivityText> activityText;

    /**
     * 现场小视频
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ActivityVideo> activityVideo;

    /**
     * 音频文件
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ActivityFrequency> activityFrequencies;

    /**
     * 地理位置
     * @return
     */
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GeographicPosition> geographicPosition;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ACTIVITY_TYPE getType() {
        return type;
    }

    public void setType(ACTIVITY_TYPE type) {
        this.type = type;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Set<Sign> getSigns() {
        return signs;
    }

    public void setSigns(Set<Sign> signs) {
        this.signs = signs;
    }

    public Set<ActivityFile> getActivityFiles() {
        return activityFiles;
    }

    public void setActivityFiles(Set<ActivityFile> activityFiles) {
        this.activityFiles = activityFiles;
    }

    public Set<ReleaseSubject> getReleaseSubjects() {
        return releaseSubjects;
    }

    public void setReleaseSubjects(Set<ReleaseSubject> releaseSubjects) {
        this.releaseSubjects = releaseSubjects;
    }

    public Set<ReleaseDepart> getReleaseDeparts() {
        return releaseDeparts;
    }

    public void setReleaseDeparts(Set<ReleaseDepart> releaseDeparts) {
        this.releaseDeparts = releaseDeparts;
    }

    public Set<ReleaseGroups> getReleaseGroups() {
        return releaseGroups;
    }

    public void setReleaseGroups(Set<ReleaseGroups> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    public Set<ActivityImage> getActivityImages() {
        return activityImages;
    }

    public void setActivityImages(Set<ActivityImage> activityImages) {
        this.activityImages = activityImages;
    }

    public Set<ActivityText> getActivityText() {
        return activityText;
    }

    public void setActivityText(Set<ActivityText> activityText) {
        this.activityText = activityText;
    }

    public Set<ActivityVideo> getActivityVideo() {
        return activityVideo;
    }

    public void setActivityVideo(Set<ActivityVideo> activityVideo) {
        this.activityVideo = activityVideo;
    }

    public Set<ActivityFrequency> getActivityFrequencies() {
        return activityFrequencies;
    }

    public void setActivityFrequencies(Set<ActivityFrequency> activityFrequencies) {
        this.activityFrequencies = activityFrequencies;
    }

    public Set<GeographicPosition> getGeographicPosition() {
        return geographicPosition;
    }

    public void setGeographicPosition(Set<GeographicPosition> geographicPosition) {
        this.geographicPosition = geographicPosition;
    }

    public enum ACTIVITY_TYPE{
        /**
         * 部门考核
         */
        depart,

        /**
         * 老干部考核
         */
        veteranCadres,

        /**
         * etc
         */
        etc
    }
}

