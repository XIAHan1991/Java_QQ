package com.qq.client.model;

import java.net.*;
import java.io.*;
import java.util.*;
import com.qq.client.manager.*;
import com.qq.common.*;
/*
 * Connect the server 
 */
public class QqConServer {

	//TODO delete the static Using HashMap to manage the socket
	public Socket s;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	public QqConServer(){
		try {
			s = new Socket(ServerInfo.IpAdress,ServerInfo.MyPort);
			
		} catch (Exception e) {
			if(e.getLocalizedMessage().equals("Connection refused"))
				System.out.println("Can not find the Server");
			else e.printStackTrace();
		}finally{
			
		}
	}
	public String sendLoginInfo(LoginInfo u){
		Message ms = null;
		String msType = null;
		if (s != null){
			try{
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(u);
				oos.flush();
				ois = new ObjectInputStream(s.getInputStream());
				ms = (Message) ois.readObject();
				if (ms.getMesType().equals(MessType.loginSucess)){
					ClientConServerThread myConThread = new ClientConServerThread(s);
					ManagerClientThread.addClientThread(u.getUserName(),myConThread);
					myConThread.start();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			msType = ms.getMesType();
		}
		else msType = MessType.notFindServer;
		return msType;
	}
}
