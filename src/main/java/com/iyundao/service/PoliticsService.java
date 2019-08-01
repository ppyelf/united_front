package com.iyundao.service;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Politics;

import java.util.List;

/**
 * @ClassName: PoliticsService
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
public interface PoliticsService {

    /**
     * 参政议政列表分页
     * @param pageable
     * @return
     */
    Page<Politics> findAllForPage(Pageable pageable);

    /**
     * 添加参政议政实体
     * @param title
     * @param synopsis
     * @param startTime
     * @param endTime
     * @param state
     * @return
     */
    Politics add(String title, String synopsis, String startTime, String endTime, int state);
}
