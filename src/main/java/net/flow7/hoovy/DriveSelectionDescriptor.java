package net.flow7.hoovy;

import com.nexes.wizard.*;
import javax.swing.SwingUtilities;

public class DriveSelectionDescriptor extends DriveCleanerPanelDescriptor{
    public static final String IDENTIFIER = "DRIVE_SELECTOR";
    
    DriveSelectionPanel panel = new DriveSelectionPanel();
    
    public DriveSelectionDescriptor(){
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel);
        SwingUtilities.updateComponentTreeUI(panel);        
    }
    
    public String getPath(){
        return panel.fc.getSelectedFile().getAbsolutePath();
    }
    
    @Override
    public Object getNextPanelDescriptor() {
        return ProcessingDescriptor.IDENTIFIER;
    }
    
    @Override
    public Object getBackPanelDescriptor() {
        return CustomerKeyDescriptor.IDENTIFIER;
    }    
    @Override
    public void aboutToHidePanel(){
        client.setMountPoint( panel.typeInPath.getText() );        
    }
}