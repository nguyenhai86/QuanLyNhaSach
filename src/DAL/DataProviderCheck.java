package DAL;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Nhu Y
 */
public class DataProviderCheck {
    private String Sever, database, userID, passWord;
    private Connection connection;

    public DataProviderCheck(String Sever, String userID, String passWord,String database) {
        this.Sever = Sever;
        this.database = database;
        this.userID = userID;
        this.passWord = passWord;
    }

    public String getSever() {
        return Sever;
    }

    public void setSever(String Sever) {
        this.Sever = Sever;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public String getConnectionURL(){
        String connectionURL = null;
        try{
            if (userID == null && passWord == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connectionURL="jdbc:sqlserver://" + Sever + ":1433" + ";databaseName="+ database + ";integratedSecurity=true";
            }
            else{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connectionURL="jdbc:sqlserver://" + Sever + ":1433" + ";databaseName=" + database +";user="+userID+";password="+passWord;
            }
            return connectionURL;
        }catch(Exception e){
            e.printStackTrace();
        }
        return connectionURL;
    }
    
    public void Open(){
        String connectionURL = null;
        try{
            if (userID == null && passWord == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connectionURL="jdbc:sqlserver://" + Sever + ":1433" + ";databaseName="+ database + ";integratedSecurity=true";
            }
            else{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connectionURL="jdbc:sqlserver://" + Sever + ":1433" + ";databaseName=" + database +";user="+userID+";password="+passWord;
            }
            connection = DriverManager.getConnection(connectionURL);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    //Moi khi dung thi dong connect
    public void Close(){
        try{
            this.connection.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    //Dung cho nhung cau query binh thuong
    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try{
            Statement sm = connection.createStatement();
            rs = sm.executeQuery(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return rs;
    }
}