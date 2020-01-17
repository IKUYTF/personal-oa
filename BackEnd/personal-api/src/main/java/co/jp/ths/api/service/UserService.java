package co.jp.ths.api.service;

import java.util.List;

import co.jp.ths.api.entity.User;

public interface UserService {
    
    List<User> getUserByKeyword(String keyword);
    
    List<User> getAllUsers();
    
    int getTotalNum();
    
    int checkUser(String keyword);
    
    void addUser(User tbl);
    
    void editUser(User tbl);
    
    User validateUser(User tbl);
    
    void deleteUser(String username);

}
