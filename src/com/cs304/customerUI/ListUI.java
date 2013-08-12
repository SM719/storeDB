package com.cs304.customerUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class ListUI {

	private JList list, customerList;
	private JFrame frame, fr;
	Connection con;
	float price = 0;

	JButton sel, checkout;

	public ListUI(Connection c, String[] s, String[] pr, JFrame f, String i) {

		final String id = i;
		fr = f;
		con = c;
		frame = new JFrame();

		frame.setLayout(new FlowLayout());

		list = new JList(s);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		frame.add(new JScrollPane(list));
		frame.setSize(600, 500);
		frame.setVisible(true);
		// frame.add(list);

		customerList = new JList();
		customerList
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		sel = new JButton("Add");
		sel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				customerList.setListData(list.getSelectedValues());
			}
		});
		frame.add(sel);
		frame.add(new JScrollPane(customerList));

		checkout = new JButton("Check Out");
		checkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ListModel n = customerList.getModel();
				String[] a = new String[20];

				// ///
				String[] upc = new String[20];
				// ///

				for (int i = 0; i < n.getSize(); i++) {
					a[i] = (String) n.getElementAt(i).toString();
					if (a[i] != null) {

						String[] asdf = a[i].split(":");

						upc[i] = asdf[1].substring(0, 1);
						System.out.println(upc[i] + "\n");

					}

				}

				float price = 0;
				for (int j = 0; j < upc.length; j++) {
					try {
						// connection.setAutoCommit(false);
						Statement state = con.createStatement();
						ResultSet r = state
								.executeQuery("SELECT * FROM Item WHERE UPC ="
										+ upc[j]);
						while (r.next()) {
							String temp = r.getString("price");
							price += Float.parseFloat(temp);
							System.out.println(price + "\n");

						}
						con.commit();
						state.close();
					} catch (SQLException error) {
						System.out.println(error.getMessage());
					}
				}
				new CreditCardInfo(con, fr, id, price, upc);
				frame.dispose();

			}
		});
		frame.add(checkout);

		frame.setVisible(true);

	}
}
