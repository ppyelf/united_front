package com.iyundao.entity;

import com.iyundao.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * @ClassName: Role
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/16 14:48
 * @Description: 角色
 * @Version: V2.0
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity<String> {

    private static final long serialVersionUID = -1273498712913347912L;

    /**
     * 名称
     */
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    /**
     * 编码
     */
    @Column(name = "CODE", length = 50, nullable = false, unique = true)
    private String code;

    /**
     * 角色关系
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RoleRelation> roleRelations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoleRelation> getRoleRelations() {
        return roleRelations;
    }

    public void setRoleRelations(Set<RoleRelation> roleRelations) {
        this.roleRelations = roleRelations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
