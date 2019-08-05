package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.EvaluationEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EvaluationEventRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/5
 * @Description: 事件评价
 * @Version: V1.0
 */
@Repository
public interface EvaluationEventRepository extends BaseRepository<EvaluationEvent,String>{

    @Query("select ee from  EvaluationEvent ee order by ee.lastModifiedDate desc ")
    List<EvaluationEvent> findAllEvent();

    @Query(value = "select * from t_evaluation_event where INCIDENTCODE in( select CODE from t_incident  where title LIKE %?1% ) ORDER by LASTMODIFIEDTIME DESC ",
    nativeQuery = true)
    List<EvaluationEvent> findAllByLikeIncidentTitle(String likeIncidentTitle);
}
