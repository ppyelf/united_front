package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.Industry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @ClassName: IndustryRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/30 8:51
 * @Description: 行业仓库
 * @Version: V1.0
 */
@Repository
public interface IndustryRepository extends BaseRepository<Industry, String> {

    /**
     * 获取父类为空行业集合
     * @return
     */
    @Query("select i from Industry i where i.father is null order by i.code")
    List<Industry> findByFatherIsNull();

    /**
     * 根据父类ID获取集合
     * @param id
     * @return
     */
    @Query("select i from Industry i where i.father.id = ?1")
    List<Industry> findByFatherId(String id);
}
