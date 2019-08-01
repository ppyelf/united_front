package com.iyundao.service.impl;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Politics;
import com.iyundao.repository.PoliticsRepository;
import com.iyundao.service.PoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: PoliticsServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/7/31
 * @Description: 参政议政
 * @Version: V1.0
 */
@Service
@Transactional
public class PoliticsServiceImpl implements PoliticsService {

    @Autowired
    private PoliticsRepository politicsRepository;

    @Override
    public Page<Politics> findAllForPage(Pageable pageable) {
        return politicsRepository.findPage(pageable);
    }

    @Override
    public Politics add(String title, String synopsis, String startTime, String endTime, int state) {
        Politics politics = new Politics();
        politics.setCreatedDate(new Date());
        politics.setLastModifiedDate(new Date());
        politics.setTitle(title);
        politics.setSynopsis(synopsis);
        politics.setStartTime(startTime);
        politics.setEndTime(endTime);
        for (Politics.POLITICS_TYPE type : Politics.POLITICS_TYPE.values()){
            if (type.ordinal() == state){
                politics.setState(type);
            }
        }
        return politicsRepository.save(politics);
    }
}
