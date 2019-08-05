package com.iyundao.service.impl;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.Label;
import com.iyundao.entity.UserLabel;
import com.iyundao.repository.LabelRepository;
import com.iyundao.repository.UserLabelRepository;
import com.iyundao.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: LabelServiceImpl
 * @project: unitedfront
 * @author: 念
 * @Date: 2019/8/3 11:36
 * @Description: 实现 - 标签
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private UserLabelRepository userLabelRepository;

    @Override
    public boolean existsCode(String code) {
        Label label = labelRepository.findByCode(code);
        return label == null ? false : true;
    }

    @Override
    public Label createLabel(String name, String code, String remark, Label.LABEL_TYPE type) {
        Label label = new Label();
        label.setCreatedDate(new Date());
        label.setLastModifiedDate(new Date());
        label.setType(type);
        label.setName(name);
        label.setCode(code);
        label.setRemark(remark);
        label = labelRepository.save(label);
        return label;
    }

    @Override
    public List<Label> findAllLabels() {
        return labelRepository.findAll();
    }

    @Override
    public Label findLabelById(String id) {
        return labelRepository.find(id);
    }

    @Override
    public void deleteLabel(Label label) {
        labelRepository.delete(label);
    }

    @Override
    public List<Label> findLabelByIds(String[] labelIds) {
        return labelRepository.findByIds(labelIds);
    }

    @Override
    public UserLabel findUserLabelByUserIdAndLabelId(String userId, String labelId) {
        return userLabelRepository.findUserLabelByUserIdAndLabelId(userId, labelId);
    }

    @Override
    public void delUserLabel(UserLabel userLabel) {
        userLabelRepository.delete(userLabel);
    }

    @Override
    public Page<Label> findUserLabelPage(Pageable pageable) {
        List<Label> list = labelRepository.findLabelByType(Label.LABEL_TYPE.user.ordinal(), pageable.getPageNumber(), pageable.getPageSize());
        int count = labelRepository.countLabelByType(Label.LABEL_TYPE.user.ordinal());
        Page<Label> page = new Page<>(list, count, pageable);
        return page;
    }

    @Override
    public Page<Label> findActivityLabelPage(Pageable pageable) {
        List<Label> list = labelRepository.findLabelByType(Label.LABEL_TYPE.activity.ordinal(), pageable.getPageNumber(), pageable.getPageSize());
        int count = labelRepository.countLabelByType(Label.LABEL_TYPE.activity.ordinal());
        Page<Label> page = new Page<>(list, count, pageable);
        return page;
    }

}
