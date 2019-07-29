package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ActivityRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 13:43
 * @Description: 仓库 - 活动
 * @Version: V2.0
 */
@Repository
public interface ActivityRepository extends BaseRepository<Activity, String> {

    /**
     * 根据ID查找实体
     * @param id
     * @return
     */
    @Query("select a from Activity a where a.id = ?1")
    Activity find(String id);

    /**
     * 获取活动列表集合
     * @return
     */
    @Query("select a from Activity a")
    List<Activity> findAllForList();

    /**
     * 查询活动分页
     * @param pageable
     * @return
     */
    @Query("select a from Activity a")
    Page<Activity> findAllForPage(Pageable pageable);
}
