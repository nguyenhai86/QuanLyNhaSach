
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.Sach;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */
public class BSach {
    private static BSach instance;
    public static BSach getInstance(){
        if(instance == null){
            instance = new BSach();
        }
        return instance;
    }
    private static void setInstance(BSach instance){
        BSach.instance = instance;
    }
    private BSach(){}
    
   public ArrayList<Sach> getSachs() throws SQLException{
    ArrayList<Sach> sachs = new ArrayList<Sach>();
    String sql = "EXEC P_GetAllSach";
    DataProvider.getInstance().Open();
    ResultSet rs = DataProvider.getInstance().executeQuery(sql);
    while(rs.next()){
        Sach sach = new Sach();
        sach.setMaSach(rs.getString("MaSach").trim());
        sach.setTenSach(rs.getString("TenSach").trim());
        sach.setTacGia(rs.getString("TacGia").trim());
        sach.setSoLuong(rs.getInt("SoLuong"));
        sach.setGiaNhap(rs.getDouble("GiaNhap"));
        sach.setGiaBan(rs.getDouble("GiaBan"));
        sach.setNhomHang(rs.getString("TenNhom").trim());
        sach.setTinhTrang(rs.getBoolean("TinhTrang"));
        sachs.add(sach);
    }
    DataProvider.getInstance().Close();
    return sachs;
    }
   public Sach getSachByMa(String maSach) throws SQLException{
       Sach sach = null;
       if (maSach != null) {
            
            String sql = "SELECT MaSach,TenSach,TacGia,GiaNhap,GiaBan,SoLuong,dbo.NHOMSACH.TenNhom,dbo.SACH.TinhTrang FROM dbo.SACH JOIN dbo.NHOMSACH ON NHOMSACH.MaNhomSach = SACH.MaNhomSach WHERE MaSach = ?";
            DataProvider.getInstance().Open();
            ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maSach});
            while(rs.next()){
                sach = new Sach();
                sach.setMaSach(rs.getString("MaSach").trim());
                sach.setTenSach(rs.getString("TenSach").trim());
                sach.setTacGia(rs.getString("TacGia").trim());
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGiaNhap(rs.getDouble("GiaNhap"));
                sach.setGiaBan(rs.getDouble("GiaBan"));
                sach.setNhomHang(rs.getString("TenNhom").trim());
                sach.setTinhTrang(rs.getBoolean("TinhTrang"));
            }
        DataProvider.getInstance().Close();
       }
       return sach;
   }
    public ArrayList<Sach> BoLoc(String tenHoacMa, String NhomSach) throws SQLException
    {
        String sql = "SELECT MaSach,TenSach,TacGia,GiaNhap,GiaBan,SoLuong,dbo.NHOMSACH.TenNhom,dbo.SACH.TinhTrang FROM dbo.SACH JOIN dbo.NHOMSACH ON NHOMSACH.MaNhomSach = SACH.MaNhomSach WHERE 1 = 1";
        boolean[] bit = new boolean[3];
        
        if (NhomSach != null) {
            sql = sql + " AND TenNhom = ? ";
            bit[0] = true;
        }
        
        if (tenHoacMa.trim().length() != 0) {
            sql = sql + " AND TenSach LIKE ? OR MaSach LIKE ? ";
            bit[1] = true;
            bit[2] = true;
        }
        
        sql = sql + " ORDER BY CONVERT(INT,SUBSTRING(dbo.SACH.MaSach,2,100)) DESC";
        
        ArrayList<Sach> sachs = null;
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryFilter(sql,bit, new Object[]{NhomSach,"%"+tenHoacMa+"%","%"+tenHoacMa+"%"});
        sachs = new ArrayList<Sach>();
        while(rs.next()){
           Sach sach = new Sach();
           sach.setMaSach(rs.getString("MaSach").trim());
           sach.setTenSach(rs.getString("TenSach").trim());
           sach.setTacGia(rs.getString("TacGia").trim());
           sach.setSoLuong(rs.getInt("SoLuong"));
           sach.setGiaNhap(rs.getDouble("GiaNhap"));
           sach.setGiaBan(rs.getDouble("GiaBan"));
           sach.setNhomHang(rs.getString("TenNhom").trim());
           sach.setTinhTrang(rs.getBoolean("TinhTrang"));
           sachs.add(sach);
       }
       DataProvider.getInstance().Close();
       return sachs;
    }
    public boolean ThemSach(Sach sach ) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_ThemSach @TenSach = ?, @TacGia = ?, @GiaNhap = ?, @GiaBan = ?, @NhomSach = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{sach.getTenSach(),sach.getTacGia(),sach.getGiaNhap(),sach.getGiaBan(),sach.getNhomHang(), sach.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
    public boolean UpdateSach(Sach sach ) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_UpdateSach @MaSach = ?, @TenSach = ?, @TacGia = ?, @GiaNhap = ?, @GiaBan = ?, @NhomSach = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{sach.getMaSach(),sach.getTenSach(),sach.getTacGia(),sach.getGiaNhap(),sach.getGiaBan(),sach.getNhomHang(), sach.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
    public int TinhSachHetHang() throws SQLException{
        int result = 0;
        String sql = "SELECT COUNT(*) AS SoLuong FROM SACH WHERE SoLuong = 0 AND TinhTrang = 1";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        while(rs.next()){
            result = rs.getInt("SoLuong");
        }
        DataProvider.getInstance().Close();
        return result ;
    }
    public int TinhSoSachTon() throws SQLException{
        int result = 0;
        String sql = "SELECT SUM(SoLuong) AS SoLuong FROM SACH WHERE TinhTrang = 1";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        while(rs.next()){
            result = rs.getInt("SoLuong");
        }
        DataProvider.getInstance().Close();
        return result ;
    }
}
