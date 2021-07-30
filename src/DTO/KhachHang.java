/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author nguye
 */
public class KhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private String dienThoai;
    private String nhomKhachHang;
    private Date lanCuoiMuaHang;
    private Double tongTien;
    private boolean tinhTrang;

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getNhomKhachHang() {
        return nhomKhachHang;
    }

    public void setNhomKhachHang(String nhomKhachHang) {
        this.nhomKhachHang = nhomKhachHang;
    }

    public Date getLanCuoiMuaHang() {
        return lanCuoiMuaHang;
    }

    public void setLanCuoiMuaHang(Date lanCuoiMuaHang) {
        this.lanCuoiMuaHang = lanCuoiMuaHang;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
