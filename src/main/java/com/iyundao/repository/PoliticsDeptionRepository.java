package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Politics;
import com.iyundao.entity.PoliticsDeption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: PoliticsDeptionRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/1
 * @Description: 参与部门组织机构
 * @Version: V1.0
 */
@Repository
public interface PoliticsDeptionRepository extends BaseRepository<PoliticsDeption, String> {

    @Query("select p from PoliticsDeption p where p.politics = ?1")
    List<PoliticsDeption> findDeptionByPolitics(Politics politics);
}
