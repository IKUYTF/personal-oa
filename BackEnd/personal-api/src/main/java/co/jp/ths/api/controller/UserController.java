package co.jp.ths.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.jp.ths.api.entity.User;
import co.jp.ths.api.service.UserService;

/**
 * 
 * @author iku 2019/05/14
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/user-get/{username}")
    public List<User> getUserByKeyword(@PathVariable String username) {
        String keyword = "%" + username + "%";
        
        List<User> userList = userService.getUserByKeyword(keyword);
    	for(User user : userList) {
    		user.setCreateTimeStr(user.getCreateTime().toString().substring(0, 16));
    		;
    		if ("1".equals(user.getShopAuth())) {
    			user.setShopAuthFlg(true);
    		} else {
    			user.setShopAuthFlg(false);
    		}

    		if ("1".equals(user.getUserAuth())) {
    			user.setUserAuthFlg(true);
    		} else {
    			user.setUserAuthFlg(false);
    		}
    		if(user.getUpdateTime() != null) {
    			user.setUpdateTimeStr(user.getUpdateTime().toString().substring(0, 16));
    		}
    	}
    	
       return userList;
    }
    

    @GetMapping(value = "/user-getAll")
    public List<User> getAllUsers() {

    	List<User> userList = userService.getAllUsers();
    	for(User user : userList) {
    		user.setCreateTimeStr(user.getCreateTime().toString().substring(0, 16));
    		;
    		if ("1".equals(user.getShopAuth())) {
    			user.setShopAuthFlg(true);
    		} else {
    			user.setShopAuthFlg(false);
    		}

    		if ("1".equals(user.getUserAuth())) {
    			user.setUserAuthFlg(true);
    		} else {
    			user.setUserAuthFlg(false);
    		}
    		if(user.getUpdateTime() != null) {
    			user.setUpdateTimeStr(user.getUpdateTime().toString().substring(0, 16));
    		}
    	}
    	
       return userList;
    }
    
    @GetMapping(value = "/user-check/{username}")
    public int checkUser(@PathVariable String username) {

        return userService.checkUser(username);
    }
    
    @PostMapping(value = "/user-insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> addNewUser(@RequestBody User tbl) {

		tbl.setShopAuth("0");
    	if (tbl.getShopAuthFlg() == true) {
    		tbl.setShopAuth("1");
    	} 
    	
    	tbl.setUserAuth("0");
    	if (tbl.getUserAuthFlg() == true) {
    		tbl.setUserAuth("1");
    	} 
        
        userService.addUser(tbl);
        return getAllUsers();
    }
    
    @PostMapping(value = "/user-update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> updateUser(@RequestBody User tbl) {
		tbl.setShopAuth("0");
    	if (tbl.getShopAuthFlg() == true) {
    		tbl.setShopAuth("1");
    	} 
    	
    	tbl.setUserAuth("0");
    	if (tbl.getUserAuthFlg() == true) {
    		tbl.setUserAuth("1");
    	} 
    	
    	userService.editUser(tbl);

        return getAllUsers();
    }
    
    @GetMapping(value = "/user-exist/{username}/{password}")
    public User checkUser(@PathVariable String username, @PathVariable String password) {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	
    	user = userService.validateUser(user);
    	
    	if ("1".equals(user.getShopAuth())) {
			user.setShopAuthFlg(true);
		} else {
			user.setShopAuthFlg(false);
		}

		if ("1".equals(user.getUserAuth())) {
			user.setUserAuthFlg(true);
		} else {
			user.setUserAuthFlg(false);
		}

        return user;
    }
    
	@GetMapping(value = "/user-delete/{username}")
	public List<User> deleteUser(@PathVariable String username) {
		userService.deleteUser(username);
        return getAllUsers();
	}
   
    
}
