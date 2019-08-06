package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: IncidentRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:
 * @Version: V1.0
 */
@Repository
public interface IncidentRepository extends BaseRepository<Incident,String> {

    /**
     * 根据code查找实体
     * @param incidentCode
     * @return
     */
    @Query("select  i from  Incident  i  where i.code = ?1")
    Incident findByCode(String incidentCode);
}
