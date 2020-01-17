package co.jp.ths.api.service;

import java.util.List;

import co.jp.ths.api.entity.Task;

public interface TaskService {
    
    List<Task> getTaskByKeyword(String keyword);
    
    List<Task> getAllTask();
    
    List<Task> getUnfinish();
    
    List<Task> getFinish();
    
    List<Task> getMemo();
    
    int getTotalNum();
    
    void addTask(Task tbl);
    
    void editTask(Task tbl);
    

}
