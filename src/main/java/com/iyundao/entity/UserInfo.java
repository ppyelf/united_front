package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

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
     *
     */
}
