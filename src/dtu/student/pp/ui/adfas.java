package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLayeredPane;

public class adfas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			adfas dialog = new adfas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public adfas() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Assistants", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{71, 0};
				gbl_panel_1.rowHeights = new int[]{64, 24, 0};
				gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				{
					JList list = new JList();
					list.setModel(new AbstractListModel() {
						String[] values = new String[] {"HEJ", "ASSI", "1234"};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					GridBagConstraints gbc_list = new GridBagConstraints();
					gbc_list.fill = GridBagConstraints.BOTH;
					gbc_list.insets = new Insets(0, 0, 5, 0);
					gbc_list.gridx = 0;
					gbc_list.gridy = 0;
					panel_1.add(list, gbc_list);
				}
				{
					JButton button = new JButton("Remove");
					button.setEnabled(false);
					GridBagConstraints gbc_button = new GridBagConstraints();
					gbc_button.fill = GridBagConstraints.BOTH;
					gbc_button.gridx = 0;
					gbc_button.gridy = 1;
					panel_1.add(button, gbc_button);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.CENTER);
				panel_2.setBorder(new TitledBorder(null, "Staff", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagLayout gbl_panel_2 = new GridBagLayout();
				gbl_panel_2.columnWidths = new int[]{71, 0};
				gbl_panel_2.rowHeights = new int[] {64, 24, 0};
				gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
				gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				panel_2.setLayout(gbl_panel_2);
				{
					JButton btnNewButton = new JButton("Remove");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					{
						JList list = new JList();
						list.setModel(new AbstractListModel() {
							String[] values = new String[] {"TEST", "NAVN", "INIT", "L"};
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
						list.setSelectedIndex(1);
						GridBagConstraints gbc_list = new GridBagConstraints();
						gbc_list.fill = GridBagConstraints.BOTH;
						gbc_list.insets = new Insets(0, 0, 5, 0);
						gbc_list.gridx = 0;
						gbc_list.gridy = 0;
						panel_2.add(list, gbc_list);
					}
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.fill = GridBagConstraints.BOTH;
					gbc_btnNewButton.gridx = 0;
					gbc_btnNewButton.gridy = 1;
					panel_2.add(btnNewButton, gbc_btnNewButton);
					panel_2.setVisible(false);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
