/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.passwordstore;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 30022145
 */
public class LogWriter {
    
    
    public void logWriter(String LogText) {
    extraFunctions EF = new extraFunctions();
        
        String directory=System.getProperty("user.dir");
    String savestr=directory+File.separator+"Logs"+File.separator+"ps.Log";    
try {
           
       
    File f = new File(savestr); 
    PrintWriter out = null;
        if ( f.exists()) {
            out = new PrintWriter(new FileOutputStream(new File(savestr), true));
            }
        else {
        out = new PrintWriter(savestr);
        }
    out.append(EF.getDate() + " " + LogText);
    out.println();
    out.close();
}
catch (FileNotFoundException ex){
System.out.println (ex.getMessage());
}
    
    }
    
}
