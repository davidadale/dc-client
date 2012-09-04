package net.flow7.hoovy;

import com.nexes.wizard.*;
import javax.swing.*;
import java.io.*;
import net.flow7.hoovy.service.*;


public class Main{
    
    static int registrationAttempts = 0;
    static Client client = null;

    /**
    * Start the client wizard to start cleaning the drive.
    */
    public static void main(String[] args){
        
        while( !registered()  ){
            if( tooManyTrys() ){ System.exit(0); }
            registrationAttempts++;
            String code = promptForRegistrationCode();
            if( code==null ){ System.exit(0); } // canceled.
            if ( verifyCode( code ) ){ break; }
        }
        
        try{
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }catch(Exception e){
            
        }
        
        startWizard();
        System.exit(0);        
    }
    
    /**
    * Check to see if this machine has been registered by a qualified 
    * technician.
    */
    protected static boolean registered(){
        try{
            // InputStream is =  this.getClass().getClassLoader().getResourceAsStream("");
            String path = System.getProperty("user.home");
            File code = new File( path + "/hoovy.reg");
            System.out.println("File location is " + code.getCanonicalPath() );
            
            if( code.exists() ){
                BufferedReader r =  new BufferedReader( new FileReader( code ) );
                if( verifyCode( r.readLine() ) ){
                    return true;
                }
            }
            return false;
        
        }catch(Exception e){
         return false;   
        }
    }    
    
    /**
    * Verify the technicians code that gets passed in.
    */
    protected static boolean verifyCode(String code){
        if( code!=null && code.length() > 0 ){
            return true;
        }
        
        JPanel parent = new JPanel();
        SwingUtilities.updateComponentTreeUI(parent);        
        JOptionPane.showMessageDialog( parent, "Invalid registration code!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    protected static String promptForRegistrationCode(){
        JPanel parent = new JPanel();
        SwingUtilities.updateComponentTreeUI(parent);        
        String s = (String) JOptionPane.showInputDialog( parent ,"Registration Code:");
        return s;
    }
    
    protected static boolean tooManyTrys(){
        return registrationAttempts >= 3;
    }
    

    
    protected static void startWizard(){
        
        client = new Client();
        
        Wizard wizard = new Wizard();
        wizard.getDialog().setTitle("Hard Drive Clean");
        
        // 1. Collect the customer key for the drive
        CustomerKeyDescriptor descriptor1 = new CustomerKeyDescriptor();
        descriptor1.setClient( client );
        wizard.registerWizardPanel(CustomerKeyDescriptor.IDENTIFIER, descriptor1);
        
        // 2. Allow the user to point to the client device
        DriveSelectionDescriptor descriptor2 = new DriveSelectionDescriptor();
        descriptor2.setClient( client );
        wizard.registerWizardPanel(DriveSelectionDescriptor.IDENTIFIER, descriptor2);

        // 3. Create the index file
        ProcessingDescriptor descriptor3 = new ProcessingDescriptor();
        descriptor3.setClient( client );
        wizard.registerWizardPanel(ProcessingDescriptor.IDENTIFIER, descriptor3);
        
        // 4. Create the client folder and copy files from drive
        CopyFilesDescriptor descriptor4 = new CopyFilesDescriptor();
        descriptor4.setClient( client );
        wizard.registerWizardPanel( CopyFilesDescriptor.IDENTIFIER, descriptor4 );
        
        wizard.setCurrentPanel( CustomerKeyDescriptor.IDENTIFIER );
        int ret = wizard.showModalDialog();        
    }
    
}