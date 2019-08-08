package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Questionnaire;
import com.iyundao.entity.QuestionnaireTitle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: QuestionnaireTitleRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface QuestionnaireTitleRepository extends BaseRepository<QuestionnaireTitle,String> {

    @Query("select qt from QuestionnaireTitle qt where qt.questionnaire = ?1")
    List<QuestionnaireTitle> findTitleByQuestionnaire(Questionnaire questionnaire);
}
