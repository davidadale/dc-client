package net.flow7.hoovy;

import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class CustomerKeyDescriptor extends DriveCleanerPanelDescriptor implements DocumentListener{
    
    public static final String IDENTIFIER = "CUSTOMER_KEY";
    boolean error;
    int count = 0;
    
    CustomerKeyPanel panel;
    
    public CustomerKeyDescriptor(){
        panel = new CustomerKeyPanel();
        panel.addClientKeyListener( this );
        SwingUtilities.updateComponentTreeUI(panel);        
        setPanelDescriptorIdentifier( IDENTIFIER );
        setPanelComponent( panel );
    }
    
    public Object getNextPanelDescriptor() {
        if( error ){ return IDENTIFIER; }
        return DriveSelectionDescriptor.IDENTIFIER;
    }
    
    public Object getBackPanelDescriptor() {
        return null;
    }    
    
    public void changedUpdate(DocumentEvent e){
        enableButtons();
    }
    public void insertUpdate(DocumentEvent e){
        enableButtons();        
    }
    public void removeUpdate(DocumentEvent e){
        enableButtons();
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println("This should be called");
        enableButtons();
    }    
    
    private void enableButtons(){
        if( "12345".equals( panel.getClientKey() ) ){
            getWizard().setNextFinishButtonEnabled( true );
        }else{
            getWizard().setNextFinishButtonEnabled( false );
        }
    }
    public void aboutToDisplayPanel(){
        enableButtons();
    }
    
    public void aboutToHidePanel(){
        client.setClientDirectory( panel.getClientKey() );
    }    
    
    
}