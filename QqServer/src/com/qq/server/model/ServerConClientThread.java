package com.qq.server.model;
import java.net.*;
import java.util.Vector;
import java.io.*;
import com.qq.common.*;
import com.qq.server.manager.*;
/*
 * to Create the thread to get message
 */
public class ServerConClientThread extends Thread{

	private Socket s;
	public ServerConClientThread(Socket s) {
		
		this.s = s;
	}
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		while(true){
			try{
				System.out.println("wait to get information");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m =(Message) ois.readObject();
				if(m.getMesType().equals(MessType.comMessage)){
					System.out.println(m.getSender()+"发送给"+m.getReciever()+":"+m.getMessage()+" at "+m.getSendTime());
					if(ManagerConnectUser.getClientThread(m.getReciever()) != null){
						Socket sR =ManagerConnectUser.getClientThread(m.getReciever()).getS();
						ObjectOutputStream oos = new ObjectOutputStream(sR.getOutputStream());
						oos.writeObject(m);
					}
					else{
						System.out.println(m.getReciever()+" is  offline");
						System.out.println("Can not send "+m.getReciever()+" message");
					}
						
					System.out.println(m.getSender()+"发送给"+m.getReciever()+":"+m.getMessage());
				}	
				else if(m.getMesType().equals(MessType.requestFriendList)){
					System.out.println("Get the login information");
					
				}
				else if(m.getMesType().equals(MessType.requestOnLineList)){
					/*System.out.println("to get the on line information");
					Message returnM = new Message();
					returnM.setMesType(MessType.returnOnlineList);
					returnM.setAllUserInfos(ManagerConnectUser.getOnlineUser());
					//TODO this is send to all log in user should add if is friend or not;
					Vector<LoginInfo> infos = returnM.getAllUserInfos();
					for(int i = 0; i < infos.size();i++){
						returnM.setSender(infos.get(i).getUserName());
						Socket sR =ManagerConnectUser.getClientThread(infos.get(i).getUserName()).getS();
						ObjectOutputStream oos = new ObjectOutputStream(sR.getOutputStream());
						oos.writeObject(returnM);
					}*/
					returnOnlineInfo();
				}
				else if (m.getMesType().equals(MessType.logOut)){
					closeSocket();
					ManagerConnectUser.removeConnectThread(m.getSender());
					returnOnlineInfo();
					break;
				}
			
			}catch (Exception e) {
				e.printStackTrace();
				//TODO to end the thread when Logout
			}
		}
	}
	public void closeSocket(){
		try{
			s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void returnOnlineInfo(){
		try{
			Message returnM = new Message();
			returnM.setMesType(MessType.returnOnlineList);
			returnM.setAllUserInfos(ManagerConnectUser.getOnlineUser());
			//TODO this is send to all log in user should add if is friend or not;
			Vector<LoginInfo> infos = returnM.getAllUserInfos();
			for(int i = 0; i < infos.size();i++){
				returnM.setSender(infos.get(i).getUserName());
				Socket sR =ManagerConnectUser.getClientThread(infos.get(i).getUserName()).getS();
				ObjectOutputStream oos = new ObjectOutputStream(sR.getOutputStream());
				oos.writeObject(returnM);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
