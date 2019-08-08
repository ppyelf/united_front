package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.QuestionnaireAnswer;
import com.iyundao.entity.QuestionnaireTitle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: QuestionnaireAnswerRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/8
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface QuestionnaireAnswerRepository extends BaseRepository<QuestionnaireAnswer,String>{

    @Query("select qa from QuestionnaireAnswer qa where qa.questionnaireTitle = ?1")
    List<QuestionnaireAnswer> findByTitle(QuestionnaireTitle title);
}
