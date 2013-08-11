package com.cs304.customerUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemList implements ActionListener{

	private JList list;
	private JPanel panel;
	int i = 1;
	String[] l, n;
	private JFrame fr;
	JButton enter, cancel;

	Connection connect;
	ResultSet r;
	String category, title, leadSinger;
	int stock;
	JFrame Frame;
	JLabel m1, m2;
	JTextField textfield;

	public ItemList(Connection con, JFrame f) {
		
		fr = f;
		connect = con;
		Frame = new JFrame ("Search");
		
		JPanel panel = new JPanel();
		
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();
		
		enter = new JButton("Enter");
		cancel = new JButton ("Cancel");
		
		m1 = new JLabel ("Enter Search below");
		m2 = new JLabel ("Search: ");
		textfield = new JTextField();
		
		
		g.gridx = 1;
		g.gridy = 0;
		gb.setConstraints(m1, g);
		panel.add(m1);
		
		g.gridx = 0;
		g.gridy = 1;
		gb.setConstraints(m2, g);
		panel.add(m2);
		
		g.gridx = 1;
		g.gridy = 1;
		textfield.setColumns(10);
		gb.setConstraints(textfield, g);
		panel.add(textfield);
		
		g.gridx = 1;
		g.gridy = 3;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		gb.setConstraints(enter, g);
		panel.add(enter);
		
		g.gridx = 0;
		g.gridy = 3;
		gb.setConstraints(cancel, g);
		panel.add(cancel);
		
		cancel.addActionListener(this);
		enter.addActionListener(this);
		
		panel.setLayout(gb);
		
		Frame.add(panel);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		int t1 = 0;
		String[] Sarray = new String [200];
		Sarray [0] = "The Search Results are:           ";
		
		if (event.getSource() == cancel)
		{
			fr.setVisible(true);
			Frame.dispose();
		}
		if (event.getSource() == enter)
		{
			if(textfield.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Invalid Search Term");
				
			}else
			{
				try {
					connect.setAutoCommit(false);
					Statement state = connect.createStatement();
					ResultSet rs = state.executeQuery("SELECT DISTINCT Item.upc, Item.title, Item.Stock FROM Item LEFT JOIN LeadSinger ON Item.upc = LeadSinger.UPC LEFT JOIN HasSong ON Item.upc = HasSong.upc Where Item.catagory Like" + "'%" + textfield.getText() + "%'" + "OR LEADSINGER.NAME Like"  + "'%" + textfield.getText() + "%'" + "OR HASSONG.TITLE LIKE"  + "'%" + textfield.getText() + "%'" );
					
					while (rs.next() == true)
					{
							
								
							
							Sarray[i] = "Item:" + rs.getInt("upc")
										+"Item name:" + rs.getString("title")
										+"Stock:" + rs.getInt("stock") + "\n";
							
						
						
							
							i++;
					}
					connect.commit();
					state.close();
				}catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, new JList(Sarray));
			}
		}
	}
}
