package com.member.entity;

public class AuthResponse {

	private String message;
	private Integer role;
	private Integer statusCode;
	private LoggedInUser user;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public LoggedInUser getUser() {
		return user;
	}
	public void setUser(LoggedInUser user) {
		this.user = user;
	}
	
}
