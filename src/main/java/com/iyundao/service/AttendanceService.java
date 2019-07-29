package com.iyundao.service;

import com.iyundao.entity.Activity;
import com.iyundao.entity.Attendance;

import java.util.List;

/**
 * @ClassName: AttendanceService
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 13:51
 * @Description: 服务 - 出勤时间
 * @Version: V2.0
 */
public interface AttendanceService {

    /**
     * 根据活动查询实体集合
     * @param Id
     * @return
     */
    List<Attendance> findByActivityId(String Id);

    /**
     * 根据IDS获取集合信息
     * @param attendanceIds
     * @return
     */
    List<Attendance> findByIds(String[] attendanceIds);

    /**
     * 保存实体
     * @param activity
     * @param startTime
     * @param endTime
     * @param day
     * @param type
     * @param axisx
     * @param axisy
     * @param area
     * @return
     */
    Attendance save(Activity activity, String startTime, String endTime, int day, int type, String axisx, String axisy, String area);
}
