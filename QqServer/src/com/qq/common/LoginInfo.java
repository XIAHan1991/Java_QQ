package com.qq.common;

import java.io.Serializable;
/*
 * Define the information of Users
 */

public class LoginInfo implements Serializable{

	private String messType;
	private String userName;
	private String passWord;
	private String logState;
	private String jpgDir;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMessType() {
		return messType;
	}
	public void setMessType(String messType) {
		this.messType = messType;
	}
	public String getLogState() {
		return logState;
	}
	public void setLogState(String logState) {
		this.logState = logState;
	}
	public String getJpgDir() {
		return jpgDir;
	}
	public void setJpgDir(String jpgDir) {
		this.jpgDir = jpgDir;
	}
	
	
	
}
