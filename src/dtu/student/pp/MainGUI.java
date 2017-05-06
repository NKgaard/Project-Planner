package dtu.student.pp;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Component;
import dtu.student.pp.data.project.Project;
import dtu.student.pp.data.project.ProjectNumber;

public class MainGUI extends JPanel implements ActionListener{


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//Komponenter til fane 1
	private JButton OpretProjektKnap = new JButton("Opret Projekt");
	private JButton SletProjektKnap = new JButton("Slet Projekt");
	private JButton SetProjektLederKnap = new JButton("Set Projekt Leder");
	private JTextField OpretProjektFelt = new JTextField(20);
	private JTextField SletProjektFelt = new JTextField(20);
	private JTextField SetProjektLederFelt = new JTextField(20);
	
	//Layout kontrol til fane 1
	JPanel opretProjekt = new JPanel();
	JPanel sletProjekt = new JPanel();
	JPanel setLeder = new JPanel();
	
	
	//private Component tabbedPane;

	 public MainGUI(ProjectPlanner planner) {
		 //super(new GridLayout(1, 1));
		//Create and set up the window.
	     
	    
	        
	       
	        //Skrifttype til knapper
	        Font font = OpretProjektKnap.getFont();
	        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+5);
	        OpretProjektKnap.setFont(boldFont);
	        SletProjektKnap.setFont(boldFont);
	        SetProjektLederKnap.setFont(boldFont);
	       
	        //Ikoner til tabs
	        ImageIcon icon = createImageIcon("images/test2.png");
	        
	      
	        //Tab #1     
	        JTabbedPane tabbedPane = new JTabbedPane();
	        JComponent panel1 = makePanel();
	        panel1.setLayout(new GridLayout(0,1));
	        
	        panel1.add(opretProjekt);
	        panel1.add(sletProjekt);
	        panel1.add(setLeder);
	        	      
	        tabbedPane.addTab("Projects", icon, panel1,"Manage Projects");
	        panel1.setPreferredSize(new Dimension(800, 600));
	        
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        
	        sletProjekt.setLayout(new FlowLayout());
	        opretProjekt.setLayout(new FlowLayout());
	        setLeder.setLayout(new FlowLayout());
	        //Opret projektknap og felter
	        OpretProjektKnap.setHorizontalTextPosition(SwingConstants.LEADING);
	        opretProjekt.add(OpretProjektKnap);
	        opretProjekt.add(OpretProjektFelt);
	        sletProjekt.add(SletProjektKnap);
	        sletProjekt.add(SletProjektFelt);
	        setLeder.add(SetProjektLederKnap);
	        setLeder.add(SetProjektLederFelt);
	        
	        SletProjektFelt.setText("Indsæt projektnummer");
	        SetProjektLederFelt.setText("Indsæt Projektnummer");
	       
	        SetProjektLederFelt.addFocusListener(new FocusListener() {


	        	public void focusGained(FocusEvent e) {
	        	    SetProjektLederFelt.setText(""); 
	        	}	
	        	public void focusLost(FocusEvent e) {

	        	}

	        	});   
	        
	        SletProjektFelt.addFocusListener(new FocusListener() {


	        	public void focusGained(FocusEvent e) {
	        	    SletProjektFelt.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}

	        	}); 
	       
	       
	        //Funktionalitet Tab#1
	        OpretProjektKnap.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            		Project p = planner.createProject();
	            		//System.out.println(p.getProjectNumber());
	            		OpretProjektFelt.setText("Projekt nr:" + p.getProjectNumber() + " oprettet.");
	            }
	        });
	        
	        SletProjektKnap.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if (planner.getState().projectExistCheck(SletProjektFelt.getText())){
	            		for(Project p: planner.getState().getProjects()){
	            			if (p.getProjectNumber().toString().equals(SletProjektFelt.getText())){
	            			String temp = SletProjektFelt.getText();
	            			planner.getState().removeProject(p);
	            			SletProjektFelt.setText("Project " + temp + " removed.");
	            			
	            			}
	            		}
	            	} else {
	            		SletProjektFelt.setText("Projektnummer eksisterer ikke!");
	            	}
	            
	            	
	            }
	        });
	        
	        
	        
	        SetProjektLederKnap.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            //	if (planner.getState().projectExistCheck(SletProjektFelt.getText())){	
	            //		for(Project p: planner.getState().getProjects()){
	            //		
	            //			if (p.getProjectNumber().toString().equals(SetProjektLederFelt.getText())){
	            //			p.setLeader(planner.getUser());
	            //			SetProjektLederFelt.setText(p.getLeader() + " tilføjet som leder");
	            //				if (p.getLeader().toString().equals(planner.getUser())){
	            //				System.out.println("Succes adding leader");
	            //				}
	            //			}
	            //		}
	            //	} else {
	            //		SetProjektLederFelt.setText("Projektnummer eksisterer ikke!");
	            //	}
	            	String inputValue = JOptionPane.showInputDialog("Please input a Project Number:");
	            	
	            	if (planner.getState().projectExistCheck(inputValue)){	
	    	            		for(Project p: planner.getState().getProjects()){
	    	            		
	    	            			if (p.getProjectNumber().toString().equals(inputValue)){
	    	            			p.setLeader(planner.getUser());
	    	            			SetProjektLederFelt.setText(p.getLeader() + " tilføjet som leder");
	    	            				if (p.getLeader().toString().equals(planner.getUser())){
	    	            				System.out.println("Succes adding leader");
	    	            				}
	    	            			}
	    	            		}
	    	            	}
	            	
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
	        
	        

	        
	        
	        JFrame frame = new JFrame("Project Planner");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.add(tabbedPane, BorderLayout.CENTER);
	        frame.setTitle("Project Planner V1.0");
			frame.setSize(800, 600);
			frame.add(tabbedPane, BorderLayout.CENTER);
	        //Add content to the window.
	        
	        
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
	    
	    
	
	    
	    public static void main(ProjectPlanner planner){
			MainGUI GUI = new MainGUI(planner);
			GUI.addWindowListener(new WindowListener(){
				@Override public void windowActivated(WindowEvent arg0) {}
				@Override
				public void windowClosed(WindowEvent arg0) {
					//Main.exit(state);
				}
				@Override public void windowClosing(WindowEvent arg0) {}
				@Override public void windowDeactivated(WindowEvent arg0) {}
				@Override public void windowDeiconified(WindowEvent arg0) {}
				@Override public void windowIconified(WindowEvent arg0) {}
				@Override public void windowOpened(WindowEvent arg0) {}
			});
				
		}

		private void addWindowListener(WindowListener windowListener) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}