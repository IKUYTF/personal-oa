package co.jp.ths.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.ths.api.entity.Worktime;

@Mapper
public interface WorktimeDao {
    
    List<Worktime> selectAll(Worktime worktime);
    
    int countAll();
    
    void insert(Worktime worktime);
    
    void update(Worktime worktime);
    
}
