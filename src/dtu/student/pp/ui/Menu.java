package dtu.student.pp.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class Menu extends JPanel implements ActionListener {
	JLabel currentMenu = new JLabel("Menu");
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public Menu() {
		//No back button, since there is only two layers.
		//My activities <-> all activities cycle
		
		//menuHolder.add(menuPane, BorderLayout.NORTH);
		GridBagLayout gbl_menuPane = new GridBagLayout();
		gbl_menuPane.columnWidths = new int[] {100, 0};
		gbl_menuPane.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_menuPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_menuPane.rowWeights = new double[]{0.0};
		this.setLayout(gbl_menuPane);
		
		currentMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
		currentMenu.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_currentMenu = new GridBagConstraints();
		gbc_currentMenu.insets = new Insets(0, 0, 5, 0);
		gbc_currentMenu.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentMenu.gridx = 0;
		gbc_currentMenu.gridy = 0;
		this.add(currentMenu, gbc_currentMenu);
		
		for(int i=1; i<11; i++) {
			String num = Integer.toString(i%10);
			JButton b = new JButton(num);
			b.setMnemonic(num.charAt(0));
			
			b.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_b = new GridBagConstraints();
			gbc_b.fill = GridBagConstraints.BOTH;
			gbc_b.gridx = 0;
			gbc_b.gridy = i;
			
			b.addActionListener(this);
			buttons.add(b);
			add(b, gbc_b);
		}
		
		
		MenuItem activities = new MenuItem("Activities", 0);
		MenuItem myActivities = new MenuItem("My activites", 0);
		
		MenuItem projects  = new MenuItem("Projects",   1);
		MenuItem newProject = new MenuItem("New project",    2);
		MenuItem myProjects = new MenuItem("My projects",    1);
		
		items.put(activities.itemText, activities);
		items.put(myActivities.itemText, myActivities);
		items.put(projects.itemText, projects);
		items.put(newProject.itemText, newProject);
		items.put(myProjects.itemText, myProjects);
		
		//Layer 1 buttons
		activities.subMenus.add(projects);
		activities.subMenus.add(myActivities);
		projects.subMenus.add(activities);
		projects.subMenus.add(myProjects);
		projects.subMenus.add(newProject);
		
		myActivities.subMenus = Arrays.asList(activities, projects);
		myProjects.subMenus = Arrays.asList(activities, projects);
		//Layer 2
		
		setItems(Arrays.asList(activities, projects));
	}
	
	HashMap<String, MenuItem> items = new HashMap<String, MenuItem>();
	
	public void setItems(List<MenuItem> items) {
		for (JButton b:buttons) {
			b.setEnabled(true);
			b.setVisible(false);
		}
		
		for (MenuItem item:items) {
			JButton b = buttons.get(item.placement);
			b.setText((item.placement+1)%10+" "+item.itemText);
			b.setVisible(true);
			if(item.placement!=9)
				b.setActionCommand(item.itemText);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuItem item = items.get(e.getActionCommand());
		System.out.println(e.getActionCommand());
		if(item!=null) {
			if(item.itemText.equals("New Project")) {
				
			} else {
				currentMenu.setText(e.getActionCommand());
				setItems(item.subMenus);
			}
		}
	}
	
	class MenuItem {
		final String itemText;
		final int placement;
		List<MenuItem> subMenus = new ArrayList<MenuItem>();
		public MenuItem(String text, int placement) {
			this.itemText = text;
			this.placement = placement;
			
		}
		
		@Override
		public String toString() {
			return itemText;
		}
	}
	
	
}

