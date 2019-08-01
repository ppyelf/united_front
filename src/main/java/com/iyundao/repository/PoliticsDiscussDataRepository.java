package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Politics;
import com.iyundao.entity.PoliticsDiscussData;
import com.iyundao.entity.PoliticsIssueData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PoliticsDiscussDataRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/1
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface PoliticsDiscussDataRepository extends BaseRepository<PoliticsDiscussData,String>{

    @Query("select pdd from PoliticsDiscussData pdd where  pdd.politics = ?1")
    List<PoliticsDiscussData> findDiscussDataByPolitics(Politics politics);


    @Query("select pdd from PoliticsDiscussData pdd where  pdd.politicsIssueData = ?1")
    List<PoliticsDiscussData> findByIssueData(PoliticsIssueData pid);

    /**
     * 通过议题数据id删除所有讨论数据
     * @param id
     */
    @Modifying
    @Query(value = "DELETE FROM t_politics_discuss_data WHERE POLITICSISSUEDATAID = ?1",nativeQuery = true)
    void deleteByIssueDataId(String id);


}
