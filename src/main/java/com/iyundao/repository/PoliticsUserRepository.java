package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.PoliticsUser;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: PoliticsUserRepository
 * @project:
 * @author: 13620
 * @Date: 2019/8/1
 * @Description: 参政议政关联人员表
 * @Version: V1.0
 */
@Repository
public interface PoliticsUserRepository extends BaseRepository<PoliticsUser, String> {
}
