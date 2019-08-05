package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserTrain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserTrainRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 10:19
 * @Description: 用户培训经历
 * @Version: V1.0
 */
@Repository
public interface UserTrainRepository extends BaseRepository<UserTrain, String> {

    /**
     * 查询用户培训经历集合
     * @param id
     * @return
     */
    @Query("select ut from UserTrain ut where ut.user.id = ?1")
    List<UserTrain> findUserTrainByUserId(String id);
}
