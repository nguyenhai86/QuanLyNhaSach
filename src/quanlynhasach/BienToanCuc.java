/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

import BLL.BNhanVien;
import DTO.NhanVien;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author nguye
 */
public class BienToanCuc {
    private BienToanCuc(){try {
        this.nguoiDangNhap = BNhanVien.getInstance().getNhanVienByMaNV("nv001");
        } catch (SQLException ex) {
            Logger.getLogger(BienToanCuc.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private static BienToanCuc instance;
    public static BienToanCuc getInstance() {
        if (instance == null)
            instance = new BienToanCuc();
        return instance;
    }
    
    private NhanVien nguoiDangNhap;
    private F_About f_About;
    private F_DoiServer f_DoiSever;
    private F_Home f_Home;
    private F_KhachHang f_KhachHang;
    private F_Login f_Login;
    private F_NhanVien f_NhanVien;
    private F_NhapSach f_NhapSach;
    private F_Pos f_Pos;
    private F_QuanLy f_QuanLy;
    private F_ThongTinCaNhan f_ThongTinCaNhan;
    private F_ThemKhachHang f_ThemKhachHang;
    
    public F_ThemKhachHang getF_ThemKhachHang() {
        if (f_ThemKhachHang == null || !f_ThemKhachHang.isVisible()) {
            f_ThemKhachHang = new F_ThemKhachHang();
            f_ThemKhachHang.setLocationRelativeTo(null);
        }
        return f_ThemKhachHang;
    }
    
    
    public F_About getF_About() {
        if (f_About == null || !f_About.isVisible()) {
            f_About = new F_About();
            f_About.setLocationRelativeTo(null);
        }
        return f_About;
    }
    public F_DoiServer getF_DoiSever() {
        if (f_DoiSever == null || !f_DoiSever.isVisible()) {
            f_DoiSever = new F_DoiServer();
            f_DoiSever.setLocationRelativeTo(null);
        }
        return f_DoiSever;
    }
    public F_Home getF_Home() {
        if (f_Home == null || !f_Home.isVisible()) {
            f_Home = new F_Home();
            f_Home.setLocationRelativeTo(null);
        }
        return f_Home;
    }
    public F_KhachHang getF_KhachHang() {
        if (f_KhachHang == null || !f_KhachHang.isVisible()) {
            f_KhachHang = new F_KhachHang();
            f_KhachHang.setLocationRelativeTo(null);
        }
        return f_KhachHang;
    }
    public F_Login getF_Login() {
        if (f_Login == null || !f_Login.isVisible()) {
            f_Login = new F_Login();
            f_Login.setLocationRelativeTo(null);
        }
        return f_Login;
    }
    public F_NhanVien getF_NhanVien() {
        if (f_NhanVien == null || !f_NhanVien.isVisible()) {
            f_NhanVien = new F_NhanVien();
            f_NhanVien.setLocationRelativeTo(null);
        }
        return f_NhanVien;
    }
    public F_NhapSach getF_NhapSach() {
        if (f_NhapSach == null || !f_NhapSach.isVisible()) {
            f_NhapSach = new F_NhapSach();
            f_NhapSach.setLocationRelativeTo(null);
        }
        return f_NhapSach;
    }
    public F_Pos getF_Pos() {
        if (f_Pos == null || !f_Pos.isVisible()) {
            f_Pos = new F_Pos();
            f_Pos.setLocationRelativeTo(null);
        }
        return f_Pos;
    }
    public F_QuanLy getF_QuanLy() {
        if (f_QuanLy == null || !f_QuanLy.isVisible()) {
            f_QuanLy = new F_QuanLy();
            f_QuanLy.setLocationRelativeTo(null);
        }
        return f_QuanLy;
    }
    public F_ThongTinCaNhan getF_ThongTinCaNhan() {
        if (f_ThongTinCaNhan == null || !f_ThongTinCaNhan.isVisible()) {
            f_ThongTinCaNhan = new F_ThongTinCaNhan();
            f_ThongTinCaNhan.setLocationRelativeTo(null);
        }
        return f_ThongTinCaNhan;
    }
    
    public void closeF_About(){
        if (f_About != null)
            f_About.dispose();
    }
    public void closeF_DoiServer(){
        if (f_DoiSever != null)
            f_DoiSever.dispose();
    }
    public void closeF_KhachHang(){
        if (f_KhachHang != null)
            f_KhachHang.dispose();
    }
    public void closeF_Home(){
        if (f_Home != null)
            f_Home.dispose();
    }
    public void closeF_Login(){
        if (f_Login != null)
            f_Login.dispose();
    }
    public void closeF_NhanVien(){
        if (f_NhanVien != null)
            f_NhanVien.dispose();
    }
    public void closeF_NhapSach(){
        if (f_NhapSach != null)
            f_NhapSach.dispose();
    }
    public void closeF_Pos(){
        if (f_Pos != null) {
            closeF_ThemKhachHang();
            f_Pos.dispose();
        }
    }
    public void closeF_ThongTinCaNhan(){
        if (f_ThongTinCaNhan != null)
            f_ThongTinCaNhan.dispose();
    }
    public void closeF_QuanLy(){
        if (f_QuanLy != null) {
            f_QuanLy.dispose();
        }
    }
    
    public NhanVien getNguoiDangNhap() {
        return nguoiDangNhap;
    }
    public void setNguoiDangNhap(NhanVien nguoiDangNhap) {
        this.nguoiDangNhap = nguoiDangNhap;
    }
    public void closeF_ThemKhachHang(){
        if (f_ThemKhachHang != null) {
            f_ThemKhachHang.dispose();
        }
    }
}
