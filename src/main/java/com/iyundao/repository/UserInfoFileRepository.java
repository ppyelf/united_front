package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserInfoFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: UserInfoFileRepository
 * @project: IYunDao
 * @author: King
 * @Date: 2019/6/14 9:00
 * @Description: 仓库 - 用户详情 -附件表
 * @Version: V2.0
 */
@Repository
public interface UserInfoFileRepository extends BaseRepository<UserInfoFile,String> {

    /**
     * 根据用户ID查询实体信息
     * @param userinfoid
     * @return
     */
    @Query("select uif from UserInfoFile uif where uif.userInfoId=?1")
    List<UserInfoFile> findByUserInfoFileUserid(String userinfoid);
}
