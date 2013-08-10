package com.cs304.customerUI;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	String[] l, n;
	private JFrame fr;

	Connection connect;
	ResultSet r;
	String category, title, leadSinger;
	int stock;

	public ItemList(Connection con, JFrame f, String cat, String t, String ls,
			int q) {

		connect = con;
		fr = f;
		category = cat;
		stock = q;
		title = t;
		leadSinger = ls;

		l = new String[200];
		n = new String[200];

		if ((!category.equals("")) && (title.equals(""))
				&& (leadSinger.equals(""))) {
			try {
				// connect.setAutoCommit(false);
				// Statement state = connect.createStatement();
				PreparedStatement p;
				p = connect.prepareStatement("SELECT * FROM Item");

				// p.setString(1, category);
				// p.setInt(2, stock);

				ResultSet r = p.executeQuery();

				int i = 0;
				while (r.next()) {
					l[i] = r.getString("title") + "/" + r.getString("catagory")
							+ "/" + r.getString("stock") + "/"
							+ r.getString("price");
					n[i] = l[i];
					i++;
				}

				connect.commit();
				r.close();
				// state.close();

			} catch (SQLException error) {
				System.out.println(error.getMessage());
			}

		}

		/*
		 * } else if((cat.equals("")) && (!title.equals("")) &&
		 * (leadSinger.equals(""))){
		 * 
		 * 
		 * 
		 * } else if((cat.equals("")) && (title.equals("")) &&
		 * (!leadSinger.equals(""))){
		 * 
		 * 
		 * } else if((!cat.equals("")) && (!title.equals("")) &&
		 * (leadSinger.equals(""))){
		 * 
		 * } else if((!cat.equals("")) && (title.equals("")) &&
		 * (!leadSinger.equals(""))){
		 * 
		 * } else if((cat.equals("")) && (!title.equals("")) &&
		 * (!leadSinger.equals(""))){
		 * 
		 * } else{
		 * 
		 * }
		 */

		final JFrame frame = new JFrame();
		frame.setTitle("Item Selection List");

		list = new JList(l);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frame.add(new JScrollPane(list));
		frame.setSize(600, 450);
		frame.setVisible(true);

		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {

				Vector<Component> basket = new Vector<Component>();

				if (e.getValueIsAdjusting() == false) {

					if (list.getSelectedIndex() == 0) {
						basket.addElement(list.getComponent(0));
						System.out.println("0\n");
					} else if (list.getSelectedIndex() == 1) {
						basket.addElement(list.getComponent(1));
						System.out.println("1\n");
					} else if (list.getSelectedIndex() == 2) {
						basket.addElement(list.getComponent(2));
						System.out.println("2\n");
					} else if (list.getSelectedIndex() == 3) {
						basket.addElement(list.getComponent(3));
						System.out.println("3\n");
					} else if (list.getSelectedIndex() == 4) {
						basket.addElement(list.getComponent(4));
						System.out.println("4\n");
					} else if (list.getSelectedIndex() == 5) {
						basket.addElement(list.getComponent(5));
						System.out.println("5\n");
					} else if (list.getSelectedIndex() == 6) {
						basket.addElement(list.getComponent(6));
						System.out.println("6\n");
					} else if (list.getSelectedIndex() == 7) {
						basket.addElement(list.getComponent(7));
						System.out.println("7\n");
					} else if (list.getSelectedIndex() == 8) {
						basket.addElement(list.getComponent(8));
						System.out.println("8\n");
					} else if (list.getSelectedIndex() == 9) {
						basket.addElement(list.getComponent(9));
						System.out.println("9\n");
					} else {
						frame.dispose();
					}
				}
			}

		});

	}
}
