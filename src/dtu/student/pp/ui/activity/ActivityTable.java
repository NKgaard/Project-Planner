package dtu.student.pp.ui.activity;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.ui.MainWindow;

@SuppressWarnings("serial")
public class ActivityTable extends JTable {
	
	final MainWindow listener;
	/**
	 * @Author Nicolai Kammersgård (s143780)
	 */
	public ActivityTable(MainWindow listener, ActivityTableModel model) {
		this.listener = listener;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	setModel(model);
    	//Default to sort with new activities first
    	setAutoCreateRowSorter(true);
		getRowSorter().setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
		
		//Number
		getColumnModel().getColumn(0).setMinWidth(25);
		//Name
		getColumnModel().getColumn(1).setMinWidth(40);
		//Dates
		DateTableRenderer dateRenderer = new DateTableRenderer();
		getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(3).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(2).setMinWidth(35);
		getColumnModel().getColumn(3).setMinWidth(35);
		//Number of personel
		getColumnModel().getColumn(4).setMinWidth(45);
		getColumnModel().getColumn(5).setMinWidth(45);
		//Hours
		getColumnModel().getColumn(6).setMinWidth(35);
		//getColumnModel().getColumn(6).setCellRenderer(cellRenderer);
		//Sum of hours
		getColumnModel().getColumn(7).setMinWidth(40);
		
		//Add cursor to show that a field is editable
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent arg0) {}

			@Override
			public void mouseMoved(MouseEvent e) {
				Point point = e.getPoint();
				int col = convertColumnIndexToModel(columnAtPoint(point));
				int row = convertRowIndexToModel(rowAtPoint(point));
				if(getModel().isCellEditable(row, col)) {
					setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
				} else setCursor(Cursor.getDefaultCursor());
			}
		});
	}
	
	@Override //JTable implements listselectionlistener
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		if(listener!=null && getSelectedRow()!=-1) {
			AbstractActivity activity = ((ActivityTableModel) this.getModel()).getActivityAt(convertRowIndexToModel(getSelectedRow()));
			listener.setSelectedActivity(activity);
		}
	}
}


