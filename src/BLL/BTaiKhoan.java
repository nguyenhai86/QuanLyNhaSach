/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.TaiKhoan;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;



/**
 *
 * @author nguye
 */
public class BTaiKhoan {
    
    private static BTaiKhoan instance;
    public static BTaiKhoan getInstance() {
        if (instance == null) {
            instance = new BTaiKhoan();
        }
        return instance;
    }
    private void setInstance(BTaiKhoan instance) {
        this.instance = instance;
    }
    private BTaiKhoan(){}
    
    public ArrayList<TaiKhoan> getTaiKhoans() throws SQLException
    {
        ArrayList<TaiKhoan> taiKhoans = null;
        String sql = "EXEC dbo.P_GetAllTaiKhoan";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);

        taiKhoans = new ArrayList<TaiKhoan>();
        while(rs.next())
        {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTenDangNhap(rs.getString("TenDN").trim());
            taiKhoan.setMaNhanVien(rs.getString("MaNhanVien").trim());
            taiKhoan.setTenNhanVien(rs.getString("TenNhanVien").trim());
            taiKhoans.add(taiKhoan);
        }
        DataProvider.getInstance().Close();
        return taiKhoans;
    }
    
    public ArrayList<TaiKhoan> SearchTaiKhoan(String value) throws SQLException
    {
        ArrayList<TaiKhoan> taiKhoans = null;
        String sql = "SELECT TenDN,TK.MaNhanVien, TenNhanVien FROM dbo.TAIKHOAN TK JOIN dbo.NHANVIEN NV ON NV.MaNhanVien LIKE TK.MaNhanVien WHERE NV.MaNhanVien LIKE ? OR NV.TenNhanVien LIKE ? OR TenDN LIKE ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{"%"+ value + "%",  "%"+ value + "%",  "%"+ value + "%"});

        taiKhoans = new ArrayList<TaiKhoan>();
        while(rs.next())
        {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTenDangNhap(rs.getString("TenDN").trim());
            taiKhoan.setMaNhanVien(rs.getString("MaNhanVien").trim());
            taiKhoan.setTenNhanVien(rs.getString("TenNhanVien").trim());
            taiKhoans.add(taiKhoan);
        }
        DataProvider.getInstance().Close();
            
        return taiKhoans;
    }
    
    public boolean ThemTaiKhoan(TaiKhoan taiKhoan) throws SQLException
    {  
       boolean result = false;
        String sql = "EXEC dbo.P_ThemTaiKhoan @TenDangNhap = ?, @MaNhanVien = ?, @MatKhau = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{taiKhoan.getTenDangNhap(),taiKhoan.getMaNhanVien(), "c4ca4238a0b923820dcc509a6f75849b"}) > 0;
        DataProvider.getInstance().Close();
       return result;
    }
    
    public boolean CheckEistsMaNV(String maNV) throws SQLException
    {
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.TaiKhoan WHERE MaNhanVien = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maNV});
        if (rs.next())
            return true;
        DataProvider.getInstance().Close();
        return false;
    }
    
    public boolean CheckEistsTenDN (String tenDN) throws SQLException
    {
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.TaiKhoan WHERE TenDN = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenDN});
        if (rs.next())
            return true;
        DataProvider.getInstance().Close();
        return false;
    }
    
    public boolean checkAccess(String tenDN) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT * FROM NHANVIEN JOIN TAIKHOAN ON TAIKHOAN.MaNhanVien = NHANVIEN.MaNhanVien WHERE TinhTrang = 1 AND tenDN = ?";
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenDN});
        if (rs.next())
            return true;
        DataProvider.getInstance().Close();
        
        return false;
    }
    
    public boolean CheckDangNhap(String tenDN, String pass) throws SQLException
    {
        String passMD5 = DigestUtils.md5Hex(pass);
        
        String sql = "SELECT * FROM TaiKhoan WHERE TenDN = ? AND MatKhau = ?";
        boolean result = false;
        ResultSet rs = null;
        
        DataProvider.getInstance().Open();
        rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenDN,passMD5});

        if (rs.next())
            result = true;
        DataProvider.getInstance().Close();
        return result;
    }
    
    public boolean DeteleTaiKhoan(String tenDN) throws SQLException
    {
        boolean result = false;
        String sql = "DELETE TAIKHOAN WHERE TenDN = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{tenDN}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean UpdateTaiKhoan(String tenDN, String newPass) throws SQLException
    {
        String passMD5 = DigestUtils.md5Hex(newPass);
        boolean result = false;
        String sql = "UPDATE dbo.TAIKHOAN SET MatKhau = ? WHERE TenDN = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{passMD5,tenDN}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public TaiKhoan getTaiKhoanByMaNV(String maNV) throws SQLException
    {
        TaiKhoan taiKhoan = null;
        String sql = "SELECT TenDN,NHANVIEN.MaNhanVien, TenNhanVien FROM dbo.TAIKHOAN JOIN dbo.NHANVIEN ON NHANVIEN.MaNhanVien = TAIKHOAN.MaNhanVien WHERE NHANVIEN.MaNhanVien = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{maNV});

        while(rs.next())
        {
            taiKhoan = new TaiKhoan();
            taiKhoan.setTenDangNhap((rs.getString("TenDN")).trim());
            taiKhoan.setMaNhanVien(rs.getString("MaNhanVien").trim());
            taiKhoan.setTenNhanVien(rs.getString("TenNhanVien").trim());
        }
        DataProvider.getInstance().Close();
        return taiKhoan;
    }
}
