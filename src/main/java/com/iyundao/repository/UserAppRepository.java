package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserApp;
import org.springframework.data.jpa.repository.Query;

/**
 * @ClassName: UserAppRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/7/20 16:55
 * @Description: 仓库 - 用户授权
 * @Version: V2.0
 */
public interface UserAppRepository extends BaseRepository<UserApp, String> {

    /**
     * 根据openid查询实体
     * @param openId
     * @return
     */
    @Query("select up from UserApp up where up.openId = ?1")
    UserApp findByOpenId(String openId);
}
