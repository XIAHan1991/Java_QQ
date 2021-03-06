package com.qq.client.view;
/*
 * The Frame for show the friend list
 */
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.qq.client.manager.*;
import com.qq.client.model.*;
import com.qq.common.*;

public class QqFriendsList extends JFrame implements ActionListener,MouseListener,WindowListener{

	//Card One
	JButton jb1,jb2,jb3;
	JScrollPane jsp1;
	JPanel jp1,jp1C,jp1S;
	JLabel jlbUser;
	JLabel[] jlb2s,jlb3s;
	HashMap<String, JLabel> friendsJlb = new HashMap<String,JLabel>();
	CardLayout myCards;
	//Card Two
	JButton jb21,jb22,jb23;
	JScrollPane jsp2;
	JPanel jp2,jp2C,jp2N;
	//Card Three
	JButton jb31,jb32,jb33;
	JScrollPane jsp3;
	JPanel jp3,jp3C,jp3N;
	//QqConServer myCon;
	String ownerID;
	// TODO add an input parameter (Message m)
	public QqFriendsList(String ownerID){
		//this.myCon = myCon;
		this.ownerID = ownerID;
		//Card 1 
		
		InitCard1();
		InitCard2();
		InitCard3();
		myCards = new CardLayout();
		this.setLayout(myCards);
		this.add(jp1,"1");
		this.add(jp2,"2");
		this.add(jp3,"3");
		this.setSize(140,400);
		this.setVisible(true);
		this.setTitle(ownerID);
		this.addWindowListener(this);
		//this.setDefaultCloseOperation();
		
	}
	public void InitCard1(){
		jb1 = new JButton("My Friends");
		jb1.addActionListener(this);
		jb1.setActionCommand("Friends");
		jb2 = new JButton("Strangers");
		jb2.addActionListener(this);
		jb2.setActionCommand("Strangers");
		jb3 = new JButton("BlackList");
		jb3.addActionListener(this);
		jb3.setActionCommand("Black");
		jp1 = new JPanel(new BorderLayout());
		jp1C = new JPanel();
		jp1S = new JPanel(new GridLayout(2,1));
		jlbUser = new JLabel(ownerID,new ImageIcon("Images/head.GIF"),JLabel.LEFT);
		jp1C.add(jlbUser);
		showAllFriendList();
		//showFriendList();
		jp1S.add(jb2); jp1S.add(jb3);
		jsp1 = new JScrollPane(jp1C);
		
		jp1.add(jb1,"North");
		jp1.add(jsp1,"Center");
		jp1.add(jp1S,"South");
	}
	public void InitCard2(){
		jb21 = new JButton("My Friends");
		jb21.addActionListener(this);
		jb21.setActionCommand("Friends");
		jb22 = new JButton("陌生人");
		jb22.addActionListener(this);
		jb22.setActionCommand("Strangers");
		jb23 = new JButton("黑名单");
		jb23.addActionListener(this);
		jb23.setActionCommand("Black");
		jp2C = new JPanel(new GridLayout(20,1,4,4));
		jlb2s = new JLabel[20];
		for(int i = 0; i < 20 ; i++){
			jlb2s[i] = new JLabel(i+1+"M",new ImageIcon("Images/head.GIF"),JLabel.LEFT);
			jlb2s[i].addMouseListener(this);
			jp2C.add(jlb2s[i]);
		}
		jp2N = new JPanel(new GridLayout(2,1,0,0));
		jp2N.add(jb21); jp2N.add(jb22);
		jsp2 = new JScrollPane(jp2C);
		
		jp2 = new JPanel(new BorderLayout());
		jp2.add(jp2N,"North");
		jp2.add(jsp2,"Center");
		jp2.add(jb23,"South");
	}
	public void InitCard3(){
		jb31 = new JButton("My Friends");
		jb31.addActionListener(this);
		jb31.setActionCommand("Friends");
		jb32 = new JButton("陌生人");
		jb32.addActionListener(this);
		jb32.setActionCommand("Strangers");
		jb33 = new JButton("黑名单");
		jb33.addActionListener(this);
		jb33.setActionCommand("Black");
		
		jp3C = new JPanel(new GridLayout(20,1,4,4));
		jlb3s = new JLabel[10];
		for(int i = 0; i < 10 ; i++){
			jlb3s[i] = new JLabel(i+1+"B",new ImageIcon("Images/head.GIF"),JLabel.LEFT);
			jlb3s[i].addMouseListener(this);
			jp3C.add(jlb3s[i]);
		}
		jp3N = new JPanel(new GridLayout(3,1,0,0));
		jp3N.add(jb31); jp3N.add(jb32); jp3N.add(jb33);
		jsp3 = new JScrollPane(jp3C);
		
		jp3 = new JPanel(new BorderLayout());
		jp3.add(jp3N,"North");
		jp3.add(jsp3,"Center");
	}
	
	public void showAllFriendList(){
		int total =50;
		jp1C.setLayout(new GridLayout(total,1,4,4));
		for(int i = 0; i < total ; i++){
			JLabel myLabel = new JLabel(i+1+"",new ImageIcon("Images/head.GIF"),JLabel.LEFT);
			myLabel.setEnabled(false);
			if(!myLabel.getText().equals(ownerID)){
				myLabel.addMouseListener(this);
				jp1C.add(myLabel);
				friendsJlb.put(i+1+"",myLabel);
			}
		}
	}
	
	//TODO get the friends list from the server
	public void showFriendList(){
		ClientConServerThread myThread = ManagerClientThread.getClientThread(ownerID);
		int totalNum = myThread.getListSize();
		jp1C.setLayout(new GridLayout(totalNum,1,4,4));
		for (int i = 0; i < totalNum; i++){
			LoginInfo info = myThread.getFriendList(i);
			//JLabel myLabel = new JLabel(info.getUserName(),new ImageIcon(info.getJpgDir()),JLabel.LEFT);
			JLabel myLabel = new JLabel(info.getUserName(),new ImageIcon("Images/head.GIF"),JLabel.LEFT);
			if(!myLabel.getText().equals(ownerID)){
				myLabel.addMouseListener(this);
				myLabel.setEnabled(false);
				jp1C.add(myLabel);
				friendsJlb.put(info.getUserName(),myLabel);
			}
		}
	}
	public void updateOnlineList(Message m){
		initOnLineList();
		Vector<LoginInfo> userInfos = m.getAllUserInfos();
		int totalNum = userInfos.size();
		System.out.println("update on line info" + totalNum);
		for(int i = 0; i <totalNum; i++){
			LoginInfo info = userInfos.get(i);
			if(friendsJlb.containsKey(info.getUserName()))
				friendsJlb.get(info.getUserName()).setEnabled(true);
		}
		//initOnLineList();
	}
	public void initOnLineList(){
		System.out.println("init the on line list");
		Iterator<String> iter = friendsJlb.keySet().iterator();
		while(iter.hasNext()){
			String userID = iter.next().toString();
			//System.out.println(userID);
			if(!userID.equals(ownerID))
				friendsJlb.get(userID).setEnabled(false);;
		}
	}
	
	//TODO Add friend and delete friend
	public void addFriendList(String userID){
		
	}
	public void removeFriendList(String userID){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Strangers")){
			myCards.show(this.getContentPane(), "2");
			System.out.println("Strangers");
		}
		if (e.getActionCommand().equals("Friends")){
			myCards.show(this.getContentPane(), "1");
		}
		if (e.getActionCommand().equals("Black")){
			myCards.show(this.getContentPane(), "3");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			JLabel myLabel = (JLabel) e.getSource();
			String friendsID = myLabel.getText();
			System.out.println("你希望和"+friendsID+"聊天");
			if(!this.ownerID.equals(friendsID) && myLabel.isEnabled()){
				String dialogID = this.ownerID+"#"+friendsID;
				if (ManagerChatDialog.isOpenDialog(dialogID)){
					ManagerChatDialog.getChatDialog(dialogID).showOnTop();
				}	
				else{
					ChatDialog myChat = new ChatDialog(this.ownerID,friendsID);
					ManagerChatDialog.addChatDialog(dialogID, myChat);
				}
				
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		if(e.getSource().equals(this)){
			System.out.println("the window is closing");
			Message m = new Message();
			m.setMesType(MessType.logOut);
			m.setSender(ownerID);
			new SendMessageToServer(ownerID, m);
			ManagerClientThread.removeClientThread(ownerID);
			System.exit(0);
		}
	}
	@Override
	public void windowClosed(WindowEvent e) {
		if(e.getSource().equals(this)){
			System.out.println("the window has closed");
		}
		
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
