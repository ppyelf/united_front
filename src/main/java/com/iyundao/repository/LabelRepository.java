package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Label;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: LabelRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 15:07
 * @Description: 用户标签仓库
 * @Version: V1.0
 */
@Repository
public interface LabelRepository extends BaseRepository<Label, String> {

    /**
     * 根据CODE查询实体
     * @param code
     * @return
     */
    @Query("select l from Label l where l.code = ?1")
    Label findByCode(String code);
}
