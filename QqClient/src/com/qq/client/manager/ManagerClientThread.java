package com.qq.client.manager;
import java.util.*;
import com.qq.client.model.*;

public class ManagerClientThread {
	public static HashMap<String,ClientConServerThread> myClientThreads = 
			new HashMap<String,ClientConServerThread>(); 

	public static void addClientThread(String userID,ClientConServerThread myThread){
		myClientThreads.put(userID, myThread);
	}
	public static ClientConServerThread getClientThread(String userID){
		return myClientThreads.get(userID);
	}
	public static void removeClientThread(String userID){
		ClientConServerThread myClientThread = getClientThread(userID);
		if (getClientThread(userID) != null){
			
			try{
				myClientThread.getS().close();
				myClientThread.interrupt();
			}catch(Exception e){
				e.printStackTrace();
			}
			myClientThreads.remove(userID);
		}
		else {
			//TODO to do some thing if come up bug
			System.out.println("Meet Some thing wrong");
		}
	}
}
