package dtu.student.pp.ui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import dtu.student.pp.data.comparators.Interval;
import dtu.student.pp.data.project.Project;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SpinnerNumberModel;

public class ProjectControl extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public ProjectControl(Project p, boolean check) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		String projektNummer = p.toString();
		setTitle("Projekt: " + projektNummer);
		setBounds(100, 100, 204, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edit Project", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Project Start Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Project End Date", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 52, 1));
		spinner_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Week", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(2017, 1990, 9999, 1));
		spinner_3.setBorder(new TitledBorder(null, "Year", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnNewButton_1 = new JButton("Set End Date");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_3, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(spinner_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(spinner_2, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addGap(45))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addComponent(panel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton_2 = new JButton("OK");
		
		JButton btnNewButton_3 = new JButton("Cancel");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnNewButton_3)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 52, 1));
		spinner.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Week", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(2017, 1990, 9999, 1));
		spinner_1.setBorder(new TitledBorder(null, "Year", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnNewButton = new JButton("Set Start Date");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinner_1, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(spinner_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(spinner, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addGap(53))
		);
		panel_1.setLayout(gl_panel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		if (p.getName() != null){
			textField.setText(p.getName());
		} else {
			textField.setText("Nameless");
		}
		
		textField.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Project Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		
		//Opstartslogik til datofelterne
				//Deaktiver datofelter hvis der ikke eksisterer datoer
				if (p.getEnd() == null){
					spinner_2.setEnabled(false);
					spinner_3.setEnabled(false);
					btnNewButton.setEnabled(true);
					
				} else {
					spinner_2.setValue(p.getEnd().get(Calendar.WEEK_OF_YEAR));
					spinner_3.setValue(p.getEnd().get(Calendar.YEAR));
					btnNewButton_1.setEnabled(false);
					
				}
				
				if (p.getStart() == null){
					spinner.setEnabled(false);
					spinner_1.setEnabled(false);
					btnNewButton.setEnabled(true);
				} else {
					spinner.setValue(p.getStart().get(Calendar.WEEK_OF_YEAR));
					spinner_1.setValue(p.getStart().get(Calendar.YEAR));
					btnNewButton.setEnabled(false);
					
					
				}
				//Aktiver datofelter hvis der trykkes på "set.."
				btnNewButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	
		            	//Enable de to datofelter og giv dem værdier
		            	//disable set knappen
		            	spinner.setEnabled(true);
		            	spinner.setValue(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
		            	spinner_1.setEnabled(true);
		            	spinner_1.setValue(Calendar.getInstance().get(Calendar.YEAR));
		            	btnNewButton.setEnabled(false);
		            	
		            }
		        });
				btnNewButton_1.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	//Enable de to datofelter og giv dem værdier
		            	//disable set knappen
		            	spinner_2.setEnabled(true);
		            	spinner_2.setValue(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
		            	spinner_3.setEnabled(true);
		            	spinner_3.setValue(Calendar.getInstance().get(Calendar.YEAR));
		            	btnNewButton_1.setEnabled(false);
		            	
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
				//textField.setEditable(check);
				//btnNewButton.setEnabled(check);
				//btnNewButton_1.setEnabled(check);
				//spinner.setEnabled(check);
				//spinner_1.setEnabled(check);
				//spinner_2.setEnabled(check);
				//spinner_3.setEnabled(check);
				
				btnNewButton_2.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	
		            	
		            	//Save all changes and exit window
		            	//Gem aktivitetsnummer
		            	//Gem slutdato
		            	if (textField.getText() != "Nameless"){
		            		p.setName(textField.getText());
		            	}
		            	
		            	
		            	if (btnNewButton.isEnabled()==false && check){
		            		p.setStart((int) spinner.getValue(), (int) spinner_1.getValue());
		            	}
		            	if (btnNewButton_1.isEnabled()==false && check){
		            		p.setEnd((int) spinner_2.getValue(), (int) spinner_3.getValue());
		            	}
		            	
		            	//if(Interval.verifyTime((int) spinner.getValue(), (int) spinner_1.getValue(), (int) spinner_2.getValue(), (int) spinner_3.getValue())){
		            		//p.setStart((int) spinner.getValue(), (int) spinner_1.getValue());
		            		//p.setEnd((int) spinner_2.getValue(), (int) spinner_3.getValue());
		            		//if (spinner.isEnabled()){
		            		//	p.setStart((int) spinner.getValue(), (int) spinner_1.getValue());
		            		//	
		            		//}
		            		//if (spinner_2.isEnabled()){
		            		//	p.setEnd((int) spinner_2.getValue(), (int) spinner_3.getValue());
		            		//}
		            		
		            	//}
		            	
		            	
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
				pack();
				setVisible(true);
	}
}
