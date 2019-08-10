package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Learn;
import com.iyundao.entity.LearnParticipation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: LearnParticipationRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/10
 * @Description: 学习参与部门
 * @Version: V1.0
 */
@Repository
public interface LearnParticipationRepository extends BaseRepository<LearnParticipation,String> {

    @Query("SELECT lp from LearnParticipation  lp where lp.learn = ?1")
    List<LearnParticipation> findByLearn(Learn learn);
}
