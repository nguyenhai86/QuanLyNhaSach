/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;


import DAL.DataProvider;
import DTO.DateBox;
import DTO.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author nguye
 */
public class BKhachHang {
    
    private static BKhachHang instance;
    public static BKhachHang getInstance() {
        if (instance == null) {
            instance = new BKhachHang();
        }
        return instance;
    }
    private static void setInstance(BKhachHang instance) {
        BKhachHang.instance = instance;
    }
    private BKhachHang(){}
    
    public ArrayList<KhachHang> getKhachHangs() throws SQLException{
        ArrayList<KhachHang> khachHangs = null;
        String sql = "EXEC P_GetAllKhachHang";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);

        khachHangs = new ArrayList<KhachHang>();
        while(rs.next())
        {
            KhachHang khachhang = new KhachHang();
            khachhang.setMaKhachHang(rs.getString("MaKhachHang").trim());
            khachhang.setTenKhachHang(rs.getString("TenKhachHang").trim());
            khachhang.setDienThoai(rs.getString("DienThoai").trim());
            khachhang.setNhomKhachHang(rs.getString("TenNhom").trim());
            khachhang.setLanCuoiMuaHang(DateBox.getDate(rs.getString("LanCuoiMuaHang")));
            khachhang.setTongTien(rs.getDouble("TongTien"));
            khachhang.setTinhTrang(rs.getBoolean("TinhTrang"));   
            khachHangs.add(khachhang);
        }
        DataProvider.getInstance().Close();
        return khachHangs;
    }
    
    public ArrayList<KhachHang> searchKH(String value) throws SQLException{
        ArrayList<KhachHang> khachHangs = null;
        String sql = "EXEC dbo.P_SeachKH @MaKH = ?, @TenKH = ?, @DienThoai = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{value,value,value});
        khachHangs = new ArrayList<KhachHang>();
        while(rs.next()){
            KhachHang khachhang = new KhachHang();
            khachhang.setMaKhachHang(rs.getString("MaKhachHang").trim());
            khachhang.setTenKhachHang(rs.getString("TenKhachHang").trim());
            khachhang.setDienThoai(rs.getString("DienThoai").trim());
            khachhang.setNhomKhachHang(rs.getString("TenNhom").trim());
            khachhang.setLanCuoiMuaHang(DateBox.getDate(rs.getString("LanCuoiMuaHang")));
            khachhang.setTongTien(rs.getDouble("TongTien"));
            khachhang.setTinhTrang(rs.getBoolean("TinhTrang"));   
            khachHangs.add(khachhang);
        }
        DataProvider.getInstance().Close();
        return khachHangs;
    }
    
    public boolean IsExitstMaKH(String maKH) throws SQLException{
        boolean result = false;
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.KHACHHANG WHERE MaKhachHang = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maKH});
        if (rs.next())
            result = true;
        DataProvider.getInstance().Close();
        return result;
    }
    public KhachHang getKhachHangBySDT (String soDienThoai) throws SQLException{
        KhachHang khachhang = null;
        String sql = "EXEC dbo.P_GetKhachHangBySDT @DienThoai = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{soDienThoai});

        while(rs.next())
        {
            khachhang = new KhachHang();
            khachhang.setMaKhachHang(rs.getString("MaKhachHang").trim());
            khachhang.setTenKhachHang(rs.getString("TenKhachHang").trim());
            khachhang.setDienThoai(rs.getString("DienThoai").trim());
            khachhang.setNhomKhachHang(rs.getString("TenNhom").trim());
            khachhang.setLanCuoiMuaHang(DateBox.getDate(rs.getString("LanCuoiMuaHang")));
            khachhang.setTongTien(rs.getDouble("TongTien"));
            khachhang.setTinhTrang(rs.getBoolean("TinhTrang"));   
        }
        DataProvider.getInstance().Close();
        return khachhang;
    }
    public KhachHang getKhachHangByMa(String maKhachHang) throws SQLException{
        KhachHang khachhang = null;
        String sql = "EXEC dbo.P_GetKhachHangByMa @MaKH = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maKhachHang});

        while(rs.next())
        {
            khachhang = new KhachHang();
            khachhang.setMaKhachHang(rs.getString("MaKhachHang").trim());
            khachhang.setTenKhachHang(rs.getString("TenKhachHang").trim());
            khachhang.setDienThoai(rs.getString("DienThoai").trim());
            khachhang.setNhomKhachHang(rs.getString("TenNhom").trim());
            khachhang.setLanCuoiMuaHang(DateBox.getDate(rs.getString("LanCuoiMuaHang")));
            khachhang.setTongTien(rs.getDouble("TongTien"));
            khachhang.setTinhTrang(rs.getBoolean("TinhTrang"));   
        }
        DataProvider.getInstance().Close();
        return khachhang;
    }
    public boolean ThemKhachHang(KhachHang khachHang) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_ThemKhachHang @TenKhachHang = ?, @DienThoai = ?, @TenNhom = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{khachHang.getTenKhachHang(), khachHang.getDienThoai(), khachHang.getNhomKhachHang(), khachHang.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
    public boolean UpdateKhachHang(KhachHang khachHang) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_UpdateKhachHang @MaKhachHang = ?, @TenKhachHang = ?, @DienThoai = ?, @TenNhom = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{khachHang.getMaKhachHang(),khachHang.getTenKhachHang(), khachHang.getDienThoai(), khachHang.getNhomKhachHang(), khachHang.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
}
