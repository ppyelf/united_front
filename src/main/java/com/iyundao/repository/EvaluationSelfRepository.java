package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.EvaluationSelf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EvaluationSelfRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/5
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface EvaluationSelfRepository extends BaseRepository<EvaluationSelf,String> {
    @Query("select es from  EvaluationSelf es where es.lastModifiedDate= ?1")
    List<EvaluationSelf> findAllSelf();

    @Query(value = "SELECT * FROM t_evaluation_self WHERE EVALUATEUSERCODE \n" +
            "in (SELECT code FROM t_user WHERE NAME LIKE %?1%) ORDER BY LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationSelf> findAllSelfByUserName(String likeName);
}
