package net.flow7.hoovy;

import java.io.File;
import javax.swing.SwingUtilities;
import net.flow7.hoovy.service.FileListener;

public class ProcessingDescriptor extends DriveCleanerPanelDescriptor implements FileListener {

    public static final String IDENTIFIER = "PROCESSING";
    
    ProcessingPanel panel = new ProcessingPanel();

    public ProcessingDescriptor() {
        setPanelDescriptorIdentifier(IDENTIFIER);
        setPanelComponent(panel);
        SwingUtilities.updateComponentTreeUI(panel);        
    }

    @Override
    public Object getNextPanelDescriptor() {
        return CopyFilesDescriptor.IDENTIFIER;
    }


    @Override
    public Object getBackPanelDescriptor() {
        return DriveSelectionDescriptor.IDENTIFIER;
    }

    public void fileFound(File file) {
        panel.updateProgressLabel(file.toString());

    }

    public void fileCopied(File file) {
        // TODO Auto-generated method stub
    }

    public void aboutToDisplayPanel() {
        client.addFileListener(this);
        client.createClientFolder();
        client.startCopy();
    }
}