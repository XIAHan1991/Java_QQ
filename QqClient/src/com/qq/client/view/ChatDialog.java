package com.qq.client.view;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.qq.client.manager.*;
import com.qq.client.model.*;
import com.qq.common.*;


public class ChatDialog extends JFrame implements ActionListener,WindowListener{

	/**
	 * the interface to show the chat Dialog
	 */
	private static final long serialVersionUID = 1L;
	JButton jb1;
	JTextArea jta;
	JTextField jtf;
	JScrollPane jsp;
	JPanel jp1;
	String ownerID,friendID;
	//QqConServer myCon;
	public ChatDialog(String ownerID,String friendID){
		//this.myCon = myCon;
		this.ownerID = ownerID;
		this.friendID = friendID;
		jb1 =new JButton("Send");
		jb1.addActionListener(this);
		jb1.setActionCommand("Send");
		jta = new JTextArea();
		jta.setEditable(false);
		jsp = new JScrollPane(jta);
		jtf = new JTextField(15);
		jp1 = new JPanel();
		jp1.add(jtf); jp1.add(jb1);
		
		this.add(jsp);
		this.add(jp1,"South");
		
		this.addWindowListener(this);
		
		this.setVisible(true);
		this.setIconImage((new ImageIcon("Images/head.GIF")).getImage());
		this.setTitle(this.ownerID+" is chatting with "+this.friendID);
		this.setSize(300,200);
	}
	public void ShowMessage(Message m){
		//TODO Can show other types of message in the future
		if(m.getMesType().equals(MessType.comMessage)){
			if(m.getSender().equals(this.ownerID))
				jta.append("you send to "+this.friendID+":\n    "+m.getMessage()+"\n");
			else
				jta.append(m.getSender()+" send to you:\n    "+m.getMessage()+"\n");
		}
	}
	public void showOnTop(){
		//make the window showing on the top
		this.setExtendedState(JFrame.NORMAL);
		this.setAlwaysOnTop(true);
		this.setAlwaysOnTop(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Send")){
			Message m = new Message();
			m.setMesType(MessType.comMessage);
			m.setSender(ownerID);
			m.setReciever(friendID);
			m.setMessage(jtf.getText().trim());
			m.setSendTime((new Date()).toString());
			ShowMessage(m);
			new SendMessageToServer(ownerID, m);
		}
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		if(e.getSource().equals(this)){
			String dialogID = ownerID+"#"+friendID;
			ManagerChatDialog.removeChatDialog(dialogID);
			System.out.println("Closing the "+ownerID+"#"+friendID+" chat dialog");
		}
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
