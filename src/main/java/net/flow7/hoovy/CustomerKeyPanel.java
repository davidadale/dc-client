package net.flow7.hoovy;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class CustomerKeyPanel extends JPanel{
 
    private JTextField field;
 
    public CustomerKeyPanel(){
         setLayout(new java.awt.BorderLayout());
         JPanel jPanel1 = new JPanel();
         jPanel1.add( new Label("Enter the client code and click next") );
         field = new JTextField( 40);
         jPanel1.add( field );
         add(jPanel1, java.awt.BorderLayout.CENTER);
    }
    
    
    public String getClientKey(){
        return field.getText();
    }
    public void addClientKeyListener( DocumentListener l ){
        field.getDocument().addDocumentListener( l );
    }

}