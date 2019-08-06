package com.iyundao.service;

import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.entity.SpeechArticle;
import com.iyundao.entity.SpeechDiscussion;
import com.iyundao.entity.SpeechStudy;
import com.iyundao.entity.User;

import java.util.List;

/**
 * @ClassName: SpeechService
 * @project:
 * @author: 13620
 * @Date: 2019/8/2
 * @Description: 言论记录
 * @Version: V1.0
 */
public interface SpeechService {

    /**
     * 添加实体
     * @param title
     * @param content
     * @param url
     * @param user
     * @param sort
     * @param time
     * @return
     */
    SpeechArticle saveSpeechArticle(String title, String content, String url, User user, int sort, String time);

    /**
     * 发表文章列表分页
     * @return
     */
    List<SpeechArticle> findAllSpeechArticle();

    /**
     * 通过文章id找到实体
     * @param speechArticleId
     * @return
     */
    SpeechArticle findSpeechArticleById(String speechArticleId);

    /**
     * 通过文章ids找到所有实体
     * @param speechArticleIds
     * @return
     */
    List<SpeechArticle> findSpeechArticleByIds(String[] speechArticleIds);

    /**
     * 删除文章实体
     * @param speechArticleList
     */
    void deleteAllSpeechArticle(List<SpeechArticle> speechArticleList);

    /**
     * 根据用户实体找到所有文章
     * @param user
     * @return
     */
    List<SpeechArticle> findAllSpeechArticleByUser(User user);

    /**
     * 理论研究列表
     * @return
     */
    List<SpeechStudy> findAllSpeechStudy();

    /**
     * 添加理论研究
     * @param title
     * @param content
     * @param state
     * @param startTime
     * @param endTime
     * @param result
     * @return
     */
    SpeechStudy saveSpeechStudy(String title, String content,User user, int state, String startTime, String endTime, String result);

    /**
     * 通过用户实体找到所有所有理论研究
     * @param user
     * @return
     */
    List<SpeechStudy> findAllSpeechStudyByUser(User user);

    /**
     * 通过理论研究id找到所有实体
     * @param speechStudyId
     * @return
     */
    SpeechStudy findSpeechStudyById(String speechStudyId);

    /**
     * 通过理论研究ids找到所有实体
     * @param speechStudyIds
     * @return
     */
    List<SpeechStudy> findSpeechStudyByIds(String[] speechStudyIds);

    /**
     * 删除实体
     * @param speechStudyList
     */
    void deleteAllSpeechStudy(List<SpeechStudy> speechStudyList);

    /**
     * 通过参与讨论信息id查找实体
     * @param speechDiscussionId
     * @return
     */
    SpeechDiscussion findSpeechDiscussionById(String speechDiscussionId);



    /**
     * 根据speechArticleCode查找实体
     * @return
     */
    SpeechArticle findByArticleCode(String speechArticleCode);

    /**
     *根据SpeechDiscussion查找实体
     * @param speechDiscussionCode
     * @return
     */
    SpeechDiscussion findByDiscussionCode(String speechDiscussionCode);

    /**
     * 根据SpeechStudy查找实体
     * @param speechStudyCode
     * @return
     */
    SpeechStudy findByStudyCode(String speechStudyCode);
}
