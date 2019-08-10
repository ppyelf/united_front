package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.DoorMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: DoorMessageRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/9
 * @Description: 门户信息
 * @Version: V1.0
 */
@Repository
public interface DoorMessageRepository extends BaseRepository<DoorMessage,String>{

    @Query(value = "SELECT * from t_door_message WHERE state = 1 ORDER BY CREATEDATE DESC ",nativeQuery = true)
    List<DoorMessage> findAllDesc();

    @Modifying
    @Query(value = "UPDATE t_door_message SET state = ?2 WHERE id =?1",nativeQuery = true)
    void modifyState(String  messageId, int type);
}
