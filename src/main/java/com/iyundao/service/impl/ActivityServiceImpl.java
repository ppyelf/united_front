package com.iyundao.service.impl;

import com.alibaba.fastjson.JSON;
import com.iyundao.base.Page;
import com.iyundao.base.Pageable;
import com.iyundao.base.utils.FileUtils;
import com.iyundao.base.utils.JsonResult;
import com.iyundao.base.utils.JsonUtils;
import com.iyundao.entity.*;
import com.iyundao.repository.*;
import com.iyundao.service.ActivityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @ClassName: ActivityServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 13:42
 * @Description: 实现 - 活动
 * @Version: V2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityServiceImpl implements ActivityService {

    @Value("${server.upload}")
    private String uploadPath;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityFileRepository activityFileRepository;

    @Autowired
    private ActivityImageRepository activityImageRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ReleaseSubjectRepository releaseSubjectRepository;

    @Autowired
    private ReleaseDepartRepository releaseDepartRepository;

    @Autowired
    private ReleaseGroupRepository releaseGroupRepository;

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private ActivityFrequencyRepository activityFrequencyRepository;

    @Autowired
    private ActivityVideoRepository activityVideoRepository;

    @Autowired
    private ActivityTextRepository activityTextRepository;

    @Override
    @Modifying
    public Activity save(Activity activity, List<Attendance> attendances, List<ActivityFile> activityFiles, List<ActivityImage> activityImages, String subjectId, String departId, String groupId) {
        activity = activityRepository.save(activity);
        Set<Attendance> set = new HashSet<>();
        Set<ActivityFile> fileSet = new HashSet<>();
        Set<ActivityImage> imageSet = new HashSet<>();
        for (Attendance attendance : attendances) {
            attendance.setActivity(activity);
            attendanceRepository.save(attendance);
            set.add(attendance);
        }
        for (ActivityFile activityFile : fileSet) {
            activityFile.setActivity(activity);
            fileSet.add(activityFile);
        }
        activityFileRepository.saveAll(fileSet);
        for (ActivityImage activityImage : imageSet) {
            activityImage.setActivity(activity);
            imageSet.add(activityImage);
        }
        activityImageRepository.saveAll(imageSet);
        activity.setAttendances(set);
        activity.setActivityFiles(fileSet);
        activity.setActivityImages(imageSet);
        activity = activityRepository.save(activity);
        if (StringUtils.isNotBlank(subjectId)) {
            ReleaseSubject rs = new ReleaseSubject();
            rs.setActivity(activity);
            rs.setCreatedDate(new Date());
            rs.setLastModifiedDate(new Date());
            rs.setSubjectId(subjectId);
            releaseSubjectRepository.save(rs);
        }
        if (StringUtils.isNotBlank(departId)) {
            ReleaseDepart rd = new ReleaseDepart();
            rd.setActivity(activity);
            rd.setCreatedDate(new Date());
            rd.setLastModifiedDate(new Date());
            rd.setDepartId(departId);
            releaseDepartRepository.save(rd);
        }
        if (StringUtils.isNotBlank(groupId)) {
            ReleaseGroups rg = new ReleaseGroups();
            rg.setActivity(activity);
            rg.setCreatedDate(new Date());
            rg.setLastModifiedDate(new Date());
            rg.setGroupsId(groupId);
            releaseGroupRepository.save(rg);
        }
        return activity;
    }

    @Override
    public Activity find(String id) {

        return activityRepository.find(id);
    }

    @Override
    public void delete(Activity activity) {
        List<Attendance> attendances = attendanceRepository.findByActivityId(activity.getId());
        List<ActivityFile> activityFiles = activityFileRepository.findByActivityId(activity.getId());
        List<ActivityImage> activityImages = activityImageRepository.findByActivityId(activity.getId());
        List<Sign> signs = signRepository.findByActivityId(activity.getId());
        attendanceRepository.deleteAll(attendances);
        activityFileRepository.deleteAll(activityFiles);
        activityImageRepository.deleteAll(activityImages);
        signRepository.deleteAll(signs);
        activityRepository.delete(activity);
    }

    @Override
    public ActivityFile saveFile(ActivityFile file) {
        return activityFileRepository.save(file);
    }

    @Override
    public ActivityImage saveImage(ActivityImage image) {
        return activityImageRepository.save(image);
    }

    @Override
    public List<ActivityFile> findActivityFilesByIds(String[] activityFileIds) {
        List<ActivityFile> activityFiles = activityFileRepository.findByIds(activityFileIds);
        return CollectionUtils.isEmpty(activityFiles)
                ? new ArrayList<>()
                : activityFiles;
    }

    @Override
    public List<ActivityImage> findActivityImageByIds(String[] activityImageIds) {
        List<ActivityImage> activityImages = activityImageRepository.findByIds(activityImageIds);
        return CollectionUtils.isEmpty(activityImages)
                ? new ArrayList<>()
                : activityImages;
    }

    @Override
    public void delFileByIds(String[] ids) {
        List<ActivityFile> activityFiles = activityFileRepository.findByIds(ids);
        activityFileRepository.deleteAll(activityFiles);
    }

    @Override
    public void delImage(String[] ids) {
        List<ActivityImage> activityImages = activityImageRepository.findByIds(ids);
        activityImageRepository.deleteAll(activityImages);
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAllForList();
    }

    @Override
    public Page<Activity> findAllForPage(Pageable pageable) {

        return activityRepository.findPage(pageable);
    }

    @Override
    public Sign saveUserSign(Sign sign) {
        sign = signRepository.save(sign);
        return sign;
    }

    @Override
    public ActivityFile findByIds(String id) {
        return activityFileRepository.find(id);
    }

    @Override
    public JsonResult saveFrequency(MultipartFile file, User user, Activity activity) {
        ActivityFrequency af = new ActivityFrequency();
        af.setCreatedDate(new Date());
        af.setLastModifiedDate(new Date());
        af.setUser(user);
        af.setActivity(activity);
        Map<String,String> map = FileUtils.uploadFile(file,af,uploadPath);
        if (map == null){
            return JsonResult.failure(601,"上传失败");
        }
        af.setUrl(map.get("url"));
        af.setSuffix(map.get("suffix"));
        af.setName(map.get("name"));
        af = activityFrequencyRepository.save(af);
        JsonResult jsonResult = JsonResult.success();
        jsonResult.setData(JsonUtils.getJson(af));

        return jsonResult;
    }

    @Override
    public List<ActivityFrequency> findAllFrequency() {
        return activityFrequencyRepository.findAllDesc();
    }

    @Override
    public List<ActivityFrequency> findAllFrequencyByActivity(Activity activity) {
        return activityFrequencyRepository.findAllFrequencyByActivity(activity);
    }

    @Override
    public List<ActivityFrequency> findAllFrequencyByIds(String[] frequencyIds) {
        return activityFrequencyRepository.findByIds(frequencyIds);
    }

    @Override
    public void deleteFrequencys(List<ActivityFrequency> af) {
        for (ActivityFrequency activityFrequency : af) {
            FileUtils.deleteFile(uploadPath + activityFrequency.getUrl());
            activityFrequencyRepository.delete(activityFrequency);
        }
    }

    @Override
    public JsonResult saveImageWang(MultipartFile file, User user, Activity activity) {
        ActivityImage ai = new ActivityImage();
        ai.setCreatedDate(new Date());
        ai.setLastModifiedDate(new Date());
        ai.setUser(user);
        ai.setActivity(activity);
        Map<String,String> map = FileUtils.uploadFile(file,ai,uploadPath);
        if (map == null){
            return JsonResult.failure(601,"上传失败");
        }
        ai.setUrl(map.get("url"));
        ai.setSuffix(map.get("suffix"));
        ai.setName(map.get("name"));
        ai = activityImageRepository.save(ai);
        JsonResult jsonResult = JsonResult.success();
        jsonResult.setData(JsonUtils.getJson(ai));

        return jsonResult;
    }

    @Override
    public List<ActivityImage> findAllImage() {
        return activityImageRepository.findAllDesc();
    }

    @Override
    public List<ActivityImage> findAllImageByActivity(Activity activity) {
        return activityImageRepository.findAllImageByActivity(activity);
    }

    @Override
    public List<ActivityImage> findAllImageByIds(String[] imageIds) {
        return activityImageRepository.findByIds(imageIds);
    }

    @Override
    public void deleteImages(List<ActivityImage> ai) {
        for (ActivityImage activityImage : ai) {
            FileUtils.deleteFile(uploadPath + activityImage.getUrl());
            activityImageRepository.delete(activityImage);
        }
    }

    @Override
    public JsonResult saveVideo(MultipartFile file, User user, Activity activity) {
        ActivityVideo av = new ActivityVideo();
        av.setCreatedDate(new Date());
        av.setLastModifiedDate(new Date());
        av.setUser(user);
        av.setActivity(activity);
        Map<String,String> map = FileUtils.uploadFile(file,av,uploadPath);
        if (map == null){
            return JsonResult.failure(601,"上传失败");
        }
        av.setUrl(map.get("url"));
        av.setSuffix(map.get("suffix"));
        av.setName(map.get("name"));
        av = activityVideoRepository.save(av);
        JsonResult jsonResult = JsonResult.success();
        jsonResult.setData(JsonUtils.getJson(av));
        return jsonResult;
    }

    @Override
    public List<ActivityVideo> findAllVideo() {
        return activityVideoRepository.findAllVideo();
    }

    @Override
    public List<ActivityVideo> findAllVideoByActivity(Activity activity) {
        return activityVideoRepository.findAllVideoByActivity(activity);
    }

    @Override
    public List<ActivityVideo> findAllVideoByIds(String[] videoIds) {
        return activityVideoRepository.findByIds(videoIds);
    }

    @Override
    public void deleteVideo(List<ActivityVideo> activityVideoList) {
        for (ActivityVideo activityVideo : activityVideoList) {
            FileUtils.deleteFile(uploadPath + activityVideo.getUrl());
            activityVideoRepository.delete(activityVideo);
        }
    }

    @Override
    public ActivityText saveText(String content, User user, Activity activity) {
        ActivityText at = new ActivityText();
        at.setCreatedDate(new Date());
        at.setLastModifiedDate(new Date());
        at.setContent(content);
        at.setUser(user);
        at.setActivity(activity);
        at= activityTextRepository.save(at);

        return at;
    }

    @Override
    public List<ActivityText> findAllText() {
        return activityTextRepository.findAllText();
    }

    @Override
    public List<ActivityText> findAllTextByActivity(Activity activity) {
        return activityTextRepository.findAllTextByActivity(activity);
    }

    @Override
    public List<ActivityText> findAllTextByIds(String[] textIds) {
        return activityTextRepository.findByIds(textIds);
    }

    @Override
    public void deleteText(List<ActivityText> at) {
        activityTextRepository.deleteAll(at);
    }


}
