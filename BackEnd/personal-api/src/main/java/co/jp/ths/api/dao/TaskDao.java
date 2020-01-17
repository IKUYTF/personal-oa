package co.jp.ths.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.ths.api.entity.Task;

@Mapper
public interface TaskDao {
    
    List<Task> selectAll();
    
    List<Task> selectUnfinish();
    
    List<Task> selectFinish();

    List<Task> selectMemo();
    
    List<Task> selectByKeyword(String keyword);
    
    int countAll();
    
    void insert(Task tbl);
    
    void update(Task tbl);
    
}
