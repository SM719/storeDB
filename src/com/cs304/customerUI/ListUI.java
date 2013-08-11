package com.cs304.customerUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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

	JButton sel, checkout;

	public ListUI(Connection c, String[] s, final String[] pr, JFrame f,
			String i) {

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
				// String[] p = new String[20];
				int price = 0;

				for (int i = 0; i < n.getSize(); i++) {
					a[i] = (String) n.getElementAt(i).toString();
					/*
					 * if ((a[i] != null) && (pr[i] != null) &&
					 * (a[i].contains(pr[i]))) { price +=
					 * Integer.valueOf(pr[i]);
					 * 
					 * System.out.println("total price =" + price); }
					 */
				}

				new CreditCardInfo(con, fr, id);
				frame.dispose();

			}
		});
		frame.add(checkout);

		frame.setVisible(true);
		/*
		 * list.addListSelectionListener(new ListSelectionListener() {
		 * 
		 * public void valueChanged(ListSelectionEvent e) {
		 * 
		 * if (list.getSelectedIndex() == 1) { System.out.println("1\n"); } if
		 * (list.getSelectedIndex() == 2) { System.out.println("2\n"); }
		 * 
		 * }
		 * 
		 * });
		 */

	}

}
