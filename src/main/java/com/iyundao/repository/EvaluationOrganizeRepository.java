package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.EvaluationOrganize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: EvaluationOrganizeRepository
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/5
 * @Description: 组织评价
 * @Version: V1.0
 */
@Repository
public interface EvaluationOrganizeRepository  extends BaseRepository<EvaluationOrganize,String>{

    @Query("select eo from  EvaluationOrganize eo order by eo.lastModifiedDate desc")
    List<EvaluationOrganize> findAllOrganize();

    @Query(value = "SELECT * FROM  t_evaluation_organize WHERE SUBJECTCODE IN \n" +
            "(SELECT CODE FROM  t_subject WHERE NAME LIKE %?1%) ORDER BY LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationOrganize> findAllOrganizeBySubjectName(String likeName);

    @Query(value = "SELECT * FROM  t_evaluation_organize WHERE DEPARTCODE IN \n" +
            "(SELECT CODE FROM  t_depart WHERE NAME LIKE %?1%) ORDER BY LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationOrganize> findAllOrganizeByDepartName(String likeName);

    @Query(value = "SELECT * FROM  t_evaluation_organize WHERE GROUPCODE IN \n" +
            "(SELECT CODE FROM  t_group WHERE NAME LIKE %?1%) ORDER BY LASTMODIFIEDTIME DESC ",nativeQuery = true)
    List<EvaluationOrganize> findAllOrganizeByGroupName(String likeName);
}
