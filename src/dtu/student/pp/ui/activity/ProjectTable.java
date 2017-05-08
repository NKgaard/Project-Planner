package dtu.student.pp.ui.activity;

import java.util.Collections;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dtu.student.pp.data.project.Project;
import dtu.student.pp.ui.MainWindow;

@SuppressWarnings("serial")
public class ProjectTable extends JTable {
	
	final MainWindow listener;
	
	/**
	 * @Author Sebastian Præsius (s164198)
	 */
	public ProjectTable(MainWindow listener, ProjectTableModel model) {
		this.listener = listener;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	setModel(model);
		
		//Create sorter
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		//Default to sort with new activities first
		sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
		
		//Number
		getColumnModel().getColumn(0).setMinWidth(40);
		getColumnModel().getColumn(0).setPreferredWidth(40);
		//Name
		getColumnModel().getColumn(1).setMinWidth(40);
		//Dates
		DateTableRenderer dateRenderer = new DateTableRenderer();
		getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(3).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(2).setMinWidth(35);
		getColumnModel().getColumn(3).setMinWidth(35);
	}
	
	@Override //JTable implements listselectionlistener
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		if(listener!=null && getSelectedRow()!=-1) {
			Project project = ((ProjectTableModel) this.getModel()).getProjectAt(convertRowIndexToModel(getSelectedRow()));
			listener.setSelectedProject(project);
		}
	}
}