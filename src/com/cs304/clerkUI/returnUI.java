package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class returnUI implements ActionListener {
	Connection connect;
	private JFrame tframe;
	JFrame testframe, testframe2;
	JButton Breturn, cancel;
	JTextField textfields;
	JLabel rid;

	public returnUI(Connection c, JFrame frame) {
		tframe = frame;
		connect = c;

		testframe = new JFrame("Returns");
		JPanel p = new JPanel();

		testframe.setVisible(true);
		testframe.setSize(400, 400);

		Dimension dim = testframe.getToolkit().getScreenSize();
		Rectangle r = testframe.getBounds();
		testframe.setLocation((dim.width - r.width) / 2,
				(dim.height - r.height) / 2);

		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints gcon = new GridBagConstraints();

		Breturn = new JButton("Confirm");
		cancel = new JButton("Cancel");

		rid = new JLabel("ReceiptID: ");
		textfields = new JTextField();

		gcon.gridx = 0;
		gcon.gridy = 1;
		gcon.insets = new Insets(0, 0, 10, 10);
		grid.setConstraints(rid, gcon);
		p.add(rid);

		gcon.gridx = 1;
		gcon.gridy = 1;
		textfields.setColumns(10);
		grid.setConstraints(textfields, gcon);
		p.add(textfields);

		gcon.gridx = 0;
		gcon.gridy = 2;
		grid.setConstraints(Breturn, gcon);
		p.add(Breturn);

		gcon.gridx = 1;
		gcon.gridy = 2;
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
		String[] s1 = new String[50];
		String[] s2 = new String[50];

		s1[0] = "The payment form was in credit.";
		s2[0] = "The payment form was in cash";

		if (act.getSource() == cancel) {
			tframe.setVisible(true);
			testframe.dispose();
		}

		if (act.getSource() == Breturn) {
			// System.out.println("testpoint1 \n");
			if (!isInt(textfields.getText())) {

				JOptionPane.showMessageDialog(null,
						"Cannot be null or is not an int");
			} else if (isInt(textfields.getText()) == true) {
				GregorianCalendar calendar = new GregorianCalendar();
				java.sql.Date sDate = new java.sql.Date(calendar.getTime()
						.getTime());
				// System.out.println("testpoint5 \n");

				try {

					connect.setAutoCommit(false);

					Statement state = connect.createStatement();

					ResultSet r = state
							.executeQuery("SELECT COUNT(*) FROM PURCHASE WHERE receiptID ="
									+ textfields.getText()
									+ "AND DATEDIFF(day,GETDATE(),date) < 15 )");
					System.out.println("passed point 1 \n");
					if (r.first() == false) {

						JOptionPane.showMessageDialog(null,
								"receiptID does not exist");
						System.out.println("passed point 2 \n");
					} else {
						r = state
								.executeQuery("SELECT * FROM Purchase WHERE receiptID ="
										+ textfields.getText());
						System.out.println("passed point 3 \n");
						ResultSet r1 = state
								.executeQuery("SELECT * FROM PurchaseItem WHERE receiptID ="
										+ textfields.getText());
						System.out.println("passed point 4 \n");
						int upc = r1.getInt("UPC");
						System.out.println("passed point 5 \n");
						ResultSet r2 = state
								.executeQuery("SELECT * FROM Item WHERE UPC ="
										+ upc);
						System.out.println("passed point 6 \n");

						if (r.getString("cardnum") == null) {
							int i = 1;
							s2[i] = r2.getString("UPC");
							JOptionPane.showMessageDialog(null, new JList(s2));

						} else {
							s1[1] = r2.getString("UPC") + r.getString("card#");
							JOptionPane.showMessageDialog(null, new JList(s1));
						}

					}
					connect.commit();
					state.close();
				} catch (SQLException error) {
					System.out.println(error.getMessage());
				}

			}

		}

	}

	public boolean isInt(String s) {

		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}

}
