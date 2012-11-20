package net.flow7.hoovy;

import java.awt.*;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class DriveSelectionPanel extends JPanel{
    
    private JLabel welcomeTitle;
    private JPanel contentPanel;
    
    private JLabel iconLabel;
    private ImageIcon icon;    
    
    JFileChooser fc;
    JTextField typeInPath;
    
    public DriveSelectionPanel(){

        // create the central part of the content on page
        //contentPanel = getContentPanel();
        //contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        
        // get the icon label and icon
        //iconLabel = new JLabel();
        //icon = getImageIcon();
        
        // set border layout for this JPanel
        //setLayout(new java.awt.BorderLayout());
        //if (icon != null) // if there is an icon file set it on the icon label
          ///  iconLabel.setIcon(icon);

        // decorate the icon label and add it to the screen in WEST position
        //iconLabel.setBorder( new EtchedBorder(EtchedBorder.RAISED) );
        //add(iconLabel, BorderLayout.WEST);
        
        // hmm....
        ///JPanel secondaryPanel = new JPanel();
        //secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        //add(secondaryPanel, BorderLayout.CENTER);


        //fc = new JFileChooser();
        //fc.setControlButtonsAreShown(false);
        //fc.setFileHidingEnabled(true);
        //fc.setFileFilter( new FileFilter(){
        //   public boolean accept(File f){ return f.isDirectory(); }
        //   public String getDescription(){ return "File Location";} 
        //});
        
        JPanel jPanel1 = new JPanel();
        jPanel1.add(new Label("Enter path to drive:"));
        
        typeInPath = new JTextField( 40);
        jPanel1.add( typeInPath );
        
        add( jPanel1 );
        
        //Create a file chooser
  //      fc = new JFileChooser();
    }
    
    
    public String getSelectedPath(){
        try{
            return null;
        }catch(Exception e){
            return null;
        }
    }
    
    protected JPanel getContentPanel(){
        JPanel contentPanel1 = new JPanel();
        JPanel jPanel1 = new JPanel();
        
        // create a container for welcome message
        welcomeTitle = new JLabel();
        contentPanel1.setLayout(new java.awt.BorderLayout());
        
        // set the font and the welcome message on this screen
        welcomeTitle.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 14));
        welcomeTitle.setText("Welcome to the Hoovy Drive Cleaner!");

        // add welcome message to the top of this component
        contentPanel1.add(welcomeTitle, java.awt.BorderLayout.NORTH);
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.add( new Label() );
        jPanel1.add( new Label("Enter the client code and click next") );
        //fc = new JFileChooser();
        //jPanel1.add( fc );

        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        //contentPanel1.getPreferredSize().setSize(400,400);
        return contentPanel1;
        
        
        
    }
    
    private ImageIcon getImageIcon() {
        return new ImageIcon((URL)getResource("clouds.jpg"));
    }
    
    private Object getResource(String key) {

        URL url = null;
        String name = key;

        if (name != null) {

            try {
                Class c = Class.forName("com.nexes.wizard.Wizard");
                url = c.getResource( name );
            } catch (ClassNotFoundException cnfe) {
                System.err.println("Unable to find Main class");
            }
            return url;
        } else
            return null;

    }
        
    
    
}