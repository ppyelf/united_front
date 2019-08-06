package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Activity;
import com.iyundao.entity.ActivityText;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: ActivityTextRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/6
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface ActivityTextRepository extends BaseRepository<ActivityText,String> {

    @Query("select at from ActivityText at order by at.lastModifiedDate desc")
    List<ActivityText> findAllText();

    @Query("select at from ActivityText at where at.activity = ?1 order by at.lastModifiedDate desc")
    List<ActivityText> findAllTextByActivity(Activity activity);
}
