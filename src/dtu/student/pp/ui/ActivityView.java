package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatter;

import dtu.student.pp.data.activity.NormalActivity;

public class ActivityView extends JDialog implements ActionListener {
	
	boolean canceled = true;
	String name = null;
	Date startDate = null;
	Date endDate = null;
	List<String> staffToRemove = new ArrayList<String>();

	
	public ActivityView(NormalActivity act, boolean isLeader) {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("View activity #" + act.getActivityID() );
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new FlowLayout());
		
		JPanel leftPane = new JPanel();
		mainPane.add(leftPane);
		getContentPane().add(mainPane, BorderLayout.CENTER);
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		
		JTextField nameField = new JTextField(10);
		if(act.getName()!=null)
			nameField.setText(act.getName());
		if(act.getName()!=null)
			nameField.setToolTipText("Original name: " + act.getName());
		JPanel namePane = new JPanel(new BorderLayout());
		namePane.setBorder(new TitledBorder("Name"));
		namePane.add(nameField);
		leftPane.add(namePane);
		
		leftPane.add(Box.createVerticalStrut(7));
		
		String toolTip = "The date format is Week-Year";
		
		JPanel startDatePane = new JPanel(new BorderLayout());
		startDatePane.setBorder(new TitledBorder("Start date"));
		leftPane.add(startDatePane);
		JSpinner startSpinner = new JSpinner(new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.WEEK_OF_YEAR));
		startSpinner.setToolTipText(toolTip);
		startSpinner.setEditor(new JSpinner.DateEditor(startSpinner, "ww-YY"));
		((DefaultFormatter)
				((JFormattedTextField) 
						startSpinner.getEditor().getComponent(0))
				.getFormatter())
		.setCommitsOnValidEdit(true);
		startSpinner.setEnabled(isLeader);
		if(act.getStart()!=null) {
			startSpinner.getModel().setValue(act.getStart().getTime());
			startDatePane.add(startSpinner, BorderLayout.CENTER);
		} else {
			JButton setStart = new JButton("Set start date.");
			setStart.setEnabled(isLeader);
			if(!isLeader)
				setStart.setText("Not set");
			startDatePane.add(setStart, BorderLayout.CENTER);
			setStart.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					startDatePane.remove(setStart);
					startDatePane.add(startSpinner);
					startDatePane.revalidate();
				}
			});
		}
		
		
		JPanel endDatePane = new JPanel(new BorderLayout());
		endDatePane.setBorder(new TitledBorder("End date"));
		leftPane.add(endDatePane);
		JSpinner endSpinner = new JSpinner(new SpinnerDateModel(Calendar.getInstance().getTime(), null, null, Calendar.WEEK_OF_YEAR));
		endSpinner.setToolTipText(toolTip);
		endSpinner.setEditor(new JSpinner.DateEditor(endSpinner, "ww-YY"));
		((DefaultFormatter)
				((JFormattedTextField) 
						endSpinner.getEditor().getComponent(0))
				.getFormatter())
		.setCommitsOnValidEdit(true);
		endSpinner.setEnabled(isLeader);
		if(act.getEnd()!=null) {
			endSpinner.getModel().setValue(act.getEnd().getTime());
			endDatePane.add(endSpinner, BorderLayout.CENTER);
		} else {
			JButton setEnd = new JButton("Set end date.");
			if(!isLeader)
				setEnd.setText("Not set");
			setEnd.setEnabled(isLeader);
			endDatePane.add(setEnd, BorderLayout.CENTER);
			setEnd.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					endDatePane.remove(setEnd);
					endDatePane.add(endSpinner);
					endDatePane.revalidate();
				}
			});
		}
		nameField.setEditable(isLeader);
		
		nameField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				name = nameField.getText();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				name = nameField.getText();
				if(name.length()==0)
					name = null;
			}
		});
		
		ChangeListener spinnerListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(e.getSource().equals(startSpinner.getModel()))
					startDate = ((Date) startSpinner.getModel().getValue());
				else if (e.getSource().equals(endSpinner.getModel()))
					endDate = ((Date) endSpinner.getModel().getValue());
			}
		};
		
		startSpinner.getModel().addChangeListener(spinnerListener);
		endSpinner.getModel().addChangeListener(spinnerListener);
		
		
		
		JPanel rightPane = new JPanel();
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.X_AXIS));
		
		JPanel staffPane = new JPanel(new BorderLayout());
		staffPane.setBorder(new TitledBorder("Staffing"));
		DefaultListModel<String> staffList = new DefaultListModel<String>();
		for(String dev:act.getStaff())
			staffList.addElement(dev);
		JList<String> selectionStaff = new JList<String>(staffList);
		JScrollPane staffScroll = new JScrollPane(selectionStaff, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		staffPane.add(staffScroll, BorderLayout.CENTER);
		JButton remStaff = new JButton("Remove");
		staffPane.add(remStaff, BorderLayout.SOUTH);
		JPanel assistPane = new JPanel(new BorderLayout());
		assistPane.setBorder(new TitledBorder("Assistants"));
		DefaultListModel<String> assistList = new DefaultListModel<String>();
		for(String dev:act.getAssist())
			assistList.addElement(dev);
		JList<String> selectionAssist = new JList<String>(assistList);
		assistPane.add(new JScrollPane(selectionAssist, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		JButton remAssist = new JButton("Remove");
		remStaff.setEnabled(isLeader);
		remAssist.setEnabled(isLeader);
		assistPane.add(remAssist, BorderLayout.SOUTH);
		rightPane.add(staffPane);
		rightPane.add(assistPane);
		
		ActionListener removeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(remStaff)) {
					for(int i:selectionStaff.getSelectedIndices()) {
						staffToRemove.add(staffList.getElementAt(i));
						staffList.remove(i);
					}
				} else if (e.getSource().equals(remAssist)) {
					for(int i:selectionAssist.getSelectedIndices()) {
						staffToRemove.add(assistList.getElementAt(i));
						assistList.remove(i);
					}
				}
			}
		};
		remAssist.addActionListener(removeListener);
		remStaff.addActionListener(removeListener);
		
		
		mainPane.add(rightPane);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton ok = new JButton("Apply changes");
		ok.setEnabled(isLeader);
		ok.setActionCommand("OK");
		
		JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		if(isLeader) {
			buttonPane.add(ok);
			getRootPane().setDefaultButton(ok);
		} else
			getRootPane().setDefaultButton(cancel);
		buttonPane.add(cancel);
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
		//setContentPane(contentPane);
		
		selectionAssist.setVisibleRowCount(6);
		selectionStaff.setVisibleRowCount(6);
		selectionAssist.setFixedCellWidth(5);
		selectionStaff.setFixedCellWidth(5);		
		pack();
		setResizable(false);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel"))
			dispose();
		else if (e.getActionCommand().equals("OK")) {
			System.out.println("wat"+startDate+" "+endDate);
			if(startDate!=null && endDate!=null) {
				System.out.println(startDate+" "+endDate+" "+startDate.after(endDate));
				if(startDate.after(endDate)) {
					JOptionPane.showConfirmDialog(this, 
							"The start date has to come before the end date!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					this.canceled = false;
					dispose();
				}
			} else {
				this.canceled = false;
				dispose();
			}
		}
	}
	
}
