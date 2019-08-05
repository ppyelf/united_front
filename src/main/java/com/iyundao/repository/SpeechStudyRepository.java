package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.SpeechStudy;
import com.iyundao.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: SpeechStudyRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface SpeechStudyRepository extends BaseRepository<SpeechStudy,String> {

    /**
     * 通过用户实体找到所有所有理论研究
     * @param user
     * @return
     */
    @Query("select ss from  SpeechStudy ss where ss.user = ?1")
    List<SpeechStudy> findAllSpeechStudyByUser(User user);

    @Query("select ss from  SpeechStudy ss where ss.code = ?1")
    SpeechStudy findByStudyCode(String speechStudyCode);
}
