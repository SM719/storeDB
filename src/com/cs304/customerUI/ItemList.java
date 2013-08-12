package com.cs304.customerUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemList implements ActionListener {

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
	JLabel quant, cat, t, ls;
	JTextField q, c, ti, lead;

	String CID;

	public ItemList(Connection con, JFrame f, String id) {

		fr = f;
		CID = id;

		connect = con;
		Frame = new JFrame("Search");

		JPanel panel = new JPanel();

		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		enter = new JButton("Enter");
		cancel = new JButton("Cancel");

		quant = new JLabel("Enter the quantity ");
		cat = new JLabel("Enter the catagory: ");
		t = new JLabel("Enter the title");
		ls = new JLabel("Enter the leadSinger: ");
		q = new JTextField();
		c = new JTextField();
		ti = new JTextField();
		lead = new JTextField();

		g.gridx = 1;
		g.gridy = 0;
		gb.setConstraints(quant, g);
		panel.add(quant);

		g.gridx = 0;
		g.gridy = 1;
		gb.setConstraints(quant, g);
		panel.add(quant);

		g.gridx = 1;
		g.gridy = 1;
		q.setColumns(10);
		gb.setConstraints(q, g);
		panel.add(q);

		g.gridx = 0;
		g.gridy = 2;
		gb.setConstraints(cat, g);
		panel.add(cat);

		g.gridx = 1;
		g.gridy = 2;
		c.setColumns(10);
		gb.setConstraints(c, g);
		panel.add(c);

		g.gridx = 0;
		g.gridy = 3;
		gb.setConstraints(t, g);
		panel.add(t);

		g.gridx = 1;
		g.gridy = 3;
		ti.setColumns(10);
		gb.setConstraints(ti, g);
		panel.add(ti);

		g.gridx = 0;
		g.gridy = 4;
		gb.setConstraints(ls, g);
		panel.add(ls);

		g.gridx = 1;
		g.gridy = 4;
		lead.setColumns(10);
		gb.setConstraints(lead, g);
		panel.add(lead);

		g.gridx = 1;
		g.gridy = 5;
		g.gridwidth = 1;
		g.fill = GridBagConstraints.HORIZONTAL;
		gb.setConstraints(enter, g);
		panel.add(enter);

		g.gridx = 1;
		g.gridy = 6;
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		cancel.addActionListener(this);
		enter.addActionListener(this);

		q.addActionListener(this);
		c.addActionListener(this);
		ti.addActionListener(this);
		lead.addActionListener(this);

		panel.setLayout(gb);

		Frame.add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		int t1 = 0;
		String[] Sarray = new String[200];
		String[] prices = new String[200];
		Sarray[0] = "The Search Results are:           ";

		if (event.getSource() == cancel) {
			fr.setVisible(true);
			Frame.dispose();
		}
		if (event.getSource() == enter) {
			if (c.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid Search Term");

			} else {
				try {
					connect.setAutoCommit(false);
					Statement state = connect.createStatement();

					ResultSet rs = state
							.executeQuery("SELECT DISTINCT Item.upc, Item.price, Item.title, Item.Stock FROM Item LEFT JOIN LeadSinger ON Item.upc = LeadSinger.UPC LEFT JOIN HasSong ON Item.upc = HasSong.upc Where Item.catagory Like"
									+ "'%"
									+ c.getText()
									+ "%'"
									+ "AND LEADSINGER.NAME Like"
									+ "'%"
									+ lead.getText()
									+ "%'"
									+ "AND HASSONG.TITLE LIKE"
									+ "'%"
									+ ti.getText() + "%'");

					while (rs.next() == true) {

						Sarray[i] = "Item:" + rs.getString("UPC")
								+ rs.getString("title") + "Stock:"
								+ rs.getInt("stock") + "Price:"
								+ rs.getString("price") + "\n";
						prices[i] = rs.getString("price");

						i++;

					}
					connect.commit();
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				new ListUI(connect, Sarray, prices, fr, CID);
				Frame.dispose();
				// JOptionPane.showMessageDialog(null, new JList(Sarray));
			}
		}
	}
}
