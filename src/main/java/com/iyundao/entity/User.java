package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: User
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/15 9:39
 * @Description: 用户
 * @Version: V2.0
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity<String> {

    private static final long serialVersionUID = -1172094710974098503L;

    /**
     * 账号
     */
    @Column(name = "ACCOUNT", nullable = false, unique = true, length = 50)
    private String account;

    /**
     * 姓名
     */
    @Column(name ="NAME", nullable = false,length = 50)
    private String name;

    /**
     * 编号
     */
    @Column(name = "CODE", nullable = false, length = 10, unique = true)
    private String code;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    /**
     * 密码盐
     */
    @Column(name = "SALT", nullable = false, length = 50)
    private String salt;

    /**
     * 性别 0-男, 1-女
     */
    @Column(name = "SEX")
    private int sex;

    /**
     * 账号状态
     */
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "STATUS")
    private ACCOUNT_TYPE status;

    /**
     * 个人详情
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserInfo userInfo;

    /**
     * 用户简介
     */
    @Column(name = "REMARK", columnDefinition = "varchar(20) default '未填写'", length = 500)
    private String remark;

    /**
     * 机构关系
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRelation> userRelations;

    /**
     * 角色关系
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RoleRelation> roleRelations;

    /**
     * 部门负责人
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Depart> departs;

    /**
     * 小组负责人
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Group> group;

    /**
     * 第三方授权
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserApp> userApps;

    /**
     * 培训经历
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserTrain> userTrains;

    /**
     * 工作履历
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserWork> userWorks;

    /**
     * 个人标签
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserLabel> labels;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public ACCOUNT_TYPE getStatus() {
        return status;
    }

    public void setStatus(ACCOUNT_TYPE status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<UserRelation> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(Set<UserRelation> userRelations) {
        this.userRelations = userRelations;
    }

    public Set<RoleRelation> getRoleRelations() {
        return roleRelations;
    }

    public void setRoleRelations(Set<RoleRelation> roleRelations) {
        this.roleRelations = roleRelations;
    }

    public Set<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(Set<Depart> departs) {
        this.departs = departs;
    }

    public Set<UserApp> getUserApps() {
        return userApps;
    }

    public void setUserApps(Set<UserApp> userApps) {
        this.userApps = userApps;
    }

    public Set<Group> getGroup() {
        return group;
    }

    public void setGroup(Set<Group> group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<UserTrain> getUserTrains() {
        return userTrains;
    }

    public void setUserTrains(Set<UserTrain> userTrains) {
        this.userTrains = userTrains;
    }

    public Set<UserWork> getUserWorks() {
        return userWorks;
    }

    public void setUserWorks(Set<UserWork> userWorks) {
        this.userWorks = userWorks;
    }

    public Set<UserLabel> getLabels() {
        return labels;
    }

    public void setLabels(Set<UserLabel> labels) {
        this.labels = labels;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 账号类型
     */
    public enum ACCOUNT_TYPE {
        /**
         * 正常
         */
        normal,

        /**
         * 禁用
         */
        disable,

        /**
         * 锁定
         */
        block
    }
}
