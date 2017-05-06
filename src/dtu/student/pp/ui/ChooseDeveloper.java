package dtu.student.pp.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChooseDeveloper extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChooseDeveloper dialog = new ChooseDeveloper();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChooseDeveloper() {
		String[] values = new String[] {"DK", "PZ", "SKU", "VR", "ZP", "RD", "PET", "ZET", "KET"};
		
		Object[] possibleValues = values;
    	Object selectedValue = JOptionPane.showInputDialog(null,
    	"Choose a Developer", "Developer",
    	JOptionPane.INFORMATION_MESSAGE, null,
    	possibleValues, possibleValues[0]);
    	
	}

}
