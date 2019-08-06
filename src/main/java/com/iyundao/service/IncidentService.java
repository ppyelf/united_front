package com.iyundao.service;

import com.iyundao.entity.Incident;

import java.util.List;

/**
 * @ClassName: IncidentService
 * @project:
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:    事件
 * @Version: V1.0
 */
public interface IncidentService {

    /**
     * 查找所有事件
     * @return
     */
    List<Incident> findAll();

    /**
     * 添加事件
     * @param title
     * @param content
     * @param dataTime
     * @return
     */
    Incident saveIncident(String title, String content, String dataTime);

    /**
     * 根据id找到实体
     * @param incidentId
     * @return
     */
    Incident findById(String incidentId);

    /**
     *根据ids找到实体
     * @param incidentId
     * @return
     */
    List<Incident> findAllById(String[] incidentId);

    /**
     * 删除实体
     * @param incidentList
     */
    void deleteAll(List<Incident> incidentList);

    /**
     * 根据code查找实体
     * @param incidentCode
     * @return
     */
    Incident findByCode(String incidentCode);
}
