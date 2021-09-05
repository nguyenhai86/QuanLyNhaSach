/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.DateBox;
import DTO.NhanVien;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nguye
 */
public class BNhanVien {
    //Instance
    private static BNhanVien instance;
    public static BNhanVien getInstance() {
        if (instance == null) {
            instance = new BNhanVien();
        }
        return instance;
    }
    private static void setInstance(BNhanVien instance) {
        BNhanVien.instance = instance;
    }
    private BNhanVien(){}
    
    public ArrayList<NhanVien> getNhanViens() throws SQLException{
        ArrayList<NhanVien> nhanViens = null;

        String sql = "EXEC P_getALLNhanVien";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        nhanViens = new ArrayList<NhanVien>();
        while(rs.next()){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh").trim().trim());
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND").trim());
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhanViens.add(nhanVien);
        }
        DataProvider.getInstance().Close();
        return nhanViens;
    }
    public NhanVien getNhanVienByMaNV(String maNV) throws SQLException {
        if (maNV == null)
            return null;
        
        NhanVien nhanVien = null;
        String sql = "SELECT * FROM dbo.NHANVIEN WHERE MaNhanVien = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{maNV});
        while(rs.next()){
            nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh"));
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND").trim());
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
        }
        DataProvider.getInstance().Close();
        return nhanVien;
      
    }
    public ArrayList<NhanVien> getArrayNhanVienBySDT(String soDienThoai) throws SQLException{
        if (soDienThoai == null)
            return null;
        
        ArrayList<NhanVien> nhanViens = null;
        String sql = "SELECT * FROM dbo.NHANVIEN WHERE DienThoai LIKE ? rb_Sach_BinhThuong.getText()";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{soDienThoai+'%'});
        while(rs.next()){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh").trim());
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND").trim());
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhanViens.add(nhanVien);
        }
        DataProvider.getInstance().Close();
        return nhanViens;
    }
    public NhanVien getNhanVienByCMND(String chungMinhND) throws SQLException{
        if (chungMinhND == null)
            return null;
        
        NhanVien nhanVien = null;
        String sql = "SELECT * FROM dbo.NHANVIEN WHERE SoCMND = ? ";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{chungMinhND});
        while(rs.next()){
            nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh").trim());
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND").trim());
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
        }
        DataProvider.getInstance().Close();
        return nhanVien;
    }
    public ArrayList<NhanVien> searchNV(String value) throws SQLException{
        ArrayList<NhanVien> nhanViens = null;
        String sql = "SELECT MaNhanVien,TenNhanVien,GioiTinh,CONVERT(CHAR(10),NgaySinh,103) AS 'NgaySinh',SoCMND,DienThoai,TinhTrang FROM NHANVIEN WHERE MaNhanVien LIKE ? OR TenNhanVien LIKE ? ORDER BY MaNhanVien DESC";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{"%"+value+"%","%"+value+"%"});
        nhanViens = new ArrayList<NhanVien>();
        while(rs.next()){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh").trim());
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND").trim());
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhanViens.add(nhanVien);
        }
        DataProvider.getInstance().Close();
        return nhanViens;
    }
    public boolean ThemNhanVien(NhanVien nv ) throws SQLException{
        boolean result = false;
        String sql = "SET DATEFORMAT DMY EXEC dbo.P_ThemNhanVien @TenNhanVien = ?, @GioiTinh = ?, @NgaySinh = ?, @SoCMND = ?,@DienThoai = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nv.getTenNhanVien(),nv.getGioiTinh(),DateBox.ConvertToString(nv.getNgaySinh()),nv.getSoCMND(),nv.getDienThoai(),nv.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
    public boolean UpdateNhanVien(NhanVien nv ) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_UpdateNhanVien @MaNhanVien = ?, @TenNhanVien = ?, @GioiTinh = ?, @NgaySinh = ?, @SoCMND = ?, @DienThoai = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nv.getMaNhanVien(),nv.getTenNhanVien(),nv.getGioiTinh(),DateBox.ConvertToString(nv.getNgaySinh()),nv.getSoCMND(),nv.getDienThoai(),nv.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result ;
    }
    public boolean IsExistMaNV(String maNV) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.NHANVIEN WHERE MaNhanVien = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maNV});
        if (rs.next())
            return true;
        DataProvider.getInstance().Close();
        return false;
    }
    public boolean IsExistSoCMND(String soCMND) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.NHANVIEN WHERE SoCMND = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{soCMND});
        if (rs.next())
            return true;
        DataProvider.getInstance().Close();
        return false;
    }
    public NhanVien getNVByTenDN(String tenDN) throws SQLException{
        if (tenDN == null)
            return null;
        NhanVien nhanVien = null;
        String sql = "SELECT NHANVIEN.MaNhanVien,TenNhanVien,GioiTinh,CONVERT(CHAR(10),NgaySinh,103) AS NgaySinh,SoCMND,DienThoai,TinhTrang FROM dbo.NHANVIEN JOIN dbo.TAIKHOAN ON TAIKHOAN.MaNhanVien = NHANVIEN.MaNhanVien WHERE TenDN = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{tenDN});
        while(rs.next()){
            nhanVien = new NhanVien();
            nhanVien.setMaNhanVien(rs.getString("MaNhanVien").trim());
            nhanVien.setTenNhanVien(rs.getString("TenNhanVien").trim());
            nhanVien.setGioiTinh(rs.getString("GioiTinh").trim());
            nhanVien.setNgaySinh(DateBox.getDate(rs.getString("NgaySinh")));
            nhanVien.setSoCMND(rs.getString("SoCMND"));
            nhanVien.setDienThoai(rs.getString("DienThoai").trim());
            nhanVien.setTinhTrang(rs.getBoolean("TinhTrang"));
        }
        DataProvider.getInstance().Close();
        return nhanVien;
    }
    
}
