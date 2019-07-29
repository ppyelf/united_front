package com.iyundao.service.impl;

import com.iyundao.entity.Sign;
import com.iyundao.repository.SignRepository;
import com.iyundao.service.SignService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SignServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/6 10:09
 * @Description: 实现 - 签到
 * @Version: V2.0
 */
@Service
@Transactional
public class SignServiceImpl implements SignService {

    @Autowired
    private SignRepository signRepository;

    @Override
    public List<Sign> findByActivityId(String id) {
        List<Sign> signs = signRepository.findByActivityId(id);
        return CollectionUtils.isEmpty(signs)
                ? new ArrayList<>()
                : signs;
    }
}
