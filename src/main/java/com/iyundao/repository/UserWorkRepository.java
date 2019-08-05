package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserWork;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserWorkRepository
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/7/31 14:34
 * @Description: 用户工作履历
 * @Version: V1.0
 */
@Repository
public interface UserWorkRepository extends BaseRepository<UserWork, String> {

    /**
     * 查询用户工作履历集合
     * @param userId
     * @return
     */
    @Query("select w from UserWork w where w.user.id = ?1")
    List<UserWork> findUserWorkByUserId(String userId);
}
