package com.qq.client.model;
import java.io.*;
import java.net.*;
import com.qq.common.*;
import com.qq.client.manager.*;

public class SendMessageToServer {

	public SendMessageToServer (String userID, Message m){
		
		try{
			ObjectOutputStream oos  = new ObjectOutputStream(ManagerClientThread.
					getClientThread(userID).getS().getOutputStream());
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
