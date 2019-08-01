package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserTrain;
import org.springframework.stereotype.Repository;

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

}
