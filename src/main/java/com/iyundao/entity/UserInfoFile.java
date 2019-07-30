package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName: UserInfoFile
 * @project: IYunDao
 * @author: king
 * @Date: 2019/6/11 15:00
 * @Description: 用户详情附件
 * @Version: V2.0
 */
@Entity
@Table(name = "t_user_info_file")
public class UserInfoFile extends BaseEntity<String> {

    private static final long serialVersionUID = -1254258847554L;

    /**
     * 文件名称
     */
    @Column(name = "NAME",nullable = false,length = 50)
    private String name;
    /**
     * 附件URL路径
     */
    @Column(name = "URL",nullable = false)
    private String url;

    /**
     * 文件后缀名
     */
    @Column(name = "SUFFIX",nullable = false)
    private String suffix;

    /**
     * 用户详情ID
     */
    @Column(name = "USERINFOID",length = 50)
    private String userInfoId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }
}
