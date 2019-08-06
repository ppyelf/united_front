package com.iyundao.service.impl;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.*;
import com.iyundao.repository.SpeechArticleRepository;
import com.iyundao.repository.SpeechDiscussionRepository;
import com.iyundao.repository.SpeechStudyRepository;
import com.iyundao.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: SpeechServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 言论记录
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpeechServiceImpl implements SpeechService {

    @Autowired
    private SpeechArticleRepository speechArticleRepository;

    @Autowired
    private SpeechStudyRepository speechStudyRepository;

    @Autowired
    private SpeechDiscussionRepository speechDiscussionRepository;

    @Override
    public SpeechArticle saveSpeechArticle(String title, String content, String url, User user, int sort, String time) {
        SpeechArticle speechArticle = new SpeechArticle();
        speechArticle.setCreatedDate(new Date());
        speechArticle.setLastModifiedDate(new Date());
        speechArticle.setTitle(title);
        RichText richText = new RichText();
        richText.setCreatedDate(new Date());
        richText.setLastModifiedDate(new Date());
        richText.setContent(content);
        for (RichText.RichText_TYPE type : RichText.RichText_TYPE.values()){
            if(type.ordinal()==0){
                richText.setType(type);
            }
        }
        speechArticle.setRichText(richText);
        speechArticle.setUrl(url);
        speechArticle.setUser(user);
        speechArticle.setSort(sort);
        speechArticle.setTime(time);

        return speechArticleRepository.save(speechArticle);
    }

    @Override
    public List<SpeechArticle> findAllSpeechArticle() {

        return speechArticleRepository.findAll();
    }

    @Override
    public SpeechArticle findSpeechArticleById(String speechArticleId) {
        return speechArticleRepository.find(speechArticleId);
    }

    @Override
    public List<SpeechArticle> findSpeechArticleByIds(String[] speechArticleIds) {
        return speechArticleRepository.findByIds(speechArticleIds);
    }

    @Override
    public void deleteAllSpeechArticle(List<SpeechArticle> speechArticleList) {
        speechArticleRepository.deleteAll(speechArticleList);
    }

    @Override
    public List<SpeechArticle> findAllSpeechArticleByUser(User user) {
        return speechArticleRepository.findAllSpeechArticleByUser(user);
    }

    @Override
    public List<SpeechStudy> findAllSpeechStudy() {
        return speechStudyRepository.findAll();
    }

    @Override
    public SpeechStudy saveSpeechStudy(String title, String content,User user, int state, String startTime, String endTime, String result) {
        SpeechStudy speechStudy = new SpeechStudy();
        speechStudy.setCreatedDate(new Date());
        speechStudy.setLastModifiedDate(new Date());
        speechStudy.setTitle(title);
        RichText richText = new RichText();
        richText.setCreatedDate(new Date());
        richText.setLastModifiedDate(new Date());
        richText.setContent(content);
        for (RichText.RichText_TYPE type : RichText.RichText_TYPE.values()){
            if(type.ordinal()==0){
                richText.setType(type);
            }
        }
        speechStudy.setRichText(richText);
        speechStudy.setUser(user);
        for (SpeechStudy.SPEECHSTUDY_TYPE type : SpeechStudy.SPEECHSTUDY_TYPE.values()){
            if (type.ordinal()==state){
               speechStudy.setState(type);
            }
        }
        speechStudy.setStartTime(startTime);
        speechStudy.setEndTime(endTime);
        speechStudy.setResult(result);

        return speechStudyRepository.save(speechStudy);
    }

    @Override
    public List<SpeechStudy> findAllSpeechStudyByUser(User user) {
        return speechStudyRepository.findAllSpeechStudyByUser(user);
    }

    @Override
    public SpeechStudy findSpeechStudyById(String speechStudyId) {
        return speechStudyRepository.find(speechStudyId);
    }

    @Override
    public List<SpeechStudy> findSpeechStudyByIds(String[] speechStudyIds) {
        return speechStudyRepository.findByIds(speechStudyIds);
    }

    @Override
    public void deleteAllSpeechStudy(List<SpeechStudy> speechStudyList) {
        speechStudyRepository.deleteAll(speechStudyList);
    }

    @Override
    public SpeechDiscussion findSpeechDiscussionById(String speechDiscussionId) {
        return  speechDiscussionRepository.find(speechDiscussionId);
    }

    @Override
    public SpeechArticle findByArticleCode(String speechArticleCode) {
        return speechArticleRepository.findByArticleCode(speechArticleCode);
    }

    @Override
    public SpeechDiscussion findByDiscussionCode(String speechDiscussionCode) {
        return speechDiscussionRepository.findByDiscussionCode(speechDiscussionCode);
    }

    @Override
    public SpeechStudy findByStudyCode(String speechStudyCode) {
        return speechStudyRepository.findByStudyCode(speechStudyCode);
    }


}
