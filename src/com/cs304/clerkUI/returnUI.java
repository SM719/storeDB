package com.cs304.clerkUI;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class returnUI implements ActionListener {
	private Connection connect;
	private JFrame tframe;
	JFrame testframe, testframe2;
	JButton Breturn, cancel;
	JTextField textfields;

	public returnUI(Connection c, JFrame frame) {
		tframe = frame;
		connect = c;

		testframe = new JFrame("Returns");
		new JPanel();

		testframe.setVisible(true);
		testframe.setSize(400, 400);

		Dimension dim = testframe.getToolkit().getScreenSize();
		Rectangle r = testframe.getBounds();
	}

	@Override
	public void actionPerformed(ActionEvent act) {
		// TODO Auto-generated method stub
		String[] s1 = new String[50];
		String[] s2 = new String[50];

		s1[0] = "The payment form was in credit.";
		s2[0] = "The payment form was in cash";

		if (act.getSource() == cancel) {

		}

		if (act.getSource() == Breturn) {

		}

	}

}
