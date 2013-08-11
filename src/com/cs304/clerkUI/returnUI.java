package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.cs304.tables.Return;
import com.cs304.tables.ReturnItem;

public class returnUI implements ActionListener {
	Connection connect;
	private JFrame tframe;
	JFrame testframe, testframe2;
	JButton Breturn,cancel;
	JTextField textfields,textfields2,textfields3;
	JLabel rid,upc,qty;
	int rowCount;
	float ItemCost;
	int ItemUpc;
	
	public returnUI (Connection c, JFrame frame)
	{
		tframe = frame;
		connect = c;
		rowCount = -1;
		testframe = new JFrame ("Returns");
		JPanel p = new JPanel(); 
		
		testframe.setVisible(true);
		testframe.setSize(400, 400);
		
		Dimension dim = testframe.getToolkit().getScreenSize();
		Rectangle r = testframe.getBounds();
		testframe.setLocation((dim.width - r.width)/2, (dim.height - r.height) / 2 );
		 
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gcon = new GridBagConstraints();
		
		Breturn = new JButton ("Confirm");
		cancel = new JButton ("Cancel");
		
		rid = new JLabel ("ReceiptID: ");
		upc = new JLabel ("UPC: ");
		qty = new JLabel ("Quantity: ");
		textfields = new JTextField();
		textfields2 = new JTextField();
		textfields3 = new JTextField();
		
		gcon.gridx = 0;
		gcon.gridy = 1;
		gcon.insets = new Insets (0,0,10,10);
		grid.setConstraints(rid, gcon);
		p.add(rid);
		
		gcon.gridx = 1;
		gcon.gridy = 1;
		textfields.setColumns(10);
		grid.setConstraints(textfields, gcon);
		p.add(textfields);
		
		gcon.gridx = 0;
		gcon.gridy = 2;
		gcon.insets = new Insets (0,0,10,10);
		grid.setConstraints(upc, gcon);
		p.add(upc);
		
		gcon.gridx = 1;
		gcon.gridy = 2;
		textfields2.setColumns(10);
		grid.setConstraints(textfields2, gcon);
		p.add(textfields2);
		
		gcon.gridx = 0;
		gcon.gridy = 3;
		gcon.insets = new Insets (0,0,10,10);
		grid.setConstraints(qty, gcon);
		p.add(qty);
		
		gcon.gridx = 1;
		gcon.gridy = 3;
		textfields3.setColumns(10);
		grid.setConstraints(textfields3, gcon);
		p.add(textfields3);
		
		gcon.gridx = 0;
		gcon.gridy = 4;
		grid.setConstraints(Breturn, gcon);
		p.add(Breturn);
		
		gcon.gridx = 1;
		gcon.gridy = 4;
		grid.setConstraints(cancel, gcon);
		p.add(cancel);
		
		Breturn.addActionListener(this);
		cancel.addActionListener(this);
		p.setLayout(grid);
		
		testframe.add(p);
		
	}
	
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent act) {
		// TODO Auto-generated method stub
		String[] s1 = new String [50];
		String[] s2 = new String [50];
		int ReceiptID = Integer.parseInt(textfields.getText());
		s1[0] = "The payment form was in credit.";
		
		s2[0] = "The payment form was in cash. ";
		
		if (act.getSource() == cancel)
		{
			tframe.setVisible(true);
			testframe.dispose();
		}
		
		if (act.getSource() == Breturn)
		{
			//System.out.println("testpoint1 \n");
			if (!isInt(textfields.getText())|!isInt(textfields2.getText())|!isInt(textfields3.getText())){
				
				JOptionPane.showMessageDialog(null, "Cannot be null or is not an int");
			} else if (isInt(textfields.getText()) == true){
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(Calendar.MONTH, 2);
				java.sql.Date sDate = new java.sql.Date(calendar.getTime().getTime());
				
			//	System.out.println("testpoint5 \n");
							
			
			
				try{
				
				connect.setAutoCommit(false);
				int count = 0;
				Statement state = connect.createStatement();
				
				System.out.println ("Passed point 0 \n");
				//ResultSet r = state.executeQuery("SELECT * FROM PURCHASE WHERE receiptID =" + textfields.getText() + "AND(sysdate + 15 )<= ?" + sDate); 
				ResultSet rs = state.executeQuery("SELECT COUNT(*) AS TOTAL FROM PURCHASE WHERE receiptID =" + "'" + textfields.getText()+ "'");
				
				//System.out.println ("passed point 1 \n");
				while(rs.next())
				{
					count = count +rs.getInt("TOTAL");
					System.out.println ("passed point 3 \n");
				}
				
				
				if (count == 0)
				{
					
					JOptionPane.showMessageDialog(null, "receiptID does not exist");
					System.out.println ("passed point 2 \n");
				}else 
				{
					
					ResultSet rs3 = state.executeQuery ("SELECT * FROM ITEM WHERE UPC =" + "'" + textfields2.getText()+ "'");
					while (rs3.next())
					{
						System.out.println ("passed point 4 \n");
						ItemCost = rs3.getFloat("price");
						ItemUpc = rs3.getInt("upc");
						s1[1] = "\n Cost:" + ItemCost + "\n";
					s2[1] = "\n Cost:" + ItemCost + "\n";
						
					}
					System.out.println ("passed point 123123 \n");
					ResultSet rs2 = state.executeQuery("SELECT * FROM PURCHASE WHERE RECEIPTID =" + "'" + ReceiptID + "'" + "AND (SYSDATE+15) >= BDATE");
					System.out.println ("passedpoint312423 \n");
					while (rs2.next())
					{
						System.out.println ("passed point 5 \n");
						Return r = new Return();
						
						r.insertReturn(connect, rs2.getInt("RECEIPTID"));
						
						
						if (rs2.getString("cardnum") == null)
						{
							JOptionPane.showMessageDialog(null, new JList(s2));
						}else
						{
							JOptionPane.showMessageDialog(null, new JList (s1));
						}
					}
					
					ResultSet rs4 = state.executeQuery ("SELECT * FROM RETURN WHERE RECEIPTID =" + "'" + textfields.getText() + "'");
					while (rs4.next())
					{
						System.out.println ("passed point 6 \n");
						ReturnItem rt = new ReturnItem();
						rt.insertReturnItem(connect, rs4.getInt("retID"), ItemUpc, Integer.parseInt(textfields3.getText()));
					}
					
					
				}
				connect.commit();
				state.close();
				}catch (SQLException error)
				{
					System.out.println(error.getMessage());
				}
				
			}
			
		}
		
	}
	
public boolean isInt(String s){
		
		try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
		
		return true;
	}
	
}	
