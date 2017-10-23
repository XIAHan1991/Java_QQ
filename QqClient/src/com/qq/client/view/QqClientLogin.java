package com.qq.client.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.qq.common.*;
import com.qq.client.manager.ManageQqFriendList;
import com.qq.client.model.*;

// 	the frame for Login

public class QqClientLogin extends JFrame implements ActionListener{

	JPanel jp1,jp2,jp3,jp4;
	JLabel jl1;
	JLabel jl2,jl3,jl4,jl5;
	JTextField jtf1,jtf1a,jtf1b;
	JPasswordField jpf1,jpf1a,jpf1b;
	JButton jb1,jb2,jb3,jb4;
	
	JCheckBox jcb1,jcb2;
	JTabbedPane jtp;
	JLabel jl2a,jl3a,jl4a,jl5a;
	JCheckBox jcb1a,jcb2a;
	JButton jb1a;
	JLabel jl2b,jl3b,jl4b,jl5b;
	JCheckBox jcb1b,jcb2b;
	JButton jb1b;
	public static void main(String[] args) {

		new QqClientLogin();
	}
	
	public QqClientLogin(){
		//jp1 =new JPanel(new BorderLayout());
		ImageIcon imIcon1 = new ImageIcon("Images/logo554.GIF");
		imIcon1.setImage(imIcon1.getImage().getScaledInstance(425, 75,Image.SCALE_AREA_AVERAGING));
		jl1 = new JLabel(imIcon1);
		jtp = new JTabbedPane();
		
		jp3 = new JPanel();
		jp3.setBackground(Color.green);
		jtp.add("QQ Number", this.editJp1());
		jtp.add("Mobile",this.editJp2());
		jtp.add("Email", this.editJp3());
		jp4 =new JPanel(new FlowLayout(1, 30, 1));
		
		//login button
		ImageIcon imIcon2 = new ImageIcon("Images/Login.GIF");
		jb2 = new JButton("Login");
		jb2.addActionListener(this);
		jb2.setActionCommand("Login");
		//cancel button
		jb3 = new JButton("Cancel");
		jb3.addActionListener(this);
		jb3.setActionCommand("Cancel");
		//help button
		jb4 = new JButton("Help");
		
		jp4.add(jb2); jp4.add(jb3); jp4.add(jb4);
		
		this.add(jl1,BorderLayout.NORTH);
		this.add(jtp);
		this.add(jp4,BorderLayout.SOUTH);
		this.setTitle("QQ LOGIN");
		this.setSize(425,275);
		this.setLocation(450,300);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	// Edit the First Card Layout **--1--**
	public JPanel editJp1(){
		jp1 = new JPanel(new GridLayout(3, 3));
		jl2 = new JLabel("QQ Number");
		jl2.setHorizontalAlignment(JLabel.CENTER);
		jtf1 = new JTextField(15);
		jb1 =new JButton("Clear");
		jl3  = new JLabel("QQ PassWord");
		jl3.setHorizontalAlignment(JLabel.CENTER);
		jpf1 = new JPasswordField(15);
		jl4 = new JLabel("Forget PassWord");
		jl4.setHorizontalAlignment(JLabel.CENTER);
		jcb1 = new JCheckBox("Stealth");
		jcb2 = new JCheckBox("Remeber");
		
		jl5 = new JLabel("Request Proctect");
		jl5.setHorizontalAlignment(JLabel.CENTER);
		jp1.add(jl2); jp1.add(jtf1); jp1.add(jb1); jp1.add(jl3); jp1.add(jpf1);
		jp1.add(jl4); jp1.add(jcb1); jp1.add(jcb2); jp1.add(jl5);
		return jp1;
	}
	// Edit the Second Card Layout **--2--**
	public JPanel editJp2(){
		jp2 = new JPanel(new GridLayout(3, 3));
		jl2a = new JLabel("Mobile");
		jl2a.setHorizontalAlignment(JLabel.CENTER);
		jtf1a = new JTextField(15);
		jb1a =new JButton("Clear");
		jl3a  = new JLabel("PassWord");
		jl3a.setHorizontalAlignment(JLabel.CENTER);
		jpf1a = new JPasswordField(15);
		jl4a = new JLabel("Forget PassWord");
		jl4a.setHorizontalAlignment(JLabel.CENTER);
		jcb1a = new JCheckBox("Stealth");
		jcb2a = new JCheckBox("Remeber");
		
		jl5a = new JLabel("Request Protect");
		jl5a.setHorizontalAlignment(JLabel.CENTER);
		jp2.add(jl2a); jp2.add(jtf1a); jp2.add(jb1a); jp2.add(jl3a); jp2.add(jpf1a);
		jp2.add(jl4a); jp2.add(jcb1a); jp2.add(jcb2a); jp2.add(jl5a);
		return jp2;
	}
	// Edit the Third Card Layout  **--3--**
	public JPanel editJp3(){
		jp3 = new JPanel(new GridLayout(3, 3));
		jl2b = new JLabel("Email");
		jl2b.setHorizontalAlignment(JLabel.CENTER);
		jtf1b = new JTextField(15);
		jb1b =new JButton("Clear");
		jl3b  = new JLabel("PassWord");
		jl3b.setHorizontalAlignment(JLabel.CENTER);
		jpf1b = new JPasswordField(15);
		jl4b = new JLabel("Forget PassWord");
		jl4b.setHorizontalAlignment(JLabel.CENTER);
		jcb1b = new JCheckBox("Stealth");
		jcb2b = new JCheckBox("Remeber");
		
		jl5b = new JLabel("Request Protect");
		jl5b.setHorizontalAlignment(JLabel.CENTER);
		jp3.add(jl2b); jp3.add(jtf1b); jp3.add(jb1b); jp3.add(jl3b); jp3.add(jpf1b);
		jp3.add(jl4b); jp3.add(jcb1b); jp3.add(jcb2b); jp3.add(jl5b);
		return jp3;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Login")){
			//QqClientUser qqClientUser = new QqClientUser();
			//QqConServer myCon = qqClientUser.getMyCon();
			LoginInfo u = new LoginInfo();
			String userID = null,userPw = null;
			//System.out.println(jtp.getSelectedIndex());
			switch (jtp.getSelectedIndex()) {
			case 0:
				userID = jtf1.getText().trim();
				userPw = new String(jpf1.getPassword());
				break;
			case 1:
				userID = jtf1a.getText().trim();
				userPw = new String(jpf1a.getPassword());
				break;
			case 2: 
				userID = jtf1b.getText().trim();
				userPw = new String(jpf1b.getPassword());
				break;
			default:
				break;
			}
			u.setUserName(userID);
			u.setPassWord(userPw);
			String reLogInfo = (new QqClientUser()).CheckUser(u);
			if(reLogInfo == null)
				JOptionPane.showMessageDialog(this, "Unknow Error","Error", JOptionPane.ERROR_MESSAGE);
			else if(reLogInfo.equals(MessType.loginSucess)){
				Message m = new Message();
				//m.setMesType(MessType.requestFriendList);
				//m.setSender(userID);
				//new SendMessageToServer(userID, m);
				QqFriendsList qqFriendsList = new QqFriendsList(userID);
				ManageQqFriendList.addFriendList(userID, qqFriendsList);
				m.setMesType(MessType.requestOnLineList);
				m.setSender(userID);
				//System.out.println("to request on line info");
				new SendMessageToServer(userID, m);
				this.dispose();
			}
			else if(reLogInfo.equals(MessType.wrongPW))
				JOptionPane.showMessageDialog(this, "Fault UserName and password");
			else if(reLogInfo.equals(MessType.isOnline))
				JOptionPane.showMessageDialog(this, "the UserName has login");
			else if(reLogInfo.equals(MessType.notFindServer))
				JOptionPane.showMessageDialog(this, "Can not find Server","Warning", JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Unknow Error","Error", JOptionPane.ERROR_MESSAGE);
			
		}
		if(e.getActionCommand().equals("Cancel")){
			System.out.println("Click Cancle");
			this.dispose();
		}
	}
	
}
