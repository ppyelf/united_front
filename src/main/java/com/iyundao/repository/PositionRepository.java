package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PositionRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:13
 * @Description: 仓库 - 职位
 * @Version: V1.0
 */
@Repository
public interface PositionRepository extends BaseRepository<Position, String> {

    /**
     * 根据部门ID获取岗位集合信息
     * @param departId
     * @return
     */
    @Query("select p from Position p where p.depart.id = ?1")
    List<Position> findByDepartId(String departId);

    /**
     * 根小组ID获取岗位集合信息
     * @param groupId
     * @return
     */
    @Query("select p from Position p where p.group.id = ?1")
    List<Position> findByGroupId(String groupId);
}
