package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserLabel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserLabelRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 17:44
 * @Description: 用户标签仓库
 * @Version: V1.0
 */
@Repository
public interface UserLabelRepository extends BaseRepository<UserLabel, String> {

    /**
     * 根据标签ID和用户ID查询用户标签
     * @param userId
     * @param labelId
     * @return
     */
    @Query("select ul from UserLabel ul where ul.user.id = ?1 and ul.label.id = ?2")
    UserLabel findUserLabelByUserIdAndLabelId(String userId, String labelId);
}
