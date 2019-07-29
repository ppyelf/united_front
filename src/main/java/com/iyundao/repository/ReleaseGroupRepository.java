package com.iyundao.repository;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.ReleaseGroups;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ReleaseGroupRepository
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 16:38
 * @Description: 仓库 - 发布小组
 * @Version: V2.0
 */
@Repository
public interface ReleaseGroupRepository extends BaseRepository<ReleaseGroups, String> {

}
