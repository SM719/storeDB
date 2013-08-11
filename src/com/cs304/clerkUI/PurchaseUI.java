package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
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
import java.util.Locale;

import com.cs304.tables.Item;
import com.cs304.tables.Purchase;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PurchaseUI implements ActionListener {
	Connection connect;
	private JFrame tframe;
	JFrame testframe, testframe2;
	JButton Breturn,cancel;
	JTextField textfields,textfields2,textfields3;
	JLabel rid,cardnum,expireDate;
	int rowCount;

public PurchaseUI (Connection c, JFrame frame)
{
	tframe = frame;
	connect = c;
	rowCount = -1;
	testframe = new JFrame ("Purchase");
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
	
	rid = new JLabel ("UPC: ");
	cardnum = new JLabel ("card#: ");
	expireDate = new JLabel("Card Expire Date: ");
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
	
	grid.setConstraints(cardnum, gcon);
	p.add(cardnum);
	
	gcon.gridx = 1;
	gcon.gridy = 2;
	textfields2.setColumns(10);
	grid.setConstraints(textfields2, gcon);
	p.add(textfields2);
	
	gcon.gridx = 0;
	gcon.gridy = 3;
	
	grid.setConstraints(expireDate, gcon);
	p.add(expireDate);
	
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
	if (act.getSource() == cancel)
	{
		tframe.setVisible(true);
		testframe.dispose();
	}
	
	if (act.getSource() == Breturn)
	{
	
	}

	if (!isInt(textfields.getText())){
		
		JOptionPane.showMessageDialog(null, "Cannot be null or is not an int");
	}else if (isInt(textfields.getText())){
		int upc = Integer.parseInt(textfields.getText());
		GregorianCalendar calendar = new GregorianCalendar();
		
		java.sql.Date sday = new java.sql.Date(calendar.getTime().getTime());
		
		try {
			connect.setAutoCommit(false);
			Statement state = connect.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM Item WHERE UPC =" + textfields.getText());
			//Have to check if item exists somehow
			Calendar mydate = new GregorianCalendar();
			while (rs.next())
			{
				Item i = new Item();
				Purchase p = new Purchase();
				i.deleteItem(connect, rs.getInt("upc"));
				int newItemCount = (rs.getInt("stock") - 1);
			//	i.insertItem(connect, rs.getInt("upc"), rs.getString("title"), rs.getString("type"), rs.getString("catagory"), rs.getString("company"), rs.getString("year"), rs.getString("price"), newItemCount);
				
				Date testdate = new Date(1, 1, 1);
				try {
					testdate = (Date) new SimpleDateFormat ("MMMM d, yyyy",Locale.ENGLISH).parse(textfields3.getText());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				p.insertPurchase(connect, (Integer)null, cardnum.getText(), testdate, sday, sday);
				
			}
			
			/*if (rs.getInt(upc) )
			{
				
			}*/
		
			connect.commit();
			state.close();
		}catch (SQLException error)
		{
			System.out.println(error.getMessage());
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
