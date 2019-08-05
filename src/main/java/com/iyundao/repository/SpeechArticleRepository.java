package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.SpeechArticle;
import com.iyundao.entity.SpeechDiscussion;
import com.iyundao.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SpeechArticleRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 言论记录
 * @Version: V1.0
 */
@Repository
public interface SpeechArticleRepository extends BaseRepository<SpeechArticle,String>{

    @Query("select sa from SpeechArticle sa where  sa.user = ?1")
    List<SpeechArticle> findAllSpeechArticleByUser(User user);

    @Query("select sa from SpeechArticle sa where sa.code =?1")
    SpeechArticle findByArticleCode(String speechArticleCode);


}
