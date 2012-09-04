package net.flow7.hoovy;

import net.flow7.hoovy.service.*;
import com.nexes.wizard.*;

public abstract class DriveCleanerPanelDescriptor extends WizardPanelDescriptor{
    
    protected Client client = null;
    
    public void setClient( Client client ){
        this.client = client;
    }
    
    
}