package dtu.student.pp.ui.activity;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import dtu.student.pp.PPState;
import dtu.student.pp.ProjectPlanner;
import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.ui.MainWindow;

public class ActivityTable extends JTable {
	
	final MainWindow mainWindow;
	
	public ActivityTable(MainWindow mainWindow, ActivityTableModel model) {
		this.mainWindow = mainWindow;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
    	setModel(model);
		//Create comparators for sorting
		final Comparator<Calendar> dateComparator = new Comparator<Calendar>() {
			@Override
			public int compare(Calendar c0, Calendar c1) {return c0.compareTo(c1);}
		};
		final Comparator<Set<?>> setComparator = new Comparator<Set<?>>() {
			@Override
			public int compare(Set<?> c0, Set<?> c1) {return Integer.compare(c0.size(), c1.size());}
		};
		
		//Create sorter
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		sorter.setComparator(2, dateComparator);
		sorter.setComparator(3, dateComparator);
		sorter.setComparator(4, model.hoursComparator);
		sorter.setComparator(5, setComparator);
		sorter.setComparator(6, setComparator);
		//Default to sort with new activities first
		sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
		
		//Create renderers
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		ActivityTableRenderer renderer = new ActivityTableRenderer();
		
		getColumnModel().getColumn(0).setCellRenderer(renderer);
		getColumnModel().getColumn(1).setCellRenderer(renderer);
		getColumnModel().getColumn(2).setCellRenderer(renderer);
		getColumnModel().getColumn(3).setCellRenderer(renderer);
		getColumnModel().getColumn(4).setCellRenderer(renderer);
		getColumnModel().getColumn(5).setCellRenderer(renderer);
		getColumnModel().getColumn(6).setCellRenderer(renderer);
	}
	
	@Override //JTable implements listselectionlistener
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		if(getSelectedRow()!=-1) {
			AbstractActivity activity = ((ActivityTableModel) this.getModel()).getActivityAt(convertRowIndexToModel(getSelectedRow()));
			mainWindow.setSelectedActivity(activity);
		}

	}
	
	public class ActivityTableRenderer extends DefaultTableCellRenderer {
		private final SimpleDateFormat df = new SimpleDateFormat("ww-YY"); //Date format for start and end date
		
		ActivityTableRenderer() {
			super();
			setHorizontalAlignment(SwingConstants.RIGHT);
		}
		
		public void setValue(Object value) {
			
			if(value!=null) {
				if (value instanceof Calendar) {
					value = df.format(((Calendar) value).getTime());
				} else if (value instanceof Set<?>) {
					value = ((Set<?>) value).size();
				}
				
			}
			
			super.setValue(value);
		}
	}
}
