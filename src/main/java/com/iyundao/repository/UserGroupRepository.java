package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserGroupRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/28 0:18
 * @Description: 仓库 - 用户组
 * @Version: V2.0
 */
@Repository
public interface UserGroupRepository extends BaseRepository<UserGroup, String> {

    /**
     * 所有用户组集合
     * @return
     */
    @Query("select ug from UserGroup ug")
    List<UserGroup> getList();

    /**
     * 根据ID获取用户组实体
     * @param id
     * @return
     */
    @Query("select ug from UserGroup ug where ug.id = ?1")
    UserGroup findByUserGroupId(String id);

    /**
     * 根据IDS获取用户组集合
     * @param userGroupIds
     * @return
     */
    @Query("select ug from UserGroup ug where ug.id in (?1)")
    List<UserGroup> findByids(String[] userGroupIds);

    /**
     * 获取没有父级的集合
     * @return
     */
    @Query("select ug from UserGroup ug where ug.father is null")
    List<UserGroup> getListByFatherIsNull();

    /**
     * 根据父级ID获取实体集合
     * @param id
     * @return
     */
    @Query("select ug from UserGroup ug where ug.father.id = (?1)")
    List<UserGroup> findByFatherId(String id);


}
