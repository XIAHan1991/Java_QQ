package com.qq.client.manager;

import com.qq.client.view.*;
import java.util.*;

public class ManageQqFriendList {

	private static HashMap<String, QqFriendsList> myFriendLists = new HashMap<String, QqFriendsList>();
	public static void addFriendList(String userID, QqFriendsList myLists){
		myFriendLists.put(userID, myLists);
	}
	public static QqFriendsList getFriendList(String userID){
		return myFriendLists.get(userID);
	}
}
