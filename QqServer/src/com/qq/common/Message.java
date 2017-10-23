package com.qq.common;

import java.io.Serializable;
import java.util.Vector;

/*
 * To get the information of message and transmittal in the Internet
 * MesType 1: login successful
 *         2: login fail
 *         3: normal message
 */

public class Message implements Serializable{
	
	private String mesType;
	private String sender;
	private String reciever;
	private String message;
	private String sendTime;
	private Vector<LoginInfo> allUserInfos = new Vector<LoginInfo>();

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSendTime() {
		return sendTime;
	}

	public Vector<LoginInfo> getAllUserInfos() {
		return allUserInfos;
	}

	public void setAllUserInfos(Vector<LoginInfo> allUserInfos) {
		this.allUserInfos = allUserInfos;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	
	/*public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public void addUserInfo(LoginInfo userInfo){
		allUserInfos.add(userInfo);
	}
	public int getSizeInfo(){
		return allUserInfos.size();
	}
	public LoginInfo getUserInfo(int index){
		LoginInfo useInfo = null;
		if(index < getSizeInfo() && getSizeInfo()!= 0 && index >= 0)
			useInfo = allUserInfos.get(index);
		return useInfo;
	}*/
	
	
}
