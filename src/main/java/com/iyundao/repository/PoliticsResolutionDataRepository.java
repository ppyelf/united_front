package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.PoliticsIssueData;
import com.iyundao.entity.PoliticsResolutionData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: PoliticsResolutionDataRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/1
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface PoliticsResolutionDataRepository extends BaseRepository<PoliticsResolutionData,String>{

    /**
     * 根据议题数据找到决议实体
     * @param pid
     * @return
     */
    @Query("select prd from PoliticsResolutionData  prd where  prd.politicsIssueData = ?1")
    PoliticsResolutionData findByIssueData(PoliticsIssueData pid);

    /**
     * 通过议题数据id删除所有决议数据
     * @param id
     */
    @Modifying
    @Query(value = "DELETE FROM  t_politics_resolution_data WHERE POLITICSISSUEDATAID = ?1",nativeQuery = true)
    void deleteByIssueDataId(String id);
}
