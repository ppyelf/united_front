package com.iyundao.repository;

import com.iyundao.entity.User;
import com.iyundao.entity.UserRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserRelationRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/5/20 15:08
 * @Description: 仓库 - 用户关系
 * @Version: V2.0
 */
@Repository
public interface UserRelationRepository extends CrudRepository<UserRelation, String> {

    /**
     * 获取用户关系
     * @param user
     * @return
     */
    List<UserRelation> findByUser(User user);

    /**
     * 根据用户ID获取用户关系
     * @param id
     * @return
     */
    List<UserRelation> findByUserId(String id);

    /**
     * 根据用户ID和机构ID获取用户关系
     * @param userId
     * @param subjectId
     * @return
     */
    @Query("select ur from UserRelation ur where ur.user.id = ?1 and ur.subject.id = ?2")
    UserRelation findByUserIdAndSubject(String userId, String subjectId);

    /**
     * 获取所有的用户关系
     * @return
     */
    @Query("select ur from UserRelation ur")
    List<UserRelation> getAll();

    /**
     * 根据机构ID,部门ID,小组ID获取用户关系集合
     * @param subjectId
     * @param departId
     * @return
     */
    @Query("select ur from UserRelation ur where ur.subject.id = ?1 and (ur.depart.id = ?2 or ur.group.id = ?3)")
    List<UserRelation> findBySubjectAndDepartOrGroup(String subjectId, String departId, String groupId);

    /**
     * 根据机构,部门IDS,小组IDS查找用户关系
     * @param subjectId
     * @param departIds
     * @param groupIds
     * @return
     */
    @Query("select ur from UserRelation ur where ur.subject.id = ?1 and (ur.depart.id in (?2) or ur.group.id in (?3))")
    List<UserRelation> findBySubjectAndDepartIdsOrGroupIds(String subjectId, String[] departIds, String[] groupIds);

    /**
     * 根据部门id查询用户id
     * @param subjectId
     * @return
     */
    @Query("select t.id from UserRelation t where t.depart.id = ?1")
    List<String> selectByDepart(String subjectId);

    /**
     * 根据组织id查询用户id
     * @param subjectId
     * @return
     */
    @Query("select t.id from UserRelation t where t.group.id = ?1")
    List<String> selectByGroup(String subjectId);

    /**
     * 根据机构id查询用户id
     * @param subjectId
     * @return
     */
    @Query("select t.id from UserRelation t where t.subject.id = ?1")
    List<String> selectBySubjectId(String subjectId);

    /**
     * 根据用户ID和所属机构/组织ID查询所属关系
     * @param userId
     * @param departId
     * @param groupId
     * @return
     */
    @Query("select ur from UserRelation ur where ur.user.id = ?1 and (ur.depart.id = ?2 or ur.group.id = ?3)")
    UserRelation findByUserIdAndDepartIdOrGroupId(String userId, String departId, String groupId);

    /**
     * 根据用户IDS获取实体集合信息
     * @param userIds
     * @return
     */
    @Query("select ur from UserRelation ur where ur.user.id in (?1)")
    List<UserRelation> findByUserIds(String[] userIds);
}