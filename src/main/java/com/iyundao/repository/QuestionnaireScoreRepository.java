package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Questionnaire;
import com.iyundao.entity.QuestionnaireScore;
import com.iyundao.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: QuestionnaireScoreRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/8
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface QuestionnaireScoreRepository extends BaseRepository<QuestionnaireScore,String> {

    @Query("select qs from QuestionnaireScore qs where qs.questionnaire = ?1")
    List<QuestionnaireScore> findScoreByQuestionnaire(Questionnaire questionnaire);

    @Query("select qs from  QuestionnaireScore qs where qs.user = ?1")
    List<QuestionnaireScore> findScoreByUser(User user);

    @Query(value = "select u.name as userName,u.id as userId,sum(qs.score) as score from t_user u \n" +
            "INNER JOIN t_questionnaire_score qs on u.id=qs.USERID \n" +
            "WHERE qs.CREATEDATE BETWEEN ?1 AND ?2 GROUP BY u.name ORDER BY score",nativeQuery = true)
    List<Map<String,Object>>  findAllByType(String startTime, String endTime);
}
