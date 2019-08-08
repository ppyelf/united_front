package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.QuestionnaireUser;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: QuestionnaireUserRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/8
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface QuestionnaireUserRepository extends BaseRepository<QuestionnaireUser,String> {
}
