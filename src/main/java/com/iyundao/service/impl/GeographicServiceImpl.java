package com.iyundao.service.impl;

import com.iyundao.entity.Activity;
import com.iyundao.entity.GeographicPosition;
import com.iyundao.repository.GeographicRepository;
import com.iyundao.service.GeographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: GeographicServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/7
 * @Description:
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GeographicServiceImpl implements GeographicService{

    @Autowired
    private GeographicRepository geographicRepository;

    @Override
    public GeographicPosition save(String name, String x, String y, int type, Activity activity) {
        GeographicPosition gp = new GeographicPosition();
        gp.setCreatedDate(new Date());
        gp.setLastModifiedDate(new Date());
        gp.setName(name);
        gp.setLongitude(x);
        gp.setLatitude(y);
        for (GeographicPosition.XY_TYPE xy : GeographicPosition.XY_TYPE.values()){
            if (xy.ordinal()==type){
                gp.setType(xy);
            }
        }
        gp.setActivity(activity);
        return geographicRepository.save(gp);
    }

    @Override
    public List<GeographicPosition> findAll() {
        return geographicRepository.findAll();
    }

    @Override
    public List<GeographicPosition> findByActivity(Activity activity) {
        return geographicRepository.findByActivity(activity);
    }

    @Override
    public void deleteByActivity(Activity activity) {
        geographicRepository.deleteByActivity(activity);
    }

    @Override
    public List<GeographicPosition> findAllByIds(String[] geographicIds) {
        return geographicRepository.findByIds(geographicIds);
    }

    @Override
    public void delete(List<GeographicPosition> geographicPositions) {
        geographicRepository.deleteAll(geographicPositions);
    }
}
