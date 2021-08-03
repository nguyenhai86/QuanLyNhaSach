/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.ChiTietPhieuNhap;
import DTO.DateBox;
import DTO.PhieuNhap;
import DTO.Sach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class BPhieuNhap {
    private static BPhieuNhap instance;
    public static BPhieuNhap getInstance(){
        if(instance == null)
        {
            instance = new BPhieuNhap();
        }
        return instance;
    }   
    private static void setInstance(BPhieuNhap instance){
        BPhieuNhap.instance = instance;
    }
    private BPhieuNhap(){}
    
    public ArrayList<PhieuNhap> getPhieuNhaps() throws SQLException{
        ArrayList<PhieuNhap> phieuNhaps = null;
        String sql = "EXEC P_GetAllPhieuNhap";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        phieuNhaps = new ArrayList<PhieuNhap>();
        while(rs.next()){
            PhieuNhap phieuNhap = new PhieuNhap();
            phieuNhap.setMaPhieuNhap(rs.getString("MaPhieuNhap").trim());
            phieuNhap.setNgayNhap(DateBox.getDate(rs.getString("NgayNhap")));
            phieuNhap.setTenNhanVien(rs.getString("TenNhanVien").trim());
            phieuNhap.setTenNhaCungCap(rs.getString("TenNCC").trim());
            phieuNhaps.add(phieuNhap);
        }
        DataProvider.getInstance().Close();
        return phieuNhaps;
    }
    public ArrayList<ChiTietPhieuNhap> getChiTietPhieuNhaps(String maPhieuNhap) throws SQLException{
        ArrayList<ChiTietPhieuNhap> chiTietPhieuNhaps = null;
        String sql = "EXEC P_GetCTPNByMa @maPhieuNhap = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maPhieuNhap});
        chiTietPhieuNhaps = new ArrayList<ChiTietPhieuNhap>();
        while(rs.next()){
            ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
            chiTietPhieuNhap.setMaSach(rs.getString("MaSach").trim());
            chiTietPhieuNhap.setTenSach(rs.getString("TenSach").trim());
            chiTietPhieuNhap.setSoLuong(rs.getInt("SoLuong"));
            chiTietPhieuNhap.setGiaNhap(rs.getDouble("DonGia"));
            chiTietPhieuNhaps.add(chiTietPhieuNhap);
        }
        DataProvider.getInstance().Close();
        return chiTietPhieuNhaps;
    }
    
    public ArrayList<PhieuNhap> BoLoc (String maPhieuNhap, String tenNhanVien, String tenNhaCungCap, Date dateBD, Date dateKT) throws SQLException{
        String sql = "SET DATEFORMAT DMY SELECT pn.MaPhieuNhap, CONVERT(CHAR(10), PN.NgayNhap, 103) AS NgayNhap, NV.TenNhanVien, ncc.TenNCC FROM dbo.PHIEUNHAP PN JOIN dbo.NHACUNGCAP NCC ON NCC.MaNCC = PN.MaNCC JOIN dbo.NHANVIEN NV ON NV.MaNhanVien = PN.MaNhanVien WHERE 1=1 ";
        boolean[] bit = new boolean[5];
        
        if (maPhieuNhap.trim().length() != 0) {
            sql = sql + " AND pn.MaPhieuNhap LIKE ?";
            bit[0] = true;
        }
        else
            maPhieuNhap = null;
        
        if (tenNhanVien != null) {
            sql = sql + " AND NV.TenNhanVien = ? ";
            bit[1] = true;
        }
        if (tenNhaCungCap != null) {
            sql = sql + " AND ncc.TenNCC = ? ";
            bit[2] = true;
        }
        if (dateBD != null && dateKT != null) {
            sql = sql + " AND PN.NgayNhap BETWEEN ? AND ? ";
            bit[3] = true;//NgayBD
            bit[4] = true;//NgayKT
        }
        sql = sql + " ORDER BY CONVERT(INT,SUBSTRING(PN.MaPhieuNhap,3,100)) DESC ";
        
        ArrayList<PhieuNhap> phieuNhaps = null;
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryFilter(sql,bit, new Object[]{"%"+maPhieuNhap+"%",tenNhanVien ,tenNhaCungCap,DateBox.ConvertToString(dateBD),DateBox.ConvertToString(dateKT)});
        phieuNhaps = new ArrayList<PhieuNhap>();
        while(rs.next()){
            PhieuNhap phieuNhap = new PhieuNhap();
            phieuNhap.setMaPhieuNhap(rs.getString("MaPhieuNhap").trim());
            phieuNhap.setNgayNhap(DateBox.getDate(rs.getString("NgayNhap")));
            phieuNhap.setTenNhanVien(rs.getString("TenNhanVien").trim());
            phieuNhap.setTenNhaCungCap(rs.getString("TenNCC").trim());
            phieuNhaps.add(phieuNhap);
        }
        DataProvider.getInstance().Close();
        return phieuNhaps;
        
    }

    public boolean ThemPhieuNhap(PhieuNhap phieuNhap, ArrayList<Sach> sachs) throws SQLException{
        boolean result = false;
        String sql_PhieuNhap = "SET DATEFORMAT DMY EXEC dbo.P_ThemPhieuNhap @MaNhanVien = ?, @MaNhaCungCap = ?, @NgayNhap =?";
        String sql_ChiTietPN = "EXEC dbo.P_ThemChiTietPhieuNhap @MaSach = ?, @DonGia = ?, @SoLuong = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql_PhieuNhap, new Object[]{phieuNhap.getTenNhanVien(), phieuNhap.getTenNhaCungCap(), DateBox.ConvertToString(phieuNhap.getNgayNhap())}) > 0;
         if (result) {
             for (int i = 0; i < sachs.size(); i++) {
                 result = DataProvider.getInstance().executeUpdatePrepareStatement(sql_ChiTietPN, new Object[]{sachs.get(i).getMaSach(), sachs.get(i).getGiaNhap(), sachs.get(i).getSoLuong()}) > 0;
             }
         }
        DataProvider.getInstance().Close();
        return result ;
    }
    public String getMaPhieuNhapMoiNhat() throws SQLException{
        String maPhieuNhap = null;
        String sql = "SELECT  dbo.F_MaPhieuNhapMoiNhat() AS 'MaPhieuNhap'";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        while(rs.next()){
            maPhieuNhap = rs.getString("MaPhieuNhap");
        }
        DataProvider.getInstance().Close();
        return maPhieuNhap;
    }
}
