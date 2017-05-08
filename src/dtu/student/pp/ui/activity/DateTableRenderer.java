package dtu.student.pp.ui.activity;

import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 
 * @author Sebastian Præsius (s164198)
 *
 */
@SuppressWarnings("serial")
public class DateTableRenderer extends DefaultTableCellRenderer {
	private final static SimpleDateFormat df = new SimpleDateFormat("ww-YYYY"); //Date format for start and end date
	public void setValue(Object value) {
		if(value!=null)
			value = df.format(value);
		super.setValue(value);
	}
}