/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.DateBox;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
/**
 *
 * @author NguyenHai
 */
public class BQuanLy {
    private static BQuanLy instance;
    public static BQuanLy getInstance() {
        if (instance == null) {
            instance = new BQuanLy();
        }
        return instance;
    }
    private static void setInstance(BQuanLy instance) {
        BQuanLy.instance = instance;
    }
    private BQuanLy(){}
    
    public double TinhDoanhThu() throws SQLException{
        double result = 0;
        String sql = "SELECT dbo.F_TinhDoanhSo() AS 'DoanhSo'";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        while(rs.next()){
            result = rs.getDouble("DoanhSo");
        }
        DataProvider.getInstance().Close();
        return result ;
    }
    
       public double TinhDoanhThu(Date ngayBatDau, Date ngayKetThuc ) throws SQLException{
        double result = 0;
        String sql = "SET DATEFORMAT DMY SELECT dbo.F_TinhDoanhThuTheo(?,?) AS 'DoanhSo'";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{DateBox.ConvertToString(ngayBatDau), DateBox.ConvertToString(ngayKetThuc)});
        while(rs.next()){
            result = rs.getDouble("DoanhSo");
        }
        DataProvider.getInstance().Close();
        return result ;
    }
    public double TinhLai() throws SQLException{
        double result = 0;
        String sql = "SELECT dbo.F_TinhLai() AS 'Lai'";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        while(rs.next()){
            result = rs.getDouble("Lai");
        }
        DataProvider.getInstance().Close();
        return result ;
    }
    
}
