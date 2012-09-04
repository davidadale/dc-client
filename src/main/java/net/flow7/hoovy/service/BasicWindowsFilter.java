/*
 * Copyright 2012 daviddale.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.flow7.hoovy.service;

import java.io.File;
import java.io.FileFilter;
import java.util.*;


/**
 *
 * @author daviddale
 */
public class BasicWindowsFilter implements FileFilter{

    static final Map<String,String> audio = new HashMap<String,String>();
    static final Map<String,String> documents = new HashMap<String,String>();
    static final Map<String,String> images = new HashMap<String,String>();
    static final List<String> ignore = new LinkedList<String>();
    
    
    static{
        audio.put(".aac",  "Apple audio format");        
        audio.put(".aif",  "Audio Interchange File Format");
        audio.put(".iff",  "Interchange File Format");
        audio.put(".m3u",  "Media Playlist File");
        audio.put(".m4a",  "MPEG-4 Audio File");
        audio.put(".mid",  "MIDI File");
        audio.put(".mp3",  "MP3 Audio File");
        audio.put(".mpa",  "MPEG-2 Audio File");
        audio.put(".ra",   "Real Audio File");
        audio.put(".wav",  "WAVE Audio File");
        audio.put(".wma",  "Windows Media Audio File");
        documents.put(".doc",	"Microsoft Word Document");
        documents.put(".docx",	"Microsoft Word Open XML Document");
        documents.put(".odt",	"OpenDocument Text Document");
        documents.put(".pages",	"Pages Document");
        documents.put(".rtf",	"Rich Text Format File");
        documents.put(".tex",	"LaTeX Source Document");
        documents.put(".txt",	"Plain Text File");
        documents.put(".wpd",	"WordPerfect Document");
        documents.put(".wps",	"Microsoft Works Word Processor Document");
        documents.put(".csv",	"Comma Separated Values File");
        documents.put(".dat",	"Data File");
        documents.put(".efx",	"eFax Document");
        documents.put(".epub",	"Open eBook File");
        documents.put(".ibooks","Multi-Touch iBook");
        documents.put(".key",	"Keynote Presentation");
        documents.put(".pps",	"PowerPoint Slide Show");
        documents.put(".ppt",	"PowerPoint Presentation");
        documents.put(".pptx",	"PowerPoint Open XML Presentation");
        documents.put(".tar",	"Consolidated Unix File Archive");
        documents.put(".tax2010","TurboTax 2010 Tax Return");
        documents.put(".tax2011","TurboTax 2010 Tax Return");        
        documents.put(".vcf",	"vCard File");
        documents.put(".xml",	"XML File");
        documents.put(".indd",	"Adobe InDesign Document");
        documents.put(".pct",	"Picture File");
        documents.put(".pdf",	"Portable Document Format File");        
        documents.put(".xlr",	"Works Spreadsheet");
        documents.put(".xls",	"Excel Spreadsheet");
        documents.put(".xlsx",	"Microsoft Excel Open XML Spreadsheet");        
        images.put(".bmp",	"Bitmap Image File");
        images.put(".dds",	"DirectDraw Surface");
        images.put(".dng",	"Digital Negative Image File");
        images.put(".gif",	"Graphical Interchange Format File");
        images.put(".jpg",	"JPEG Image");
        images.put(".png",	"Portable Network Graphic");
        images.put(".psd",	"Adobe Photoshop Document");
        images.put(".pspimage",	"PaintShop Pro Image");
        images.put(".tga",	"Targa Graphic");
        images.put(".thm",	"Thumbnail Image File");
        images.put(".tif",	"Tagged Image File");
        images.put(".yuv",	"YUV Encoded Image File");
        images.put(".ai",	"Adobe Illustrator File");
        images.put(".eps",	"Encapsulated PostScript File");
        images.put(".ps",	"PostScript File");
        images.put(".svg",	"Scalable Vector Graphics File");
        ignore.add("Program Files");
        ignore.add("WINDOWS");
        ignore.add("Temporary Internet");
        ignore.add("System Volume Information");
        ignore.add("Application Data");
        
        
    }
    
    @Override
    public boolean accept(File file) {
        
        if( ignoreFile( file ) ){
            return false;
        }
        
        return isDocument( file ) ||
                isAudio( file ) ||
                isImage( file ) || 
                file.isDirectory();
        
    }
    
    protected boolean ignoreFile( File file ){
        for(String path: ignore ){
            if( file.getAbsolutePath().contains( path ) ){
                return true;
            }
        }
        return false;
    }
    
    protected static String getExtension(File file){
        int index = file.getName().lastIndexOf(".");
        if( index > -1 ){
            return file.getName().substring(index);        
        }else{
            return "";
        }
    }
    
    public static boolean isDocument( File file ){
        return documents.containsKey( getExtension(file) );
    }
    
    public static boolean isAudio( File file ){
        return audio.containsKey( getExtension(file) );
    }
    
    public static boolean isImage( File file ){
        return images.containsKey( getExtension( file ) );
    }
    
}
