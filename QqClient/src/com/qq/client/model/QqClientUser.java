package com.qq.client.model;

/*
 * to check the information of Login
 */
import com.qq.common.*;
public class QqClientUser {
	public String CheckUser(LoginInfo u){
		return (new QqConServer()).sendLoginInfo(u);
	}
}