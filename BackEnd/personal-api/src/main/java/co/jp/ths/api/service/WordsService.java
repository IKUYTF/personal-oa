package co.jp.ths.api.service;

import java.util.List;

import co.jp.ths.api.entity.Words;

public interface WordsService {
    
    List<Words> getWordsByKeyword(String keyword);
    
    List<Words> getAllWords();
    
    int getTotalNum();
    
    void addWords(Words tbl);
    
    void editWords(Words tbl);
    

}
