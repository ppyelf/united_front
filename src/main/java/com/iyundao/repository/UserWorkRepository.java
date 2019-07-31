package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.UserWork;
import org.springframework.stereotype.Repository;

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

}
