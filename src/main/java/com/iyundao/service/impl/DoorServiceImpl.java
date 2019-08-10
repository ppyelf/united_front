package com.iyundao.service.impl;

import com.iyundao.entity.DoorMessage;
import com.iyundao.repository.DoorMessageRepository;
import com.iyundao.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: DoorServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description:    门户
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DoorServiceImpl implements DoorService{

    @Autowired
    private DoorMessageRepository doorMessageRepository;

    @Override
    public DoorMessage saveMessage(String title, String content, int state) {
        DoorMessage doorMessage = new DoorMessage();
        doorMessage.setCreatedDate(new Date());
        doorMessage.setLastModifiedDate(new Date());
        doorMessage.setTitle(title);
        doorMessage.setContent(content);
        for (DoorMessage.DOORMESSAGE_TYPE doormessageType : DoorMessage.DOORMESSAGE_TYPE.values()){
            if (doormessageType.ordinal()==state){
                doorMessage.setState(doormessageType);
            }
        }
        doorMessage = doorMessageRepository.save(doorMessage);
        return doorMessage;
    }

    @Override
    public List<DoorMessage> findAll() {
        return doorMessageRepository.findAllDesc();
    }

    @Override
    public DoorMessage findByMessageId(String messageId) {
        return doorMessageRepository.find(messageId);
    }

    @Override
    public void modifyState(String messageId, int state) {
            doorMessageRepository.modifyState(messageId,state);
    }



    @Override
    public List<DoorMessage> findByMessageIds(String[] messageIds) {
        return doorMessageRepository.findByIds(messageIds);
    }

    @Override
    public void delAllMessage(List<DoorMessage> doorMessage) {
        doorMessageRepository.deleteAll(doorMessage);
    }
}
