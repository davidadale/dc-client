package net.flow7.hoovy.service;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class Client implements Runnable{
    
    Long totalFileCount = 0l;
    
    File clientDirectory;
    
    String mountPoint = null; 
    
    boolean analysisComplete = false;
    boolean copyComplete = false;
    
    List<File> files = new ArrayList<File>();
    List<FileListener> listeners = new ArrayList<FileListener>();
    
    public Client(){
        
    }
    
    public Client(String directory){
        this.clientDirectory = new File( directory );
    }
    
    public void setClientDirectory(String directory){
        this.clientDirectory = new File( directory );
    }
    
    public void addFileListener(FileListener listener){
        listeners.add( listener );
    }
    
    public void createClientFolder(){
        if( !clientDirectory.exists() ){
            clientDirectory.mkdirs();
        }
    }
    
    public void setMountPoint(String mount){
        this.mountPoint = mount;
    }
    
    
    public void run(){
        System.out.println("I am really running today");
        
        try{
            if( mountPoint == null ){
                throw new RuntimeException("No device mounted to scan and copy from.");
            }

            File mount = new File( new URI( "file://" + mountPoint ) );
            
            System.out.println("Mount point is " + mount.getPath() );
            
            if( !mount.isDirectory() ){ throw new RuntimeException("Mount point is not a directory."); }

            
            if( !analysisComplete ){
                
                analyzeDrive( mount );
                analysisComplete = true;
                
            }else if( analysisComplete && !copyComplete ){
                
                copyFiles( mount );
                copyComplete = true;
                
            }
            
            //copyFiles( mount );
            System.out.println("Finished stuff...." + totalFileCount );

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //copyComplete = true;            
        }
    }
    
    protected void fireFileFound(File file){
        for( FileListener l: listeners ){
            l.fileFound( file );
        }
    }
    
    protected void fireFileCopied(File file){
        for( FileListener l: listeners ){
            l.fileCopied( file );
        }
    }

    protected void analyzeDrive( File file ){
        if( file.isDirectory() ){
            
            File[] children = file.listFiles( new BasicWindowsFilter() );
            if( children==null){return;}
            
            for( int i=0; i<children.length; i++ ){
                analyzeDrive( children[i] );
            }
        }else{
            totalFileCount++;
            fireFileFound( file );
        }
    }
    
    protected void copyFiles(File file) throws Exception{
        
        if( file.isDirectory() ){
            File[] children = file.listFiles(new BasicWindowsFilter() );
            
            for(int i=0;i<children.length ;i++ ){
                copyFiles( children[i] );
            }

        }else{
            FileUtils.copyFile( file,  new File(clientDirectory, file.getName() ) );
            fireFileCopied( file );
        }
        
    }
    
    public boolean copyComplete(){
        return copyComplete;
    }
    
    public Long getTotalFileCount(){
        return totalFileCount;
    }
    
    
    public void startCopy(){
        Thread task = new Thread( this );
        task.start();
    }
    
    public  void pullData(){
        // start looping through the files on the disk storing data
        // off into an some structure.
    }
    
    public  void uploadClientData(){
        // upload to the cloud the data in a client folder
    }
    
    public  void sendCompletionEmail(){
        // send an email to the user
    }
    
    public boolean dummyMethod(){
        return true;
    }
}