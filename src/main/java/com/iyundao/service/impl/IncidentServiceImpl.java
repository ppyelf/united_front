package com.iyundao.service.impl;

import com.iyundao.entity.Incident;
import com.iyundao.repository.IncidentRepository;
import com.iyundao.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: IncidentServiceImpl
 * @project:
 * @author: 13620
 * @Date: 2019/8/3
 * @Description:    事件
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IncidentServiceImpl implements IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Override
    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    @Override
    public Incident saveIncident(String title, String content, String dataTime) {
        Incident incident = new Incident();
        incident.setCreatedDate(new Date());
        incident.setLastModifiedDate(new Date());
        incident.setTitle(title);
        incident.setContent(content);
        incident.setDataTime(dataTime);
        return incidentRepository.save(incident);
    }

    @Override
    public Incident findById(String incidentId) {
        return incidentRepository.find(incidentId);
    }

    @Override
    public List<Incident> findAllById(String[] incidentId) {
        return incidentRepository.findByIds(incidentId);
    }

    @Override
    public void deleteAll(List<Incident> incidentList) {
        incidentRepository.deleteAll(incidentList);
    }

    @Override
    public Incident findByCode(String incidentCode) {
        return incidentRepository.findByCode(incidentCode);
    }
}
