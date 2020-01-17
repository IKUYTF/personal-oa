package co.jp.ths.api.entity;

import java.sql.Timestamp;

public class User {
	private String username;
	private String password;
	private String shopAuth;
	private String userAuth;
	private Boolean shopAuthFlg;
	private Boolean userAuthFlg;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String createTimeStr;
	private String updateTimeStr;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getShopAuth() {
		return shopAuth;
	}

	public void setShopAuth(String shopAuth) {
		this.shopAuth = shopAuth;
	}

	public String getUserAuth() {
		return userAuth;
	}

	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}

	public Boolean getShopAuthFlg() {
		return shopAuthFlg;
	}

	public void setShopAuthFlg(Boolean shopAuthFlg) {
		this.shopAuthFlg = shopAuthFlg;
	}

	public Boolean getUserAuthFlg() {
		return userAuthFlg;
	}

	public void setUserAuthFlg(Boolean userAuthFlg) {
		this.userAuthFlg = userAuthFlg;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

}
