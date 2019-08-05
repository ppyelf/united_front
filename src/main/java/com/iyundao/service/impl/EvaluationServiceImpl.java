package com.iyundao.service.impl;

import com.iyundao.base.BaseRepository;
import com.iyundao.entity.EvaluationEvent;
import com.iyundao.entity.EvaluationOrganize;
import com.iyundao.entity.EvaluationSelf;
import com.iyundao.entity.EvaluationSpeech;
import com.iyundao.repository.EvaluationEventRepository;
import com.iyundao.repository.EvaluationOrganizeRepository;
import com.iyundao.repository.EvaluationSelfRepository;
import com.iyundao.repository.EvaluationSpeechRepository;
import com.iyundao.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: EvaluationServiceImpl
 * @project: //todo
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:    评价
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EvaluationServiceImpl implements EvaluationService{

    @Autowired
    private EvaluationSpeechRepository evaluationSpeechRepository;

    @Autowired
    private EvaluationEventRepository evaluationEventRepository;

    @Autowired
    private EvaluationOrganizeRepository evaluationOrganizeRepository;

    @Autowired
    private EvaluationSelfRepository evaluationSelfRepository;



    @Override
    public List<EvaluationEvent> saveAllEvent(List<EvaluationEvent> list) {
        for (EvaluationEvent ee : list) {
            ee.setCreatedDate(new Date());
            ee.setLastModifiedDate(new Date());
        }
        return evaluationEventRepository.saveAll(list);
    }

    @Override
    public List<EvaluationEvent> findAllEvent() {
        return evaluationEventRepository.findAllEvent();
    }

    @Override
    public List<EvaluationEvent> findAllByLikeIncidentTitle(String likeIncidentTitle) {
        return evaluationEventRepository.findAllByLikeIncidentTitle(likeIncidentTitle);
    }

    @Override
    public List<EvaluationOrganize> saveAllOrganize(List<EvaluationOrganize> list) {
        for (EvaluationOrganize evaluationOrganize : list) {
            evaluationOrganize.setCreatedDate(new Date());
            evaluationOrganize.setLastModifiedDate(new Date());
        }
        return evaluationOrganizeRepository.saveAll(list);
    }

    @Override
    public List<EvaluationOrganize> findAllOrganize() {
        return evaluationOrganizeRepository.findAllOrganize();
    }

    @Override
    public List<EvaluationOrganize> findAllOrganizeBySubjectName(String likeName) {
        return evaluationOrganizeRepository.findAllOrganizeBySubjectName(likeName);
    }

    @Override
    public List<EvaluationOrganize> findAllOrganizeByDepartName(String likeName) {
        return evaluationOrganizeRepository.findAllOrganizeByDepartName(likeName);
    }

    @Override
    public List<EvaluationOrganize> findAllOrganizeByGroupName(String likeName) {
        return evaluationOrganizeRepository.findAllOrganizeByGroupName(likeName);
    }

    @Override
    public List<EvaluationSelf> saveAllSelf(List<EvaluationSelf> list) {
        for (EvaluationSelf evaluationSelf : list) {
            evaluationSelf.setCreatedDate(new Date());
            evaluationSelf.setLastModifiedDate(new Date());
        }
        return evaluationSelfRepository.saveAll(list);
    }

    @Override
    public List<EvaluationSelf> findAllSelf() {
        return evaluationSelfRepository.findAllSelf();
    }

    @Override
    public List<EvaluationSelf> findAllSelfByUserName(String likeName) {
        return evaluationSelfRepository.findAllSelfByUserName(likeName);
    }

    @Override
    public List<EvaluationSpeech> saveAllSpeech(List<EvaluationSpeech> list) {
        for (EvaluationSpeech evaluationSpeech : list) {
            evaluationSpeech.setCreatedDate(new Date());
            evaluationSpeech.setLastModifiedDate(new Date());
        }
        return evaluationSpeechRepository.saveAll(list);
    }

    @Override
    public List<EvaluationSpeech> findAllSpeechByArticleName(String likeName) {
        return evaluationSpeechRepository.findAllSpeechByArticleName(likeName);
    }

    @Override
    public List<EvaluationSpeech> findAllSpeechByDiscussionName(String likeName) {
        return evaluationSpeechRepository.findAllSpeechByDiscussionName(likeName);
    }

    @Override
    public List<EvaluationSpeech> findAllSpeechByStudyName(String likeName) {
        return evaluationSpeechRepository.findAllSpeechByStudyName(likeName);
    }

    @Override
    public List<EvaluationSpeech> findAllSpeech() {

        return evaluationSpeechRepository.findAllDesc();
    }


}
