package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Activity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    @Override
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


    @Query(value = "select SUM(a.total/a.number) as score,s.userid as userId \n" +
            "from t_activity a \n" +
            "INNER JOIN t_sign s \n" +
            "on a.id = s.activityid \n" +
            "WHERE s.type = 0 and s.CREATEDATE  BETWEEN (?1) and (?2) \n" +
            "GROUP BY userId ORDER BY score\n",nativeQuery = true)
    List<Map<String,Object>> findAllByType(String startTime, String endTime);
}
