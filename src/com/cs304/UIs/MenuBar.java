package com.cs304.UIs;
import javax.swing.*;

import com.cs304.clerkUI.ClerkGUI;
//import Customer.CustomerGUI;
//import Manager.ManagerGUI;

import java.awt.*;
import java.awt.event.*;


public class MenuBar extends JFrame implements ActionListener {

	private JMenuItem item1 = new JMenuItem("Customer");
	private JMenuItem item2 = new JMenuItem("Clerk");
	private JMenuItem item3 = new JMenuItem("Manager");
	
	public MenuBar() {
		
		JFrame F = new JFrame("Store");
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Users");
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		
		menuBar.add(menu1);
		F.setJMenuBar(menuBar);
		F.setSize(500,500);
		F.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == item1){
			
			
			//CustomerGUI customerGUI = new CustomerGUI();
			
			// closes on hitting X
			//customerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//customerGUI.setSize(400, 400);
			//customerGUI.setVisible(true);
			
		}
		if(event.getSource() == item2){
			
	
			ClerkGUI clerkGUI = new ClerkGUI();
			
			// closes on hitting X
			//clerkGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//clerkGUI.setSize(500, 400);
			//clerkGUI.setVisible(true);
		}
		if(event.getSource() == item3){
			//ManagerGUI managerGUI = new ManagerGUI();
			
			//managerGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//managerGUI.setSize(600, 600);
			//managerGUI.setVisible(true);
		}
		
		
	}

}