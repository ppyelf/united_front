package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Learn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: LearnRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习
 * @Version: V1.0
 */
@Repository
public interface LearnRepository extends BaseRepository<Learn,String> {
    @Query("SELECT  a FROM Learn a order by a.lastModifiedDate desc ")
    List<Learn> findAllDesc();
}
