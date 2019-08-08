package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Activity;
import com.iyundao.entity.GeographicPosition;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GeographicRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface GeographicRepository extends BaseRepository<GeographicPosition,String> {

    @Query("select gp from  GeographicPosition  gp where gp.activity = ?1")
    List<GeographicPosition> findByActivity(Activity activity);

    @Modifying
    @Query("delete from GeographicPosition gp where  gp.activity =?1")
    void deleteByActivity(Activity activity);
}
