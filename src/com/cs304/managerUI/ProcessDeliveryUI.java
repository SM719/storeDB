package com.cs304.managerUI;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cs304.tables.Purchase;

public class ProcessDeliveryUI {

	Connection connection;

	private JFrame Frame, fr;

	private JTextField date;

	private JButton setDate;

	JTextField recD;
	private JButton cancel;

	public ProcessDeliveryUI(Connection connection, JFrame f) {
		// TODO Auto-generated constructor stub

		fr = f;
		this.connection = connection;

		Frame = new JFrame("Process order delivery");
		Frame.setVisible(true);
		Frame.setSize(400, 300);
		Dimension d = Frame.getToolkit().getScreenSize();
		Rectangle r = Frame.getBounds();
		Frame.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints g = new GridBagConstraints();

		JPanel panel = new JPanel();

		date = new JTextField("Set the delivery date: yy/mm/dd");
		setDate = new JButton("Set");
		cancel = new JButton("Cancel");
		recD = new JTextField("Enter receipt id");

		g.gridx = 0;
		g.gridy = 1;
		g.gridwidth = 5;
		g.fill = GridBagConstraints.HORIZONTAL;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(date, g);
		panel.add(date);

		g.gridx = 0;
		g.gridy = 2;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(recD, g);
		panel.add(recD);

		g.gridx = 0;
		g.gridy = 3;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(setDate, g);
		panel.add(setDate);

		g.gridx = 0;
		g.gridy = 4;
		g.insets = new Insets(10, 10, 10, 10);
		gb.setConstraints(cancel, g);
		panel.add(cancel);

		panel.setLayout(gb);

		theHandler Handler = new theHandler(this.connection);
		date.addActionListener(Handler);
		setDate.addActionListener(Handler);
		cancel.addActionListener(Handler);
		recD.addActionListener(Handler);

		Frame.add(panel);
	}

	// takes one method, whenever an event occurs
	private class theHandler implements ActionListener {
		Connection con;

		public theHandler(Connection connection) {
			this.con = connection;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == cancel) {
				Frame.dispose();
			} else if (event.getSource() == setDate) {
				try {
					con.setAutoCommit(false);
					Statement state = con.createStatement();
					ResultSet r = state
							.executeQuery("SELECT receiptID FROM Purchase");
					while (r.next()) {
						int rec = r.getInt("receiptID");
						if (rec == Integer.parseInt(recD.getText())) {
							new Purchase().setDate(con, rec, date.getText());
						}
					}
					JOptionPane.showMessageDialog(null, "Delivery Date Set");
					Frame.dispose();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
