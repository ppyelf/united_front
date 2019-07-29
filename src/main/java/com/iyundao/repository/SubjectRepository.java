package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: SubjectRepository
 * @project: ayundao
 * @author: 念
 * @Date: 2019/5/24 16:31
 * @Description: 仓库 - 机构
 * @Version: V2.0
 */
@Repository
public interface SubjectRepository extends BaseRepository<Subject, String> {

    /**
     * 根据code查询实体
     * @param code
     * @return
     */
    @Query("select s from Subject s where s.code = (?1)")
    Subject findByCode(String code);
}
