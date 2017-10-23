package com.qq.server.model;

import java.net.*;
import java.io.*;
import java.util.*;
import com.qq.server.manager.*;
import com.qq.common.*;
/*
 * this is the server for QQ
 */
public class MyQqServer implements Runnable{
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	public MyQqServer(){
		
	}
	public void CloseServer(){
		try{
			if(oos != null) oos.close();
			if(ois != null) ois.close();
			if(s != null && !s.isClosed())   s.close();
			if(ss != null && !ss.isClosed())  ss.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try{
			System.out.println("在端口监听");
			//在 9999 端口监听
			ss = new ServerSocket(ServerInfo.MyPort);
			//阻塞，等待连接
			while(true){
				s = ss.accept();
				//接受用户信息			
				ois =new ObjectInputStream(s.getInputStream());
				LoginInfo u = (LoginInfo) ois.readObject();
				Message m = new Message();
				oos =new ObjectOutputStream(s.getOutputStream());
				if(ManagerConnectUser.isOnLineUser(u.getUserName())){
					m.setMesType(MessType.isOnline);
					oos.writeObject(m);
					s.close();
				}
				else if (u.getPassWord().equals("123456")){
					m.setMesType(MessType.loginSucess);
					oos.writeObject(m);
					oos.flush();
					ServerConClientThread mySCCT = new ServerConClientThread(s);
					
					System.out.println("login "+u.getUserName());
					
					ManagerConnectUser.addClientThread(u.getUserName(),mySCCT);
					mySCCT.start();
				}
				else{
					m.setMesType(MessType.wrongPW);
					oos.writeObject(m);
					s.close();
				}
			}
		}catch(Exception e){
			if (e.getLocalizedMessage().equals("Socket closed"))
				System.out.println(e.getLocalizedMessage());
			else 
				e.getStackTrace();
		}finally{
			
		}
	}
	
}
