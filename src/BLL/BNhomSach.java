/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataProvider;
import DTO.NhomSach;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author NguyenHai
 */
public class BNhomSach {
    private static BNhomSach instance;
    public static BNhomSach getInstance() {
        if (instance == null) {
            instance = new BNhomSach();
        }
        return instance;
    }
    private void setInstance(BNhomSach instance) {
        this.instance = instance;
    }
    private BNhomSach(){}
    
    public ArrayList<NhomSach> getNhomSachs() throws SQLException{
        ArrayList<NhomSach> nhomSachs = new ArrayList<NhomSach>();
        
        String sql = "EXEC P_GetAllNhomSach";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQuery(sql);
        
        
        while(rs.next()){
            NhomSach nhomSach = new NhomSach();
            nhomSach.setMaNhomSach(rs.getString("MaNhomSach").trim());
            nhomSach.setTenNhom(rs.getString("TenNhom").trim());
            nhomSach.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhomSach.setSoLuong(rs.getInt("SoLuong"));
            nhomSachs.add(nhomSach);
        }
        DataProvider.getInstance().Close();
        return nhomSachs;
    }
    public boolean ThemNhomSach(NhomSach nhomSach) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_ThemNhomSach @TenNhom = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nhomSach.getTenNhom(),nhomSach.isTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean UpdateNhomSach(NhomSach nhomSach) throws SQLException{
        boolean result = false;
        String sql = "EXEC dbo.P_UpdateNhomSach @MaNhomSach = ?, @TenNhom = ?, @TinhTrang = ?";
        DataProvider.getInstance().Open();
        result = DataProvider.getInstance().executeUpdatePrepareStatement(sql, new Object[]{nhomSach.getMaNhomSach(),nhomSach.getTenNhom(),nhomSach.isTinhTrang()}) > 0;
        DataProvider.getInstance().Close();
        return result;
    }
    public boolean IsExistTenNS(String tenNhom) throws SQLException{
        boolean flag = false;
        String sql = "SELECT * FROM NHOMSACH WHERE TenNhom = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenNhom});
        if(rs.next())
            flag = true;
        DataProvider.getInstance().Close();
        return flag;
    }
    public NhomSach getNhomSachByTenNhom(String tenNhom) throws SQLException{
        NhomSach nhomSach = null;
        
        String sql = "EXEC P_GetNhomSachByTenNhom @TenNhom = ?";
        DataProvider.getInstance().Open();
        ResultSet rs = DataProvider.getInstance().executeQueryPrepareStatement(sql, new Object[]{tenNhom});
        
        while(rs.next()){
            nhomSach = new NhomSach();
            nhomSach.setMaNhomSach(rs.getString("MaNhomSach").trim());
            nhomSach.setTenNhom(rs.getString("TenNhom").trim());
            nhomSach.setTinhTrang(rs.getBoolean("TinhTrang"));
            nhomSach.setSoLuong(rs.getInt("SoLuong"));
        }
        DataProvider.getInstance().Close();
        return nhomSach;
    }
}
