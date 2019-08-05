package com.iyundao.service.impl;

import com.iyundao.entity.Industry;
import com.iyundao.repository.IndustryRepository;
import com.iyundao.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: IndustryServiceImpl
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/1 16:33
 * @Description: 实现 - 行业
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public Industry find(String industryId) {
        return industryRepository.find(industryId);
    }
}
