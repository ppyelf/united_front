package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Politics;
import com.iyundao.entity.PoliticsIssueData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PoliticsIssueDataRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/1
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface PoliticsIssueDataRepository extends BaseRepository<PoliticsIssueData,String> {

    @Query("select pid from PoliticsIssueData pid where pid.politics = ?1")
    List<PoliticsIssueData> findIssueDataByPolitics(Politics politics);

    @Modifying
    @Query(value = "DELETE FROM t_politics_issue_data WHERE id = ?1",nativeQuery = true)
    void deleteByIssueDataId(String id);
}
