package co.jp.ths.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.ths.api.dao.TaskDao;
import co.jp.ths.api.entity.Task;
import co.jp.ths.api.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskDao dao;
    
    @Override
    public List<Task> getTaskByKeyword(String keyword) {
        return dao.selectByKeyword(keyword);
    }
    
    @Override
    public List<Task> getAllTask() {
        return dao.selectAll();
    }
    
    @Override
    public List<Task> getUnfinish() {
    	return dao.selectUnfinish();
    }
    
    @Override
    public List<Task> getFinish() {
    	return dao.selectFinish();
    }
    
    @Override
    public List<Task> getMemo() {
    	return dao.selectMemo();
    }

    @Override
    public int getTotalNum() {
        return dao.countAll();
    }
    
    @Override
    public void addTask(Task tbl) {
        dao.insert(tbl);
    }
    
    @Override
    public void editTask(Task tbl) {
        dao.update(tbl);
    }
    

}
