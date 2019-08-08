package com.iyundao.service;

import com.iyundao.entity.Activity;
import com.iyundao.entity.GeographicPosition;

import java.util.List;

/**
 * @ClassName: GeographicService
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
public interface GeographicService {

    /**
     * 添加
     * @param name
     * @param x
     * @param y
     * @param type
     * @param activity
     * @return
     */
    GeographicPosition save(String name, String x, String y, int type, Activity activity);

    /**
     * 列表
     * @return
     */
    List<GeographicPosition> findAll();

    /**
     * 根据活动实体查找所有地理位置
     * @param activity
     * @return
     */
    List<GeographicPosition> findByActivity(Activity activity);

    /**
     * 根据活动实体删除地理位置
     * @param activity
     */
    void deleteByActivity(Activity activity);

    /**
     * 根据ids找到所有实体
     * @param geographicIds
     * @return
     */
    List<GeographicPosition> findAllByIds(String[] geographicIds);

    /**
     * 删除实体
     * @param geographicPositions
     */
    void delete(List<GeographicPosition> geographicPositions);
}
