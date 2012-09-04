package net.flow7.hoovy;

import java.io.File;
import javax.swing.SwingUtilities;
import net.flow7.hoovy.service.FileListener;


public class CopyFilesDescriptor extends DriveCleanerPanelDescriptor implements FileListener {

    public static final String IDENTIFIER = "COPY_FILES";
    protected CopyFilesPanel panel;
    
    public CopyFilesDescriptor() {
        panel = new CopyFilesPanel();
        SwingUtilities.updateComponentTreeUI(panel);        
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent( panel );
    }

    public Object getNextPanelDescriptor() {
        return FINISH;
    }

    public Object getBackPanelDescriptor() {
        return ProcessingDescriptor.IDENTIFIER;
    }

    public void fileFound(File file) {
        // do nothing here
    }

    public void fileCopied(File file) {
     
        int value = panel.getProgressValue();
        value++;
        panel.setProgressValue( value );
        
    }

    public void aboutToDisplayPanel() {
        panel.setSomething( client.getTotalFileCount().intValue() );
        client.addFileListener(this);
        client.startCopy();
    }
}
