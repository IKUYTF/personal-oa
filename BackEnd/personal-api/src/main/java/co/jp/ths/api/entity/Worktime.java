package co.jp.ths.api.entity;

public class Worktime {
	private String date;
	private String startTime;
	private String endTime;
	private String type;
	private String restMemo;
	private String user;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRestMemo() {
		return restMemo;
	}
	public void setRestMemo(String restMemo) {
		this.restMemo = restMemo;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
