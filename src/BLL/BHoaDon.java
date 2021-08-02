/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;


import DTO.DateBox;
import DTO.HoaDon;
import DAL.DataProvider;
import DTO.ChiTietHoaDon;
import DTO.NhanVien;
import DTO.Sach;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class BHoaDon {
    //Instance
    private static BHoaDon instance;
    public static BHoaDon getInstance(){
        if(instance == null)
        {
            instance = new BHoaDon();
        }
        return instance;
    }
    private static void setInstance(BHoaDon instance){
        BHoaDon.instance = instance;
    }
    private BHoaDon(){}
    
    public ArrayList<HoaDon> getHoaDons() throws SQLException{
        ArrayList<HoaDon> hoaDons = null;
        String sql = "EXEC P_GetAllHoaDon";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        hoaDons = new ArrayList<HoaDon>();
        while(rs.next()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaDon(rs.getString("MaHoaDon").trim());
            hoaDon.setNgayBan(DateBox.getDate(rs.getString("NgayBan")));
            hoaDon.setNhanVien(rs.getString("TenNhanVien").trim());
            hoaDon.setKhachHang(rs.getString("TenKhachHang").trim());
            hoaDon.setTongTien(rs.getDouble("TongTien"));
            hoaDons.add(hoaDon);
        }
        DataProvider.getInstance().Close();
        return hoaDons;
    }
    
    public ArrayList<ChiTietHoaDon> getChiTietHoaDons(String maHoaDon) throws SQLException{
        ArrayList<ChiTietHoaDon> chiTietHoaDons = null;
        String sql = "EXEC P_GetCTHDByMa @maHoaDon = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maHoaDon});
        chiTietHoaDons = new ArrayList<ChiTietHoaDon>();
        while(rs.next()){
           ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
           chiTietHoaDon.setMaSach(rs.getString("MaSach").trim());
           chiTietHoaDon.setTenSach(rs.getString("TenSach").trim());
           chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
           chiTietHoaDon.setGiaBan(rs.getDouble("DonGia"));
           chiTietHoaDon.setThanhTien(chiTietHoaDon.getGiaBan() * chiTietHoaDon.getSoLuong());
           chiTietHoaDons.add(chiTietHoaDon);
        }
        DataProvider.getInstance().Close();
        return chiTietHoaDons;
    }
    
    public ArrayList<HoaDon> BoLoc(String TenNhanVien, String TenKhachHang, Date dateBD, Date dateKT) throws SQLException
    {
        String sql = "SET DATEFORMAT DMY SELECT MaHoaDon, CONVERT(CHAR(10),NgayBan,103) AS NgayBan, TenNhanVien, TenKhachHang, hd.TongTien FROM HOADON  hd JOIN NHANVIEN nv on nv.MaNhanVien= hd.MaNhanVien JOIN KHACHHANG kh on kh.MaKhachHang = hd.MaKhachHang WHERE 1=1";
        boolean[] bit = new boolean[4];
        
        if (TenNhanVien != null) {
            sql = sql + " AND TenNhanVien = ?";
            bit[0] = true;
        }
        if (TenKhachHang.trim().length() != 0) {
            sql = sql + " AND TenKhachHang LIKE ?";
            bit[1] = true;
        }
        if (dateBD != null && dateKT != null) {
            sql = sql + " AND hd.NgayBan BETWEEN ? AND ?";
            bit[2] = true;//NgayBD
            bit[3] = true;//NgayKT
        }
        sql = sql + " ORDER BY CONVERT(INT,SUBSTRING(HD.MaHoaDon,3,100)) DESC";
                
        ArrayList<HoaDon> hoaDons = null;
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryFilter(sql,bit, new Object[]{TenNhanVien,"%"+TenKhachHang+"%",DateBox.ConvertToString(dateBD),DateBox.ConvertToString(dateKT)});
        hoaDons = new ArrayList<HoaDon>();
        while(rs.next()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaDon(rs.getString("MaHoaDon").trim());
            hoaDon.setNgayBan(DateBox.getDate(rs.getString("NgayBan")));
            hoaDon.setNhanVien(rs.getString("TenNhanVien").trim());
            hoaDon.setKhachHang(rs.getString("TenKhachHang").trim());
            hoaDon.setTongTien(rs.getDouble("TongTien"));
            hoaDons.add(hoaDon);
        }
        DataProvider.getInstance().Close();
        return hoaDons;
    }
    public boolean ThemHoaDon(HoaDon hoaDon, ArrayList<Sach> sachs) throws SQLException{
        boolean result = false;
        String sql_HoaDon = "SET DATEFORMAT DMY EXEC dbo.P_ThemHoaDon @MaNhanVien = ?, @MaKhachHang = ?, @NgayBan =?";
        String sql_ChiTietHD = "EXEC dbo.P_ThemChiTietHoaDon @MaSach = ?, @DonGia = ?, @SoLuong = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql_HoaDon, new Object[]{hoaDon.getNhanVien(), hoaDon.getKhachHang(), DateBox.ConvertToString(hoaDon.getNgayBan())}) > 0;
         if (result) {
             for (int i = 0; i < sachs.size(); i++) {
                 result = DataProvider.getInstance().executeUpdatePrepareStatement(sql_ChiTietHD, new Object[]{sachs.get(i).getMaSach(), sachs.get(i).getGiaBan(), sachs.get(i).getSoLuong()}) > 0;
             }
         }
        DataProvider.getInstance().Close();
        return result ;
    }
}
