/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.passwordstore;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;




/**
 *
 * @author 30022145
 */
public class sqlitefncs {
    
    sqlitefncs(){
    Ef.checkAndCreateDirs("Logs");
    Ef.checkAndCreateDirs("Dbase");
    createDatabase();
    CreateNewTables();
    
    }
    
    private static String directory=System.getProperty("user.dir");
    private final String DB=directory+File.separator+"Dbase"+File.separator+"ps.db";
    private final String url = "jdbc:sqlite:" + DB;
    private LogWriter Lw = new LogWriter();
    private extraFunctions Ef = new extraFunctions();
     Boolean tester= false;
    
     
    public String getUrl(){
    String url= this.url;
    return url;
    } 
    
    void createDatabase(){
       File file=new File(DB);
        if (!file.exists()){
            try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection(url);
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    Lw.logWriter("Db created");
                    tester= true;
                }
            } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            }
          
        }
    }
    
    void CreateNewTables(){
            Connection c = null; 
            Statement stmt = null;
                try { 
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(url);
                Lw.logWriter("Opened database successfully");
                stmt = c.createStatement(); 
                String sql = "CREATE TABLE IF NOT EXISTS Login_Password (user varchar(30), Pass varchar(30)); CREATE TABLE IF NOT EXISTS pass_store (Application varchar(30), userid varchar(100), password varchar(100));";
               stmt.executeUpdate(sql); 
                if (tester){
                String sql2 = "Insert into Login_password values('admin','Password');";
                stmt.executeUpdate(sql2); 
                tester=false;
                }
                stmt.close();         
                c.close(); } 

                catch ( Exception e ) {

                System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
                Lw.logWriter("Error Table creation:" + e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0); } 

                Lw.logWriter("Table created successfully"); 

} 
    
    
    public String getLoginPassword(){
    String Pass = null;
    String Qry="select pass from Login_Password where user='admin';";
    Connection c = null;
    Statement stmt = null;
    
    try
    {
    Class.forName("org.sqlite.JDBC");
    c = DriverManager.getConnection(url);
    c.setAutoCommit(false);
 
    stmt = c.createStatement();
    ResultSet rs = stmt.executeQuery(Qry);
    
        while(rs.next()){
        Pass=rs.getString("Pass");
        }
        rs.close();
        stmt.close();
        c.close();
    }
    catch(Exception e){
    
    System.err.println( e.getClass().getName() + ": " + e.getMessage() ); 
    System.exit(0);
     }
           
    return Pass;
    }
    
    public void AddNewRecords(String AppName, String UserId, String Password){
                  
        Connection c =null;
        Statement stmt = null;
        String sql;
        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(url);
        stmt= c.createStatement();
        sql= "insert into pass_store values ('" + AppName + "','" + UserId + "','"+ Password +"');";
        stmt.execute(sql);
        stmt.close();
        c.close();
              
        Lw.logWriter("Application added : " + Ef.getDate() );
        }
        catch(Exception e){
        System.err.println(e.getClass().getName()+ ": " + e.getMessage());
        Lw.logWriter("Data Updation Error :" +e.getMessage());
        }
        
    }
    
    
    public void UpdateRecords(String AppName, String Password){
                  
        Connection c =null;
        Statement stmt = null;
        String sql;
        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(url);
        stmt= c.createStatement();
        sql= "update pass_store set password = '" + Password +"' where Application ='" + AppName +"';";
        stmt.execute(sql);
        stmt.close();
        c.close();
              
        Lw.logWriter("Password Uupdated : " + Ef.getDate() );
        }
        catch(Exception e){
        System.err.println(e.getClass().getName()+ ": " + e.getMessage());
        Lw.logWriter("Data Updation Error :" +e.getMessage());
        }
        
    }
    
    
     public String DeleteRecord(String AppName){
     
         Connection c = null;
         Statement stmt= null;
         String sql;
         try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection(url);
             stmt= c.createStatement();
             sql= "delete from pass_store where Application ='" + AppName +"';";
             stmt.execute(sql);
             stmt.close();
            c.close();
              
        Lw.logWriter("Application Deleted : " + Ef.getDate() );
         }
         catch (Exception e){
         Lw.logWriter("Error deleting records " + e.getMessage() );
         }
      return "Record Deleted" + AppName;
      
     }
     
     public void ChangeLoginPassword(String newPassword){
                  
        Connection c =null;
        Statement stmt = null;
        String sql;
        try{
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection(url);
        stmt= c.createStatement();
        sql= "update Login_Password set Pass = '" + newPassword +"' where user ='admin'";
        stmt.execute(sql);
        stmt.close();
        c.close();
              
        Lw.logWriter("Password Uupdated : " + Ef.getDate() );
        }
        catch(Exception e){
        System.err.println(e.getClass().getName()+ ": " + e.getMessage());
        Lw.logWriter("Data Updation Error :" +e.getMessage());
        }
        
    }
    
    }


