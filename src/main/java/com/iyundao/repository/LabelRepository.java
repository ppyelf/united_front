package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Label;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 查询类型标签
     * @param type
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Query(value = "SELECT l.* FROM t_label l WHERE l.TYPE = ?1 LIMIT ?2, ?3", nativeQuery = true)
    List<Label> findLabelByType(int type, int pageNumber, int pageSize);

    /**
     * 统计标签类型总数
     *
     * @param type
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_label l WHERE l.TYPE = ?1", nativeQuery = true)
    int countLabelByType(int type);
}
