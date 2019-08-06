package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Activity;
import com.iyundao.entity.ActivityFrequency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ActivityFrequencyRepository
 * @project: //
 * @author: 13620
 * @Date: 2019/8/6
 * @Description: 音频文件
 * @Version: V1.0
 */
@Repository
public interface ActivityFrequencyRepository extends BaseRepository<ActivityFrequency,String> {

    @Query("select af from ActivityFrequency  af order by af.lastModifiedDate desc " )
    List<ActivityFrequency> findAllDesc();

    @Query("select af from ActivityFrequency af where af.activity = ?1 order by af.lastModifiedDate desc")
    List<ActivityFrequency> findAllFrequencyByActivity(Activity activity);
}
