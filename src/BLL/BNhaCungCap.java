/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * xcx
 */
package BLL;

import DAL.DataProvider;
import DTO.NhaCungCap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author ADMIN
 */
public class BNhaCungCap {

    private static BNhaCungCap instance;
    public static BNhaCungCap getInstance() {
        if (instance == null) {
            instance = new BNhaCungCap();
        }
        return instance;
    }
    private static void setInstance(BNhaCungCap instance) {
        BNhaCungCap.instance = instance;
    }
    private BNhaCungCap(){}
    
    public ArrayList<NhaCungCap> getNhaCungCaps() throws SQLException{
        ArrayList<NhaCungCap> nhaCungCaps = null;
        String sql = "EXEC P_getNhaCungCap";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);

        nhaCungCaps = new ArrayList<NhaCungCap>();
        while(rs.next())
        {
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setMaNhaCC(rs.getString("MaNCC").trim());
            nhaCungCap.setTenNhaCC(rs.getString("TenNCC").trim());
            nhaCungCap.setDienThoai(rs.getString("DienThoai").trim());
            nhaCungCap.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhaCungCaps.add(nhaCungCap);
        }
        DataProvider.getInstance().Close();
        return nhaCungCaps;
    }
    public NhaCungCap getNhaCungCapByTenNCC(String tenNCC) throws SQLException{
        String sql = "SELECT * FROM dbo.NHACUNGCAP WHERE TenNCC = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenNCC});
        NhaCungCap nhaCungCap = null;
        while(rs.next())
        {
            nhaCungCap = new NhaCungCap();
            nhaCungCap.setMaNhaCC(rs.getString("MaNCC").trim());
            nhaCungCap.setTenNhaCC(rs.getString("TenNCC").trim());
            nhaCungCap.setDienThoai(rs.getString("DienThoai").trim());
            nhaCungCap.setTinhTrang(rs.getBoolean("TinhTrang"));
        }
        DataProvider.getInstance().Close();
        return nhaCungCap;
    }
    public ArrayList<NhaCungCap> searchNCC(String value) throws SQLException{
        ArrayList<NhaCungCap> nhaCungCaps = null;
        String sql = "SELECT * FROM dbo.NHACUNGCAP WHERE MaNCC LIKE ? OR TenNCC LIKE ? OR DienThoai = ? ORDER BY CONVERT(INT,SUBSTRING(dbo.NHACUNGCAP.MaNCC,4,100)) DESC";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{"%"+value+"%","%"+value+"%",value});
        
        nhaCungCaps = new ArrayList<NhaCungCap>();
        while(rs.next())
        {
            NhaCungCap nhaCungCap = new NhaCungCap();
            nhaCungCap.setMaNhaCC(rs.getString("MaNCC").trim());
            nhaCungCap.setTenNhaCC(rs.getString("TenNCC").trim());
            nhaCungCap.setDienThoai(rs.getString("DienThoai").trim());
            nhaCungCap.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhaCungCaps.add(nhaCungCap);
        }
        DataProvider.getInstance().Close();
        return nhaCungCaps;
    }
    public boolean IsExistMaNCC(String maNCC) throws SQLException{
        boolean result = false;
        String sql = "SELECT * FROM dbo.NHACUNGCAP WHERE MaNCC = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql,new Object[]{maNCC});
        if (rs.next())
            result = true;
        DataProvider.getInstance().Close();
        return result;
    }
    
    public boolean ThemNhaCungCap(NhaCungCap ncc) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_ThemNhaCungCap @TenNCC = ?, @DienThoai = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{ncc.getTenNhaCC(),ncc.getDienThoai(), ncc.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean UpdateNhaCungCap(NhaCungCap ncc) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_UpdateNhaCungCap @MaNCC = ?, @TenNCC = ?, @DienThoai = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{ncc.getMaNhaCC(),ncc.getTenNhaCC(),ncc.getDienThoai(), ncc.getTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
}
