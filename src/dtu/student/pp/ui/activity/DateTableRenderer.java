package dtu.student.pp.ui.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.swing.table.DefaultTableCellRenderer;

public class DateTableRenderer extends DefaultTableCellRenderer {
	private final static SimpleDateFormat df = new SimpleDateFormat("ww-YY"); //Date format for start and end date
	public void setValue(Object value) {
		if(value!=null)
			value = df.format(value);
		super.setValue(value);
	}
}