package com.cs304.customerUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemList {

	private JList list;
	private JPanel panel;
	String[] l;
	private JFrame fr;

	Connection connect;
	ResultSet r;
	String category;
	int stock;

	public ItemList(Connection con, JFrame f, String c, int q) {

		connect = con;
		fr = f;
		category = c;
		stock = q;

		l = new String[200];

		try {
			// connect.setAutoCommit(false);
			// Statement state = connect.createStatement();
			PreparedStatement p;
			p = connect.prepareStatement("SELECT catagory =" + category
					+ ", FROM Item");
			// p = connect.prepareStatement("SELECT UPC, stock FROM Item");
			// p.setString(1, category);
			// p.setInt(2, stock);

			ResultSet r = p.executeQuery();

			int i = 0;
			while (r.next()) {
				l[i] = r.getString("title");
				i++;
			}

			connect.commit();
			r.close();
			// state.close();

		} catch (SQLException error) {
			System.out.println(error.getMessage());
		}

		JFrame frame = new JFrame();
		frame.setTitle("Item Selection List");

		list = new JList(l);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(new JScrollPane(list));
		frame.setSize(600, 450);
		frame.setVisible(true);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

					if (list.getSelectedIndex() == 0) {
						// No selection, disable fire button.
						System.out.println("TEST\n");
					}
				}
			}

		});

	}

}
