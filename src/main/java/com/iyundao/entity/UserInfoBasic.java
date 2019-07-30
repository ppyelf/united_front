package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: UserInfoBasic
 * @project: IYunDao
 * @author: King
 * @Date: 2019/6/11 13:50
 * @Description: 用户详情 -基础信息表
 * @Version: V2.0
 */
@Entity
@Table(name = "t_user_info_basic")

public class UserInfoBasic extends BaseEntity<String> {

    private static final long serialVerisionUID = -125421545122L;

    /**
     * 计算连续工龄时间
     */
    @Column(name = "WORKERDATE",length = 50)
    private String workerDate;

    /**
     * 合同制转正时间
     */
    @Column(name = "ZHUANZHENGDATE",length = 50)
    private String zhuanzhengDate;

    /**
     * 工资关系转移时间
     */
    @Column(name = "WAGESDATE",length = 50)
    private String wagesDate;

    /**
     * 到院时间
     */
    @Column(name = "ARRIVEDATE",length = 50)
    private String arriveDate;

    /**
     * 职工性质
     */
    @Column(name = "WORKERSNATURE",length = 50)
    private String workerSnature;

    /**
     * 职工类别
     */
    @Column(name = "WORKERSCATEGORY",length = 50)
    private String workerScategory;

    /**
     * 岗位类别
     */
    @Column(name = "POSTCATEGORY",length = 50)
    private String postCategory;

    /**
     * 考勤组
     */
    @Column(name = "CHECKGROUP",length = 50)
    private String checkGroup;

    /**
     * 编制
     */
    @Column(name = "ORGANIZATION",length = 50)
    private String organization;

    /**
     * 增加方式
     */
    @Column(name = "INCREASEMODE",length = 50)
    private String increaseMode;

    /**
     * 其他工号
     */
    @Column(name = "OTHERNUMBER",length = 50)
    private String otherNumber;

    /**
     * 年龄
     */
    @Column(name = "AGE",length = 50)
    private String age;

    /**
     * 用户详情ID
     */
    @Column(name = "USERINFOID",nullable = false,length = 50)
    private String userInfoId;

    public static long getSerialVerisionUID() {
        return serialVerisionUID;
    }

    public String getWorkerDate() {
        return workerDate;
    }

    public void setWorkerDate(String workerDate) {
        this.workerDate = workerDate;
    }

    public String getZhuanzhengDate() {
        return zhuanzhengDate;
    }

    public void setZhuanzhengDate(String zhuanzhengDate) {
        this.zhuanzhengDate = zhuanzhengDate;
    }

    public String getWagesDate() {
        return wagesDate;
    }

    public void setWagesDate(String wagesDate) {
        this.wagesDate = wagesDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getWorkerSnature() {
        return workerSnature;
    }

    public void setWorkerSnature(String workerSnature) {
        this.workerSnature = workerSnature;
    }

    public String getWorkerScategory() {
        return workerScategory;
    }

    public void setWorkerScategory(String workerScategory) {
        this.workerScategory = workerScategory;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getCheckGroup() {
        return checkGroup;
    }

    public void setCheckGroup(String checkGroup) {
        this.checkGroup = checkGroup;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getIncreaseMode() {
        return increaseMode;
    }

    public void setIncreaseMode(String increaseMode) {
        this.increaseMode = increaseMode;
    }

    public String getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
}
