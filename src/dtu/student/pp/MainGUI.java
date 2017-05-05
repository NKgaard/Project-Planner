package dtu.student.pp;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class MainGUI extends JPanel implements ActionListener{
	
	private JButton TestKnap = new JButton("Testknap");
	private JButton TestKnaplol = new JButton("Testknap");

	 public MainGUI() {
	        super(new GridLayout(1, 1));
	        
	        JTabbedPane tabbedPane = new JTabbedPane();
	        ImageIcon icon = createImageIcon("images/middle.gif");
	        
	        
	        JComponent panel1 = makeTextPanel("Panel #1");
	        	        panel1.setBackground(Color(100,100,100));
	        tabbedPane.addTab("Hej", icon, panel1,"Does nothing");
	        panel1.setPreferredSize(new Dimension(800, 600));
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        TestKnap.setHorizontalTextPosition(SwingConstants.LEADING);
	        panel1.add(TestKnap);
	        panel1.add(TestKnaplol);
	        
	        panel1.add(new JButton("hej"));
	        panel1.add(new JButton("hej"));
	        panel1.add(new JButton("hej"));
	        panel1.add(new JButton("hej"));
	        panel1.add(new JButton("hej"));
	       
	        
	        
	        
	        
	        
	        JComponent panel2 = makeTextPanel("Panel #2");
	        panel2.setBackground(Color(100,100,100));
	        
	        tabbedPane.addTab("Tab 2", icon, panel2,
	                "Does twice as much nothing");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	        
	        JComponent panel3 = makeTextPanel("Panel #3");
	        panel3.setBackground(Color(100,100,100));
	        tabbedPane.addTab("Tab 3", icon, panel3,
	                "Still does nothing");
	        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	        
	        JComponent panel4 = makeTextPanel(
	                "Panel #4.");
	        panel4.setBackground(Color(100,100,100));
	       
	        tabbedPane.addTab("Tab 4", icon, panel4, "Im useless");
	        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
	       
	        
	        //Add tabs to the JPanel
	        add(tabbedPane);
	        
	        //The following line enables to use scrolling tabs.
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    }
	    
	    private Color Color(int i, int j, int k) {
		// TODO Auto-generated method stub
		return null;
	}

		protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(JLabel.LEFT);
	        filler.setVerticalAlignment(JLabel.TOP);
	        panel.setLayout(new GridLayout(0, 2));
	        panel.add(filler);
	        return panel;
	    }
	    
	    /** Returns an ImageIcon, or null if the path was invalid. */
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = MainGUI.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
	    
	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method should be invoked from
	     * the event dispatch thread.
	     */
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Project Planner");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	        frame.setTitle("Project Planner V1.0");
			frame.setSize(800, 600);
	        //Add content to the window.
	        frame.add(new MainGUI(), BorderLayout.CENTER);
	        
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	    
	    public static void main(String[] args) {
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                //Turn off metal's use of bold fonts
			UIManager.put("swing.boldMetal", Boolean.FALSE);
			createAndShowGUI();
	            }
	        });
	    }

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}