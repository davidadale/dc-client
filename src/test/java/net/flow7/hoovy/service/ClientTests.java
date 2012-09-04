package net.flow7.hoovy.service;


import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTests{
    
    public static final String DIRECTORY_NAME = "drive_cleaners_test";
    public static final String MOUNT_DIRECTORY = "junit_test_mount_point";
    
    @Test
    public void test_client_folder_creation() throws Exception{

        Client client = new Client( getClientDirectory() );
        assertFalse( (new File( getClientDirectory() )).exists() );
        
        client.createClientFolder();
        assertTrue( (new File( getClientDirectory() ) ).exists() );

        Thread.sleep(2000);
    }

    @Test
    public void test_recursive_copy_from_mount_to_client_directory() throws Exception{
        
        Client client = new Client( getClientDirectory() );
        client.createClientFolder();
        client.setMountPoint( getMountPoint() );
        client.startCopy(); 
        
    }
    
    @Test
    public void test_file_listener_events() throws Exception{
        Client client = new Client( getClientDirectory() );
        
        client.addFileListener(new FileListener(){
            public void fileFound( File file ){
                System.out.println("File found " + file );
            }
            public void fileCopied( File file ){
                System.out.println("File copied " + file );
            }
        });
        
        client.createClientFolder();
        client.setMountPoint( getMountPoint() );
        client.startCopy(); // no reason to fire off seperate thread in unit test.
        //client.run();
                
        Thread.sleep(2000);
    }

    protected String getClientDirectory(){
        return System.getProperty("user.home") +"/"+DIRECTORY_NAME;
    }
    
    protected String getMountPoint() throws Exception{
        return ClassLoader.getSystemClassLoader().getResource( MOUNT_DIRECTORY ).toString();
    }
    
    @Before
    public void setup(){
        File file = new File( getClientDirectory() );
        
        
        if( file.exists() ){
            if( file.isDirectory() ){
                File[] files = file.listFiles();
                for(int i=0;i < files.length; i++ ){
                    files[i].delete();
                }
            }
            file.delete();
        }
    }
    

    @After
    public void teardown(){
        
    }    
    
    
}