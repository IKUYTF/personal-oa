package co.jp.ths.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.ths.api.dao.WordsDao;
import co.jp.ths.api.entity.Words;
import co.jp.ths.api.service.WordsService;

@Service
public class WordsServiceImpl implements WordsService {
    
    @Autowired
    private WordsDao dao;
    
    @Override
    public List<Words> getWordsByKeyword(String keyword) {
        return dao.selectByKeyword(keyword);
    }
    
    @Override
    public List<Words> getAllWords() {
        return dao.selectAll();
    }
    
    @Override
    public int getTotalNum() {
        return dao.countAll();
    }
    
    @Override
    public void addWords(Words tbl) {
        dao.insert(tbl);
    }
    
    @Override
    public void editWords(Words tbl) {
        dao.update(tbl);
    }
    

}
