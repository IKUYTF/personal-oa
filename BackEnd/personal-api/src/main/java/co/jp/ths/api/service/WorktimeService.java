package co.jp.ths.api.service;

import java.util.List;

import co.jp.ths.api.entity.Worktime;

public interface WorktimeService {
    
    List<Worktime> getAllWorktime(Worktime worktime);
    
    int getTotalNum();
    
    void addWorktime(Worktime worktime);
    
    void editWorktime(Worktime worktime);
    

}
