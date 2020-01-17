package co.jp.ths.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.ths.api.dao.WorktimeDao;
import co.jp.ths.api.entity.Worktime;
import co.jp.ths.api.service.WorktimeService;

@Service
public class WorktimeServiceImpl implements WorktimeService {
    
    @Autowired
    private WorktimeDao dao;
    
    @Override
    public List<Worktime> getAllWorktime(Worktime worktime) {
        return dao.selectAll(worktime);
    }

    @Override
    public int getTotalNum() {
        return dao.countAll();
    }
    
    @Override
    public void addWorktime(Worktime worktime) {
        dao.insert(worktime);
    }
    
    @Override
    public void editWorktime(Worktime worktime) {
        dao.update(worktime);
    }
    

}
