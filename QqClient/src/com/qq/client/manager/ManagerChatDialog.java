package com.qq.client.manager;
import java.util.*;
import com.qq.client.view.*;

public class ManagerChatDialog {
	public static HashMap<String, ChatDialog> ManageChats = new HashMap<String, ChatDialog>();
	
	public static void addChatDialog(String dialogID, ChatDialog myDialog){
		ManageChats.put(dialogID, myDialog);
	}
	public static ChatDialog getChatDialog(String dialogID){
		return ManageChats.get(dialogID);
	}
	public static boolean isOpenDialog(String dialogID){
		boolean b = false;
		if (ManageChats.containsKey(dialogID))
			b = true;
		return b;
	}
	public static void removeChatDialog(String dialogID){
		if(ManageChats.containsKey(dialogID))
			ManageChats.remove(dialogID);
		//TODO else do some thing or not
	}

}
