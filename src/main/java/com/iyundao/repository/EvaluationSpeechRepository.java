package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.EvaluationSpeech;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EvaluationSpeechRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:  言论评价
 * @Version: V1.0
 */
@Repository
public interface EvaluationSpeechRepository extends BaseRepository<EvaluationSpeech,String> {

    @Query(value = "SELECT * FROM t_evaluation_speech ORDER  by CREATEDATE DESC ",nativeQuery = true)
    List<EvaluationSpeech> findAllDesc();

    @Query(value = "SELECT * FROM t_evaluation_speech WHERE SPEECHARTICLECODE \n" +
            "IN (SELECT code from t_speech_article WHERE title like %?1%) order by LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationSpeech> findAllSpeechByArticleName(String likeName);

    @Query(value = "SELECT * FROM t_evaluation_speech WHERE SPEECHDISCUSSIONCODE \n" +
            "IN (SELECT code FROM t_speech_discussion WHERE title like %?1%) ORDER  by LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationSpeech> findAllSpeechByDiscussionName(String likeName);

    @Query(value = "SELECT * FROM t_evaluation_speech WHERE SPEECHSTUDYCODE \n" +
            "IN (SELECT code FROM t_speech_study WHERE  title like %?1%)ORDER BY LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationSpeech> findAllSpeechByStudyName(String likeName);
}
