package com.iyundao.service.impl;

import com.iyundao.entity.Activity;
import com.iyundao.entity.Attendance;
import com.iyundao.repository.AttendanceRepository;
import com.iyundao.service.ActivityService;
import com.iyundao.service.AttendanceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: AttendanceServiceImpl
 * @project: IYunDao
 * @author: 念
 * @Date: 2019/6/5 13:51
 * @Description: 实现 - 出勤时间
 * @Version: V2.0
 */
@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ActivityService activityService;

    @Override
    public List<Attendance> findByActivityId(String id) {
        List<Attendance> attendances = attendanceRepository.findByActivityId(id);
        return CollectionUtils.isEmpty(attendances)
                ? new ArrayList<>()
                : attendances;
    }

    @Override
    public List<Attendance> findByIds(String[] attendanceIds) {
        List<Attendance> attendances = attendanceRepository.findByIds(attendanceIds);
        return CollectionUtils.isEmpty(attendances)
                ? new ArrayList<>()
                : attendances;
    }

    @Override
    @Transactional
    public Attendance save(Activity activity, String startTime, String endTime, int day, int type, String axisx, String axisy, String area) {
        Attendance attendance = new Attendance();
        attendance.setCreatedDate(new Date());
        attendance.setLastModifiedDate(new Date());
        attendance.setStartTime(startTime);
        attendance.setEndTime(endTime);
        attendance.setDay(day);
        for (Attendance.ATTENDANCE_TYPE atype : Attendance.ATTENDANCE_TYPE.values()) {
            if (atype.ordinal() == type) {
                attendance.setAttendanceType(atype);
                break;
            }
        }
        attendance.setAxisx(axisx);
        attendance.setAxisy(axisy);
        attendance.setArea(area);
        attendance.setActivity(activity == null ? null : activity);
        return attendanceRepository.save(attendance);
    }

}
