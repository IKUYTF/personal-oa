package co.jp.ths.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.jp.ths.api.entity.User;

@Mapper
public interface UserDao {
    
    List<User> selectAll();
    
    List<User> selectByKeyword(String keyword);
    
    int countAll();
    
    int selectOneByName(String keyword);
    
    void insert(User tbl);
    
    void update(User tbl);
    
    User validateUserPass(User tbl);
    
    void delete(String username);
}
