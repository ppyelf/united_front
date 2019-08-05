package com.iyundao.entity;

import com.fasterxml.jackson.core.sym.NameN;
import com.iyundao.base.BaseEntity;

import javax.persistence.*;

/**
 * @ClassName: UserInfo
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 14:53
 * @Description: 用户详情
 * @Version: V1.0
 */
@Entity
@Table(name = "t_user_info")
public class UserInfo extends BaseEntity<String> {

    private static final long serialVersionUID = 1132213611265312165L;

    /**
     * 所属用户
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERID", nullable = false)
    private User user;

    /**
     * 真实姓名
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 民族
     */
    @Column(name = "NATION", length = 20)
    private String nation;

    /**
     * 年龄
     */
    @Column(name = "AGE", columnDefinition = "tinyint(3) default '0'")
    private int age;

    /**
     * 政治面貌
     */
    @Column(name = "POLITICAL", length = 50)
    private String political;

    /**
     * 籍贯
     */
    @Column(name = "NATIVEPLACE", length = 50)
    private String nativePlace;

    /**
     * 毕业院校
     */
    @Column(name = "UNIVERSITY", length = 50)
    private String university;

    /**
     * 专业
     */
    @Column(name = "MAJOR", length = 50)
    private String major;

    /**
     * 学历
     */
    @Column(name = "EDUCATION", length = 50)
    private String education;

    /**
     * 出生日期
     */
    @Column(name = "BIRTHDAY", length = 8)
    private String birthDay;

    /**
     * 地址
     */
    @Column(name = "ADDRESS", length = 100)
    private String address;

    /**
     * 电话
     */
    @Column(name = "TEL", length = 11, unique = true)
    private long tel;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL", length = 50)
    private String email;

    /**
     * 微信
     */
    @Column(name = "WX", length = 50, unique = true)
    private String wx;

    /**
     * QQ
     */
    @Column(name = "QQ", length = 20, unique = true)
    private String qq;

    /**
     * 身份证号
     */
    @Column(name = "ICARD", length = 18, unique = true)
    private String iCard;

    /**
     * 自我评价
     */
    @Column(name = "SELFEVALUATION")
    private String selfEvaluation;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getiCard() {
        return iCard;
    }

    public void setiCard(String iCard) {
        this.iCard = iCard;
    }

    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }
}
