package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Learn;
import com.iyundao.entity.LearnDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: LearnDetailsRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/10
 * @Description: 学习详情记录
 * @Version: V1.0
 */
@Repository
public interface LearnDetailsRepository extends BaseRepository<LearnDetails,String> {

    @Query("select ld from LearnDetails ld where ld.learn=?1")
    List<LearnDetails> findByLearn(Learn learn);
}
