package com.iyundao.service;

import com.iyundao.entity.DoorMessage;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @ClassName: DoorService
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 门户
 * @Version: V1.0
 */
public interface DoorService {

    /**
     * 添加门户信息
     * @param title
     * @param content
     * @param state
     * @return
     */
    DoorMessage saveMessage(String title, String content, int state);

    /**
     * 列表
     * @return
     */
    List<DoorMessage> findAll();

    /**
     * 根据门户信息找到实体
     * @param messageId
     * @return
     */
    DoorMessage findByMessageId(String messageId);

    /**
     * 修改状态
     * @param messageId
     * @param state
     */
    void modifyState(String messageId, int state);



    /**
     * 查找实体集合
     * @param messageIds
     * @return
     */
    List<DoorMessage> findByMessageIds(String[] messageIds);


    /**
     * 删除实体集合
     * @param doorMessage
     */
    void delAllMessage(List<DoorMessage> doorMessage);
}
