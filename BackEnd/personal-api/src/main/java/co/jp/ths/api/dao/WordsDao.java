package co.jp.ths.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.ths.api.entity.Words;

@Mapper
public interface WordsDao {
    
    List<Words> selectAll();
    
    List<Words> selectByKeyword(String keyword);
    
    int countAll();
    
    void insert(Words tbl);
    
    void update(Words tbl);
    
}
