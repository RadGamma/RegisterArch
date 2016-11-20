package baseencoder;
 /*
 * www.icodejava.com
 *
 * This class can be used to Base64 encode or Base64 decode a file. It uses apache commons codec library.
 * The library used for testing the functionality was commons-codec-1.4.jar
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import java.awt.Desktop;

import org.apache.commons.io.FileUtils; 
 
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.codec.binary.Base64;



public class Encode {
 
    private static final boolean IS_CHUNKED = true;
 
    public static void main(String args[]) throws Exception { 
 

    	String localDirectory = "C:/Users/Frazee/Desktop/BankStatements/";  

        Iterator it = FileUtils.iterateFiles(new File(localDirectory),
        			  null, true);
    	while (it.hasNext()) {
    		File f = (File) it.next();        	       
    	    String filename = f.getName();
    	    String filename2 = FilenameUtils.removeExtension(filename);
        	System.out.println("File " + f + " File Name " + f.getName() + " File Name No EXT " + filename2 );
     	
            /* Encode a file and write the encoded output to a text file. */
            //encode(localDirectory+filename, "//FLETCHER/share/Files/BankStmts/" + filename2 + ".txt", IS_CHUNKED);
       
            /* Decode a file and write the decoded file to file system */
              // decode("//FLETCHER/share/Files/01.10.15.02.09.15.txt", "C:/Users/Frazee/Desktop/BankStatements/test.pdf");
      	
    	}
    	
    	 decode("//FLETCHER/share/Files/BankStmts/01.10.15.02.09.15.txt", "C:/Users/Frazee/Desktop/BankStatements/test.pdf");
    	 
    	 
    	 
    	 // opens the pdf file frp, the app 
    	 if (Desktop.isDesktopSupported()) {
    		    try {
    		        File myFile = new File("C:/Users/Frazee/Desktop/BankStatements/test.pdf");
    		        Desktop.getDesktop().open(myFile);
    		    } catch (IOException ex) {
    		        // no application registered for PDFs
    		    }
    	 }

    }
 
    /**
     * This method converts the content of a source file into Base64 encoded data and saves that to a target file.
     * If isChunked parameter is set to true, there is a hard wrap of the output  encoded text.
     */
    private static void encode(String sourceFile, String targetFile, boolean isChunked) throws Exception {
 
        byte[] base64EncodedData = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), isChunked);
 
        writeByteArraysToFile(targetFile, base64EncodedData);
    }
 
    public static void decode(String sourceFile, String targetFile) throws Exception {
 
        byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));
 
        writeByteArraysToFile(targetFile, decodedBytes);
    }
 
    /**
     * This method loads a file from file system and returns the byte array of the content.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static byte[] loadFileAsBytesArray(String fileName) throws Exception {
 
        File file = new File(fileName);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
        return bytes;
 
    }
 
    /**
     * This method writes byte array content into a file.
     *
     * @param fileName
     * @param content
     * @throws IOException
     */
    public static void writeByteArraysToFile(String fileName, byte[] content) throws IOException {
 
        File file = new File(fileName);
        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
        writer.write(content);
        writer.flush();
        writer.close();
 
    }
}


