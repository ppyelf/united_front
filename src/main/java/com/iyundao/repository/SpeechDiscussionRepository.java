package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.SpeechDiscussion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SpeechDiscussionRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:  参与讨论信息
 * @Version: V1.0
 */
@Repository
public interface SpeechDiscussionRepository extends BaseRepository<SpeechDiscussion,String> {
    @Query("select sd from  SpeechDiscussion sd where sd.code = ?1")
    SpeechDiscussion findByDiscussionCode(String speechDiscussionCode);
}
