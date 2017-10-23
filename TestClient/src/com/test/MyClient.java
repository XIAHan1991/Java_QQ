package com.test;
import java.net.*;
import java.io.*;
import java.util.*;
import com.common.*;

public class MyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MyClient();
	}
	
	public MyClient(){
		try {
			Socket s = new Socket("127.0.0.1", 3456);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			User u = new User();
			u.setUserName("123456");
			u.setPassWord("1234***");
			oos.writeObject(u);
			 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			
		}
	}
}
