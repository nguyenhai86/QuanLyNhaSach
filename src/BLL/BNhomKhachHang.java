/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.NhomKhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class BNhomKhachHang {
    
    private static BNhomKhachHang instance;
    public static BNhomKhachHang getInstance() {
        if (instance == null) {
            instance= new BNhomKhachHang();
        }
        return instance;
    }
    private static void setInstance(BNhomKhachHang instance) {
        BNhomKhachHang.instance = instance;
    }
    
    public ArrayList<NhomKhachHang> getNhomKhachHangs() throws SQLException
    {
        ArrayList<NhomKhachHang> nhomKhachHangs = null;
        String sql = "P_GetAllNhomKhachHang";
        
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);

        nhomKhachHangs = new ArrayList<NhomKhachHang>();
        while(rs.next())
        {
            NhomKhachHang nhomKhachHang = new NhomKhachHang();
            nhomKhachHang.setMaNhom(rs.getString("MaNhomKhachHang").trim());
            nhomKhachHang.setTenNhom(rs.getString("TenNhom").trim());
            nhomKhachHang.setGhiChu(rs.getString("GhiChu"));
            nhomKhachHangs.add(nhomKhachHang);
        }
        DataProvider.getInstance().Close();
        return nhomKhachHangs;
    }
    public ArrayList<NhomKhachHang> searchNKH(String value) throws SQLException{
        ArrayList<NhomKhachHang> nhomKhachHangs = null;
        String sql = "SELECT * FROM NHOMKHACHHANG WHERE MaNhomKhachHang LIKE ? OR TenNhom LIKE ? ORDER BY CONVERT(INT,SUBSTRING(NHOMKHACHHANG.MaNhomKhachHang,4,100)) DESC";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{"%"+value+"%","%"+value+"%"});
        nhomKhachHangs = new ArrayList<NhomKhachHang>();
        while(rs.next())
        {
            NhomKhachHang nhomKhachHang = new NhomKhachHang();
            nhomKhachHang.setMaNhom(rs.getString("MaNhomKhachHang").trim());
            nhomKhachHang.setTenNhom(rs.getString("TenNhom").trim());
            nhomKhachHang.setGhiChu(rs.getString("GhiChu"));
            nhomKhachHangs.add(nhomKhachHang);
        }
        DataProvider.getInstance().Close();
        return nhomKhachHangs;
    }
    public boolean ThemNhomKhachHang(NhomKhachHang  nkh) throws SQLException{
        boolean result = false;
        DataProvider.getInstance().Open();
        String sql = "EXEC dbo.P_ThemNhomKhachHang @TenNhom = ?, @GhiChu = ?";
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nkh.getTenNhom(), nkh.getGhiChu()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean UpdateNhomKhachHang(NhomKhachHang  nkh) throws SQLException{
        boolean result = false;
        DataProvider.getInstance().Open();
        String sql = "EXEC dbo.P_UpdateNhomKhachHang @MaNhomKhachHang = ?, @TenNhom = ?, @GhiChu = ?";
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nkh.getMaNhom(),nkh.getTenNhom(), nkh.getGhiChu()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean IsExistNKH(String tenNhomKhachHang) throws SQLException{
        boolean result = false;
        ArrayList<NhomKhachHang> nhomKhachHangs = null;
        String sql = "SELECT * FROM NHOMKHACHHANG WHERE TenNhom = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{tenNhomKhachHang});
        nhomKhachHangs = new ArrayList<NhomKhachHang>();
        if (rs.next())
            result = true;
        return result;
    }
}
