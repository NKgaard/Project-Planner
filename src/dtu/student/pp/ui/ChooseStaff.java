package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.activity.NormalActivity;
import dtu.student.pp.data.activity.WorkHours;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;

public class ChooseStaff extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	public Set<String> selectedDevelopers = new HashSet<String>();
	private JTable table;
	
	/**
	 * Create the dialog.
	 */
	public ChooseStaff(ProjectPlanner planner, NormalActivity activ) {
		setTitle("Add staff to activity ");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		//Create developer selection table
		
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				//Get developers not already staff.
				Set<String> devNotStaff = planner.getDevelopers().stream()
				.filter(dev -> !activ.isStaff(dev)).collect(Collectors.toSet());
				Object[] columnNames = new String[]{"Intials", "Reg. Work", "Work Estimate"};
				String[] developers = devNotStaff.toArray(new String[devNotStaff.size()]);
				
				Object[][] data = new Object[developers.length][3];
				//Do work calculations
				HashMap<String, WorkHours> workEstimate = new HashMap<String, WorkHours>();
				HashMap<String, WorkHours> regHours = new HashMap<String, WorkHours>();
				
				for(AbstractActivity act:planner.getAllActivities()) {
					if(act instanceof NormalActivity) {
						NormalActivity activity = (NormalActivity) act;
						Set<String> staffing = new HashSet<String>();
						staffing.addAll(activity.getStaff());
						staffing.addAll(activity.getAssist());
						for(String dev:staffing) {
							//Lazy
							if(!workEstimate.containsKey(dev)) {
								workEstimate.put(dev, new WorkHours());
								regHours.put(dev, new WorkHours());
							}
							regHours.get(dev).registerHours(activity.getHours(dev));
							workEstimate.get(dev).registerHours(activity.workEstimate(activ));
						}
					}
				}
				
				for(int i=0; i<developers.length; i++) {
					data[i][0] = developers[i];
					WorkHours work = regHours.get(developers[i]);
					if(regHours.containsKey(developers[i])) {
						data[i][1] = regHours.get(developers[i]).getWork();//Autoboxing of float
						data[i][2] = workEstimate.get(developers[i]).getWork();
					}
				}
				
				table = new JTable(new DefaultTableModel(data, columnNames){
					@Override
					   public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
				
				table.setAutoCreateRowSorter(true);
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblSelectTheDevelopers = new JLabel(
					"<html><body>"
					+ "<p>Select the developers you want to add as staff.</p>"
					+ "<p>The work estimate is calculated from the timeframe of this activity.</p>"
					+ "</body></html");
			contentPanel.add(lblSelectTheDevelopers, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			this.dispose();
		} else if (e.getActionCommand().equals("OK")) {
			for(int row:table.getSelectedRows()) {
				selectedDevelopers.add((String)table.getModel()
						.getValueAt(table.convertRowIndexToModel(row), 0));
				this.dispose();
			}
		}
	}

}
