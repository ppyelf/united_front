package com.iyundao.service;


import com.iyundao.entity.Sign;

import java.util.List;

/**
 * @ClassName: SignService
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/6 10:09
 * @Description: 服务 - 签到
 * @Version: V2.0
 */
public interface SignService {

    /**
     * 根据活动ID获取签到集合
     * @param id
     * @return
     */
    List<Sign> findByActivityId(String id);
}
