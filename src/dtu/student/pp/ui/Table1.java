package dtu.student.pp.ui;

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
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
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

public class Table1 extends JTable {
	
	public class TableModel1 extends AbstractTableModel {
		
		final NumberFormat nf;
		String[] columnNames = new String[] {"#", "Name", "Start", "End", "Hours"};
	    private AbstractActivity[] activities;
	    
		
	    public void setData(Set<AbstractActivity> activities) {
	    	this.activities = activities.toArray(new AbstractActivity[activities.size()]);
	    	this.fireTableDataChanged();
	    }
	    
	    public TableModel1(Set<AbstractActivity> activities, NumberFormat nf) {
	    	this.nf = nf;
	    	setData(activities);
	    }
	    
	    @Override
	    public String getColumnName(int columnIndex) {
	    	return columnNames[columnIndex];
	    }
	    
	    @Override
	    public int getRowCount() {
	        return activities.length;
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        AbstractActivity user = activities[rowIndex];
	        switch (columnIndex) {
	            case 0:
	                return user.getActivityID();
	            case 1:
	                return user.getName();
	            case 2:
	            	return user.getStart();
	            case 3:
	            	return user.getEnd();
	            case 4:
	            	return nf.format(user.getHours("TEST"));
	            default: return "???";
	        }

	    }

	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	    	switch(columnIndex) {
	    	case 0: return Integer.class;
	    	case 1: return String.class;
	    	case 2:
	    	case 3: return Calendar.class;
	    	case 4: return String.class; //Handle hours as string to preserve formatting
	    	default: return String.class;
	    	}
	    }
	    
	    //Override this if you want the values to be editable...
	    @Override
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    	
	    	if(columnIndex==4) {
				try {
					float value = nf.parse(aValue.toString()).floatValue();
					getUserAt(rowIndex).registerHours("TEST", Math.abs(value));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	fireTableCellUpdated(rowIndex, columnIndex);
	    }
	    
	    public AbstractActivity getUserAt(int row) {
	        return activities[row];
	    }
	    
	    @Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	return columnIndex == 4;
		}
	}
	
	final TableModel1 model;
	public void setData(Set<AbstractActivity> activities) {
		model.setData(activities);
	}
	
	public Table1(Set<AbstractActivity> activity) {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		NumberFormat nf = DecimalFormat.getInstance();
		model = new TableModel1(activity, nf);
    	this.setModel(model);
		
		//Create comparators for sorting
		final Comparator<String> hoursComparator = new Comparator<String>() {
			@Override
			public int compare(String n0, String n1) {
				try {
					return Float.compare(
							nf.parse(n0).floatValue(),
							nf.parse(n1).floatValue());
				} catch(ParseException e) {
					e.printStackTrace();
				}
				return 0;
			}
		};
		final Comparator<Calendar> dateComparator = new Comparator<Calendar>() {
			@Override
			public int compare(Calendar c0, Calendar c1) {return c0.compareTo(c1);}
		};
		
		//Create sorter
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		sorter.setComparator(2, dateComparator);
		sorter.setComparator(3, dateComparator);
		sorter.setComparator(4, hoursComparator);
		
		//Create renderers
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		DefaultTableCellRenderer calendarRenderer = new DefaultTableCellRenderer() {
			SimpleDateFormat f = new SimpleDateFormat("ww-YY"); //Date format for start and end date
			public void setValue(Object value) {
				if(value!=null) {
					Calendar cal = (Calendar) value;
					super.setValue(f.format(cal.getTime()));
				} else {
					super.setValue(value);
				}
			}
		};
		this.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		this.getColumnModel().getColumn(2).setCellRenderer(calendarRenderer);
		this.getColumnModel().getColumn(3).setCellRenderer(calendarRenderer);
		
	}

	
}
