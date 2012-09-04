package net.flow7.hoovy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import net.flow7.hoovy.service.FileListener;

@SuppressWarnings("serial")
public class ProcessingPanel extends JPanel implements FileListener{
    
    JTextArea fileLabel;
    JScrollPane scrolling;
        
    int i = 0;
    public ProcessingPanel(){
        setLayout(new java.awt.BorderLayout());
        fileLabel = new JTextArea();
        fileLabel.setDocument(new PlainDocument() );
        scrolling = new JScrollPane(fileLabel);
        //scrolling.getHorizontalScrollBar().setVisible(false);
        //scrolling.getVerticalScrollBar().setVisible(false);
        scrolling.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
        scrolling.getHorizontalScrollBar().setPreferredSize (new Dimension(0,0));
        add(scrolling, BorderLayout.CENTER);        
    }
    
    
    public void updateProgressLabel(String label){
        //progressDescription.setText( label );
        //int count = fileLabel.getLineCount();
        //fileLabel.insert(  label, count==0?0:count
        //i++;
        fileLabel.append( label + "\n");
        int x = fileLabel.getLineCount() % 1000;
        
        if( x == 0){
            fileLabel.setText("");
        }
        
        //fileLabel.setText(i + "");
        scrolling.getVerticalScrollBar().setValue( scrolling.getVerticalScrollBar().getMaximum() );
    }
    
    public void setProgressText(String s) {
        //progressDescription.setText(s);
        
    }
    
    public void setProgressValue(int i) {
        //progressSent.setValue(i);
    }
    
   
    
    public void fileFound( File file ){
        
    }
    
    public void fileCopied( File file ){
        
    }
    
    
}