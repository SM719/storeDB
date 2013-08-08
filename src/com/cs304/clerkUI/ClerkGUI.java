package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class ClerkGUI  {

	//UNCOMMENT LATER
	Connection connection;
	
	private JFrame Frame,mainframe;
	
	
	private JButton processPurchase;
	private JButton processReturn;
	
	// constructor 
	public ClerkGUI(Connection connect, JFrame fr) {
		mainframe = fr;
		connection = connect;
		Frame = new JFrame("Clerk Operations");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();
		
		JPanel panel = new JPanel();
		
		processPurchase = new JButton("Process a purchase of items");
		processReturn = new JButton("Process a return of items");
		
		/*
		g.gridx = 0;
		g.gridy = 0;
		g.insets = new Insets(10, 29, 10, 10);
		gb.setConstraints(text, g);
		panel.add(text);
		*/

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(processPurchase, g);
		panel.add(processPurchase);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(processReturn, g);
		panel.add(processReturn);
		
		panel.setLayout(gb);
		
		theHandler Handler = new theHandler();
		processPurchase.addActionListener(Handler);
		processReturn.addActionListener(Handler);
		
		Frame.add(panel);
	}
	
	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == processPurchase)
			{
				Frame.setVisible(false);
				
			} else if (event.getSource() == processReturn)
			{
				Frame.setVisible(false);
				returnUI returnp = new returnUI (connection,mainframe);
			}
			
		}
		
		
	}
	
}
