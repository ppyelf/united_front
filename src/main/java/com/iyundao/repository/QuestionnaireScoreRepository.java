package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Questionnaire;
import com.iyundao.entity.QuestionnaireScore;
import com.iyundao.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
