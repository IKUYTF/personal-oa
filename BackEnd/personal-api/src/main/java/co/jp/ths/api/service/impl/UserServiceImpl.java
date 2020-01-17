package co.jp.ths.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.jp.ths.api.dao.UserDao;
import co.jp.ths.api.entity.User;
import co.jp.ths.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao dao;
    
    @Override
    public List<User> getUserByKeyword(String keyword) {
        return dao.selectByKeyword(keyword);
    }
    
    @Override
    public List<User> getAllUsers() {
        return dao.selectAll();
    }
    
    @Override
    public int checkUser(String keyword) {
    	return dao.selectOneByName(keyword);
    }
    
    @Override
    public int getTotalNum() {
        return dao.countAll();
    }
    
    @Override
    public void addUser(User tbl) {
        dao.insert(tbl);
    }
    
    @Override
    public void editUser(User tbl) {
        dao.update(tbl);
    }
    
    @Override
    public User validateUser(User tbl) {
        return dao.validateUserPass(tbl);
    }

    @Override
    public void deleteUser(String username) {
    	dao.delete(username);
    }

}
