package com.iyundao.service;

import com.iyundao.entity.Industry;

/**
 * @ClassName: IndustryService
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:33
 * @Description: 服务 - 行业
 * @Version: V1.0
 */
public interface IndustryService {

    /**
     * 根据ID查询实体集合
     * @param industryId
     * @return
     */
    Industry find(String industryId);
}
