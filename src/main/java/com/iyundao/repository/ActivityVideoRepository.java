package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Activity;
import com.iyundao.entity.ActivityVideo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ActivityVideoRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/6
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface ActivityVideoRepository extends BaseRepository<ActivityVideo,String> {

    @Query("select av from ActivityVideo av order by av.lastModifiedDate desc")
    List<ActivityVideo> findAllVideo();

    @Query("select av from  ActivityVideo av where av.activity = ?1 order by av.lastModifiedDate desc ")
    List<ActivityVideo> findAllVideoByActivity(Activity activity);
}
