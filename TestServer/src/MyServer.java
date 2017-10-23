
import java.net.*;
import java.io.*;
import java.util.*;
import com.common.*;

public class MyServer {
	ServerSocket ss;
	Socket s;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyServer();
	}
	
	public MyServer(){
		try {
			ss = new ServerSocket(3456);
			s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			User myUser = (User) ois.readObject();
			System.out.println("从客户端接受到： 用户名："+ myUser.getUserName() +"\n密码为："+myUser.getPassWord());
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				s.close();
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
