package com.iyundao.service;


import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActivityService
 * @project: ayundao
 * @author: 念
 * @Date: 2019/6/5 13:42
 * @Description: 服务 - 活动
 * @Version: V2.0
 */
public interface ActivityService {

    /**
     * 保存实体
     * @param activity
     * @param attendance
     * @param activityFiles
     * @param activityImages
     * @param subjectId
     * @param departId
     * @param groupId
     * @return
     */
    Activity save(Activity activity, List<Attendance> attendance, List<ActivityFile> activityFiles, List<ActivityImage> activityImages, String subjectId, String departId, String groupId);

    /**
     * 根据Id查找实体
     * @param id
     * @return
     */
    Activity find(String id);

    /**
     * 删除实体
     * @param activity
     */
    void delete(Activity activity);

    /**
     * 保存活动文件
     * @param file
     * @return
     */
    ActivityFile saveFile(ActivityFile file);

    /**
     * 保存活动图片
     * @param image
     * @return
     */
    ActivityImage saveImage(ActivityImage image);

    /**
     * 根据IDS获取活动文件集合
     * @param activityFileIds
     * @return
     */
    List<ActivityFile> findActivityFilesByIds(String[] activityFileIds);

    /**
     * 根据IDS获取活动图片集合
     * @param activityImageIds
     * @return
     */
    List<ActivityImage> findActivityImageByIds(String[] activityImageIds);

    /**
     * 根据IDS删除活动文件
     * @param ids
     * @return
     */
    void delFileByIds(String[] ids);

    /**
     * yundaoSERVER
     *  YD_2016
     * 根据IDS删除活动图片
     * @param ids
     */
    void delImage(String[] ids);

    /**
     * 查询活动列表
     * @return
     */
    List<Activity> findAll();

    /**
     * 活动列表分页
     * @return
     * @param pageable
     */
    Page<Activity> findAllForPage(Pageable  pageable);

    /**
     * 保存用户签到流程
     * @param sign
     * @return
     */
    Sign saveUserSign(Sign sign);

    /**
     * 根据IDS获取实体信息
     * @param id
     * @return
     */
    ActivityFile findByIds(String id);



    /**
     * 添加
     * @param file
     * @param user
     * @param activity
     */
    JsonResult saveFrequency(MultipartFile file, User user, Activity activity);

    /**
     * 所有音频
     * @return
     */
    List<ActivityFrequency> findAllFrequency();

    /**
     * 根据活动实体找到所有音频
     * @param activity
     * @return
     */
    List<ActivityFrequency> findAllFrequencyByActivity(Activity activity);


    /**
     *根据音频ids找到所有音频
     * @param frequencyIds
     * @return
     */
    List<ActivityFrequency> findAllFrequencyByIds(String[] frequencyIds);

    /**
     * 删除音频
     * @param af
     */
    void deleteFrequencys(List<ActivityFrequency> af);


    /**
     * 上传图片
     * @param file
     * @param user
     * @param activity
     * @return
     */
    JsonResult saveImageWang(MultipartFile file, User user, Activity activity);

    /**
     * 图片列表
     * @return
     */
    List<ActivityImage> findAllImage();

    /**
     * 根据活动实体找到所有图片
     * @param activity
     * @return
     */
    List<ActivityImage> findAllImageByActivity(Activity activity);


    /**
     * 根据图片ids找到所有实体
     * @param imageIds
     * @return
     */
    List<ActivityImage> findAllImageByIds(String[] imageIds);

    /**
     * 删除所有图片实体
     * @param ai
     */
    void deleteImages(List<ActivityImage> ai);

    /**
     * 上传小视频
     * @param file
     * @param user
     * @param activity
     * @return
     */
    JsonResult saveVideo(MultipartFile file, User user, Activity activity);

    /**
     * 小视频列表分页
     * @return
     */
    List<ActivityVideo> findAllVideo();

    /**
     *根据活动实体找到所有小视频
     * @param activity
     * @return
     */
    List<ActivityVideo> findAllVideoByActivity(Activity activity);

    /**
     * 根据小视频ids找到小视频实体
     * @param videoIds
     * @return
     */
    List<ActivityVideo> findAllVideoByIds(String[] videoIds);

    /**
     * 删除小视频实体
     * @param activityVideoList
     */
    void deleteVideo(List<ActivityVideo> activityVideoList);

    /**
     * 添加现场文字
     * @param content
     * @param user
     * @param activity
     * @return
     */
    ActivityText saveText(String content, User user, Activity activity);

    /**
     * 现场文字列表
     * @return
     */
    List<ActivityText> findAllText();

    /**
     * 根据活动实体找到现场文字
     * @param activity
     * @return
     */
    List<ActivityText> findAllTextByActivity(Activity activity);

    /**
     * 根据现场文字ids找到所有实体
     * @param textIds
     * @return
     */
    List<ActivityText> findAllTextByIds(String[] textIds);

    /**
     * 删除现场文字实体
     * @param at
     */
    void deleteText(List<ActivityText> at);

    /**
     *根据类型判断排行的规矩，返回实体
     * @param type
     * @return
     */
    List<Map<String,Object>> findAllByType(int type);
}
