package dtu.student.pp.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {
	private JTextField txtInitials;

	/**
	 * Create the panel.
	 */
	public Login() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblLogin);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel.add(verticalStrut);
		
		txtInitials = new JTextField();
		txtInitials.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(txtInitials);
		txtInitials.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOk.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblLogin.setLabelFor(btnOk);
		panel.add(btnOk);

	}

}
