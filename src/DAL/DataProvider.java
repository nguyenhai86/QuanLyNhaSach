/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import DTO.DateBox;
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
public class DataProvider {
    //
    private static DataProvider instance;
    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }
    private static void setInstance(DataProvider instance) {
        DataProvider.instance = instance;
    }
    private DataProvider(){};
    
    private Connection connection;
    //Moi khi dung thi mo connect
    public void Open(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=QLNS;user=sa;password=123";
            //String connectionURL = GhiDocFile.getInstance().DocFile();
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
    
    //Dung cho nhung cau query binh thuong
    public int executeUpdate(String sql) throws SQLException{
        int n = -1;
        Statement sm = connection.createStatement();
        n = sm.executeUpdate(sql);
        
        return n;
    }
    
    public ResultSet executeQueryPrepareStatement(String sql, Object[] parameter) throws SQLException
    {
        PreparedStatement preStatement;
        ResultSet rs = null;

        preStatement = connection.prepareStatement(sql);

        int soDauHoi = TinhSoDauHoi(sql);

        if (soDauHoi != 0 && parameter != null) {
            try{
                for (int i = 1; i <= soDauHoi; i++) {
                    if ((parameter[i-1]).getClass().getSimpleName().equals("String")) {
                        preStatement.setString(i,(String)parameter[i-1]);
                    }

                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Integer")) {
                        preStatement.setInt(i,(int)parameter[i-1]);
                    }

                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Boolean")) {
                        preStatement.setBoolean(i,(boolean)parameter[i-1]);
                    }
                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Double")) {
                        preStatement.setDouble(i,(Double)parameter[i-1]);
                    }
                
                }   
            }catch(Exception e){
                System.out.println("Sua lop dataprovider - Kieu du lieu chua duoc chuyen doi");
            }
        }
        rs = preStatement.executeQuery();
        return rs;
    }
    
    public ResultSet executeQueryFilter(String sql,boolean[] bit, Object[] parameter) throws SQLException{
        PreparedStatement preStatement;
        ResultSet rs = null;
            preStatement = connection.prepareStatement(sql);

            int soDauHoi = TinhSoDauHoi(sql);

            if (soDauHoi != 0 && parameter != null) {
                int count = 1;
                try{
                    for (int i = 1; i <= parameter.length; i++) {
                        if ((parameter[i-1]) != null && (parameter[i-1]).getClass().getSimpleName().equals("String") && ((boolean)bit[i-1]) == true ) {
                            preStatement.setString(count,(String)parameter[i-1]);
                            count++;
                        }
                        else if ((parameter[i-1]) != null && (parameter[i-1]).getClass().getSimpleName().equals("Integer") && ((boolean)bit[i-1]) == true ) {
                            preStatement.setInt(count,(int)parameter[i-1]);
                            count++;
                        }
                        else if ((parameter[i-1]) != null && (parameter[i-1]).getClass().getSimpleName().equals("Boolean") && ((boolean)bit[i-1]) == true ) {
                            preStatement.setBoolean(count,(boolean)parameter[i-1]);
                            count++;
                        }
                        else if ((parameter[i-1]) != null && (parameter[i-1]).getClass().getSimpleName().equals("Date") && ((boolean)bit[i-1]) == true ) {
                            preStatement.setString(count,DateBox.ConvertToString((Date)parameter[i-1]));
                            count++;
                        }
                        else if ((parameter[i-1]) != null && (parameter[i-1]).getClass().getSimpleName().equals("Double") && ((boolean)bit[i-1]) == true ) {
                            preStatement.setDouble(count,(Double)parameter[i-1]);
                            count++;
                        }
                    }
                }catch(Exception e){
                  e.printStackTrace();
                }
            }
        rs = preStatement.executeQuery();
        return rs;
    }
    
    public int executeUpdatePrepareStatement(String sql, Object[] parameter) throws SQLException
    {
        PreparedStatement preStatement;
        int result = 0;
            preStatement = connection.prepareStatement(sql);
        
            int soDauHoi = TinhSoDauHoi(sql);

            if (soDauHoi != 0 && parameter != null) {
            try{
                for (int i = 1; i <= soDauHoi; i++) {
                    if ((parameter[i-1]).getClass().getSimpleName().equals("String")) {
                        preStatement.setString(i,(String)parameter[i-1]);
                    }

                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Integer")) {
                        preStatement.setInt(i,(int)parameter[i-1]);
                    }

                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Boolean")) {
                        preStatement.setBoolean(i,(boolean)parameter[i-1]);
                    }
                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Double")) {
                        preStatement.setDouble(i,(Double)parameter[i-1]);
                    }
                    else if ((parameter[i-1]).getClass().getSimpleName().equals("Double")) {
                        preStatement.setDouble(i,(Double)parameter[i-1]);
                    }
                }   
            }catch(Exception e){
                System.out.println("Sua lop dataprovider - Kieu du lieu chua duoc chuyen doi");
            }
        }

        result = preStatement.executeUpdate();
        return result;
    }

    private int TinhSoDauHoi(String sql)
    {
        int dem = 0;
        int i = 0;
        while(true){
            if (sql.indexOf("?", i) != -1)
            {
                i = sql.indexOf("?", i) + 1;
                dem++;
            }
            else
                break;
        }
        return dem;
    }
}
