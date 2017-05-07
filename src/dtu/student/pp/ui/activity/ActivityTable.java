package dtu.student.pp.ui.activity;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
		
		//Create sorter
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		//Default to sort with new activities first
		sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
		
		//Number
		getColumnModel().getColumn(0).setPreferredWidth(30);
		//Name
		//Dates
		DateTableRenderer dateRenderer = new DateTableRenderer();
		getColumnModel().getColumn(2).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(3).setCellRenderer(dateRenderer);
		getColumnModel().getColumn(2).setPreferredWidth(30);
		getColumnModel().getColumn(3).setPreferredWidth(30);
		//Number of personel
		getColumnModel().getColumn(4).setPreferredWidth(40);
		getColumnModel().getColumn(5).setPreferredWidth(40);
		//Hours
		getColumnModel().getColumn(6).setPreferredWidth(35);
		//Sum of hours
		getColumnModel().getColumn(7).setPreferredWidth(40);
	}
	
	@Override //JTable implements listselectionlistener
	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		if(getSelectedRow()!=-1) {
			AbstractActivity activity = ((ActivityTableModel) this.getModel()).getActivityAt(convertRowIndexToModel(getSelectedRow()));
			mainWindow.setSelectedActivity(activity);
		}
	}
}


