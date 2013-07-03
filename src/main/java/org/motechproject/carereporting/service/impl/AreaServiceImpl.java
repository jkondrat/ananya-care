package org.motechproject.carereporting.service.impl;

import org.motechproject.carereporting.dao.AreaDao;
import org.motechproject.carereporting.dao.LevelDao;
import org.motechproject.carereporting.domain.AreaEntity;
import org.motechproject.carereporting.domain.LevelEntity;
import org.motechproject.carereporting.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private LevelDao levelDao;

    @Override
    @Transactional
    public Set<AreaEntity> getAllAreas() {
        return areaDao.findAll();
    }

    @Override
    @Transactional
    public AreaEntity getAreaById(Integer areaId) {
        return areaDao.findById(areaId);
    }

    @Override
    @Transactional
    public Set<LevelEntity> getAllLevels() {
        return levelDao.findAll();
    }

    @Override
    @Transactional
    public LevelEntity getLevelById(Integer levelId) {
        return levelDao.findById(levelId);
    }

    @Override
    @Transactional(readOnly = false)

    public void createNewArea(AreaEntity areaEntity) {
        areaDao.save(areaEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateArea(AreaEntity areaEntity) {
        areaDao.update(areaEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteArea(AreaEntity areaEntity) {
        areaDao.remove(areaEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public void createNewLevel(LevelEntity level) {
        levelDao.save(level);
    }
}
