package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GroupRepository
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/27 15:14
 * @Description: 仓库 - 小组
 * @Version: V2.0
 */
@Repository
public interface GroupRepository extends BaseRepository<Group, String> {

    /**
     * 获取机构的所有小组集合
     * @param subjectId
     * @return
     */
    @Query("select g from Group g where g.subject.id = ?1")
    List<Group> findBySubjectId(String subjectId);

    /**
     * 根据ID获取实体
     * @param groupsId
     * @return
     */
    @Query("select g from Group g where g.id = ?1")
    Group findByGroupId(String groupsId);

    /**
     * 获取所有小组集合
     * @return
     */
    @Query("select g from Group g")
    List<Group> getList();

    @Query("select g from Group g where g.subject is null")
    List<Group> findSubjectIsNull();

    /**
     * 获取子集
     * @param id
     * @return
     */
    @Query("select g from Group g where g.father.id = (?1)")
    List<Group> findByFatherId(String id);

    /**
     * 获取没有父级的集合
     * @return
     */
    @Query("select g from Group g where g.father is null")
    List<Group> getListByFatherIsNull();

    /**
     * 根据subjectId获取所有父级实体集合
     * @param subjectId
     * @return
     */
    @Query("select g from Group g where g.subject.id = ?1 and g.father is null")
    List<Group> findBySubjectIdAndFatherIsNull(String subjectId);

    /**
     * 根据CODE查询实体
     * @param code
     * @return
     */
    @Query("select g from Group g where g.code = ?1")
    Group findByCode(String code);
}
