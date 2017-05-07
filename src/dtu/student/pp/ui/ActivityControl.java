package dtu.student.pp.ui;





import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.comparators.Interval;
import dtu.student.pp.data.project.Project;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.*;

public class ActivityControl extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Create the dialog.
	 */
	
	public ActivityControl(NormalActivity temp2, boolean check) {
		this.setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			String name = temp2.toString();
			int year = temp2.getStart().get(Calendar.YEAR);
			int week = temp2.getStart().get(Calendar.WEEK_OF_YEAR);
	

		
		
		
		
		setTitle("Act: " + name + " From: W " + week + " Y " + year);
		setBounds(100, 100, 250, 564);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Activity End Date", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Remove Members", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Activity Start Date", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(temp2.getStart().get(Calendar.WEEK_OF_YEAR), 1, 52, 1));
		spinner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Week:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(temp2.getStart().get(Calendar.YEAR), 1990, 9999, 1));
		spinner_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Year:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		JButton btnNewButton_4 = new JButton("Set Start Date");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addGap(48)
					.addComponent(btnNewButton_4)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_4)
					.addGap(9))
		);
		panel_4.setLayout(gl_panel_4);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
		);
		
		JButton btnNewButton_2 = new JButton("OK");
		btnNewButton_2.setActionCommand("OK");
		getRootPane().setDefaultButton(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.setActionCommand("Cancel");
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3)
						.addComponent(btnNewButton_2))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JButton btnNewButton = new JButton("Remove");
		
		
		JButton btnNewButton_1 = new JButton("Remove\r\n");
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		DefaultListModel model = new DefaultListModel();
		for (Object O:temp2.getStaff()){
			model.addElement(O);
		}
		JList list_1 = new JList(model);
		scrollPane.setViewportView(list_1);
		
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list_1.setModel(new AbstractListModel() {
		//	Object[] values = temp2.getStaff().toArray();
		//	
		//	public int getSize() {
		//		return values.length;
		//	}
		//	public Object getElementAt(int index) {
		//		return values[index];
		//	}
		//});
		
		scrollPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Staff", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
	
		
		
		DefaultListModel model1 = new DefaultListModel();
		for (Object O:temp2.getAssist()){
			model1.addElement(O);
		}
		
		JScrollPane scrollPane1 = new JScrollPane();
		JList list = new JList(model1);
		
		scrollPane1.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPane1.setBorder(new TitledBorder(null, "Assistants", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JSpinner spinner_2 = new JSpinner();
		
		spinner_2.setModel(new SpinnerNumberModel(52, 1, 52, 1));
		spinner_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Week:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(2017, 1990, 9999, 1));
		spinner_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Year:", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		JButton btnNewButton_5 = new JButton("Set End Date");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(spinner_2, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
					.addGap(52)
					.addComponent(btnNewButton_5)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_5)
					.addGap(24))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		
		JLabel lblNewLabel_1 = new JLabel("Disposed Time");
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setText(temp2.toString());
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.TRAILING);
		
		String strAmount=String.valueOf(temp2.workLeft());
		textField_1.setText(strAmount);
		textField_1.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textField_1, 0, 0, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(20))
		);
		
		//Opstartslogik til datofelterne
		//Deaktiver datofelter hvis der ikke eksisterer datoer
		if (temp2.getEnd() == null){
			spinner_2.setEnabled(false);
			spinner_3.setEnabled(false);
			
		} else {
			spinner_2.setValue(temp2.getEnd().get(Calendar.WEEK_OF_YEAR));
			spinner_3.setValue(temp2.getEnd().get(Calendar.YEAR));
			btnNewButton_5.setEnabled(false);
			
		}
		
		if (temp2.getStart() == null){
			spinner.setEnabled(false);
			spinner_1.setEnabled(false);
		} else {
			spinner.setValue(temp2.getStart().get(Calendar.WEEK_OF_YEAR));
			spinner_1.setValue(temp2.getStart().get(Calendar.YEAR));
			btnNewButton_4.setEnabled(false);
			
			
		}
		//Aktiver datofelter hvis der trykkes på "set.."
		btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//Enable de to datofelter og giv dem værdier
            	//disable set knappen
            	spinner.setEnabled(true);
            	spinner.setValue(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            	spinner_1.setEnabled(true);
            	spinner_1.setValue(Calendar.getInstance().get(Calendar.YEAR));
            	btnNewButton_4.setEnabled(false);
            	
            }
        });
		btnNewButton_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Enable de to datofelter og giv dem værdier
            	//disable set knappen
            	spinner_2.setEnabled(true);
            	spinner_2.setValue(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
            	spinner_3.setEnabled(true);
            	spinner_3.setValue(Calendar.getInstance().get(Calendar.YEAR));
            	btnNewButton_5.setEnabled(false);
            	
            }
        });
		
		
		//Tjek om bruger kan redigere i aktiviteten
		if (check==false){
			textField.setEditable(false);
			btnNewButton.setEnabled(false);
			btnNewButton_1.setEnabled(false);
			
			spinner.setEnabled(false);
			spinner_1.setEnabled(false);
			spinner_2.setEnabled(false);
			spinner_3.setEnabled(false);
		}
		
		
		//Remove knapperne
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//Remove selected staff (list_1.getSelectedValue())
            	temp2.removeStaff(list_1.getSelectedValue().toString());
            	model.remove(list_1.getSelectedIndex());
            	
            	
            }
        });
		btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//Remove selected assistant (list.getSelectedValue())
            	temp2.removeStaff(list.getSelectedValue().toString());
            	
            	model1.remove(list.getSelectedIndex());
            }
        });
		btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
            	//Save all changes and exit window
            	//Gem aktivitetsnummer
            	//Gem slutdato
            	temp2.setName(textField.getText() + year);
            	
            	if(Interval.verifyTime((int) spinner.getValue(), (int) spinner_1.getValue(), (int) spinner_2.getValue(), (int) spinner_3.getValue())){
            		if (spinner.isEnabled() && spinner_1.isEnabled()){
            			temp2.setStart((int) spinner.getValue(), (int) spinner_1.getValue());
            		}
            		if (spinner_2.isEnabled() && spinner_3.isEnabled()){
            			temp2.setEnd((int) spinner_2.getValue(), (int) spinner_3.getValue());
            		}
            		
            	}
            	
            	
            	dispose();
            	
            }
        });
		btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	dispose();
            	
            	//close window
            }
        });
		
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		{
		}
		setVisible(true);
			 }	

			 
}
