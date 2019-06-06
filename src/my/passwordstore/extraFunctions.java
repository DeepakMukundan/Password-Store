/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.passwordstore;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author 30022145
 */
public class extraFunctions {
    
    private LogWriter LW;
    
   public void checkAndCreateDirs(String directory_name){
       
     String directory =System.getProperty("user.dir");
         
        Path path = Paths.get(directory + File.separator + directory_name);
        
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println(path);
            } catch (IOException e) {
                //fail to create directory
                System.out.println(e.getMessage());
                //LW.logWriter("Error Directory Creation:" + e.getMessage());
            }
        }
   } 
   
   public String getDate(){
   String curDateTime;
   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
   Date now = new Date();
   curDateTime= formatter.format(now).toString();
   return curDateTime;    
   }
   
}
