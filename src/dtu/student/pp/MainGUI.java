package dtu.student.pp;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
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
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

public class MainGUI extends JPanel implements ActionListener{
	
	private JButton OpretProjektKnap = new JButton("Opret Projekt");
	private JButton TestKnaplol = new JButton("Testknap");
	
	private JTextField OpretProjektFelt = new JTextField();

	 public MainGUI() {
	        super(new GridLayout(1, 1));
	        JTabbedPane tabbedPane = new JTabbedPane();
	       
	        //Skrifttype til knapper
	        Font font = OpretProjektKnap.getFont();
	        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+5);
	        OpretProjektKnap.setFont(boldFont);
	       
	        //Ikoner til tabs
	        ImageIcon icon = createImageIcon("images/test2.png");
	        
	      
	        //Tab #1     
	        
	        JComponent panel1 = makePanel();
	        panel1.setLayout(new GridLayout(1, 3));
	        	      
	        tabbedPane.addTab("Projects", icon, panel1,"Manage Projects");
	        panel1.setPreferredSize(new Dimension(800, 600));
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        //Opret projektknap og felter
	        OpretProjektKnap.setHorizontalTextPosition(SwingConstants.LEADING);
	        panel1.add(OpretProjektKnap);
	        panel1.add(OpretProjektFelt);
	        OpretProjektKnap.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                OpretProjektFelt.setText("I was clicked");
	                
	            }
	        });
	       
	        
	        
	        
	        //Tab #2
	        
	        JComponent panel2 = makePanel();
	        
	        
	        tabbedPane.addTab("Tab 2", icon, panel2,
	                "Does twice as much nothing");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	        
	        
	        //Tab #3
	        JComponent panel3 = makePanel();
	       
	        tabbedPane.addTab("Tab 3", icon, panel3,
	                "Still does nothing");
	        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	        
	        
	        //Tab #4
	        JComponent panel4 = makePanel();
	        
	       
	        tabbedPane.addTab("Tab 4", icon, panel4, "Im useless");
	        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
	       
	        
	        //Adds the tabs onto the JPanel
	        add(tabbedPane);
	        
	        //Scroll enable
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	    }
	    
	

	 private Color Color(int i, int j, int k) {
			// TODO Auto-generated method stub
			return null;
		}

		protected JComponent makePanel() {
	        JPanel panel = new JPanel(false);
	        //panel.setLayout(new GridLayout(2, 3));
	        panel.setBackground(Color(100,100,100));
	       
	        return panel;
	    }
	    
	    
	    protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = MainGUI.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }
	    
	    
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Project Planner");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	        frame.setTitle("Project Planner V1.0");
			frame.setSize(800, 600);
	        //Add content to the window.
	        frame.add(new MainGUI(), BorderLayout.CENTER);
	        
	        //Icon for the whole java app
	        try {
	            frame.setIconImage(ImageIO.read(new File("Pictures/ICON.png")));
	        }
	        catch (IOException exc) {
	            exc.printStackTrace();
	        }
	        
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	    
	    public static void main(String[] args) {
	      
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	               
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