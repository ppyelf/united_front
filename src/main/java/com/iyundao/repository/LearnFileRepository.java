package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Learn;
import com.iyundao.entity.LearnFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: LearnFileRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 学习文件
 * @Version: V1.0
 */
@Repository
public interface LearnFileRepository extends BaseRepository<LearnFile,String> {

    @Query("select lf from LearnFile lf where  lf.learn =?1")
    List<LearnFile> findByLearn(Learn learn);
}
