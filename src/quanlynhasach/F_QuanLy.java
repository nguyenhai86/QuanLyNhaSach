/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

import BLL.BHoaDon;
import BLL.BNhaCungCap;
import BLL.BNhanVien;
import BLL.BNhomSach;

import BLL.BSach;
import BLL.BPhieuNhap;
import BLL.BQuanLy;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.PhieuNhap;
import DTO.ChiTietPhieuNhap;
import DTO.DateBox;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.NhomSach;
import DTO.Sach;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author nguye
 */
public class F_QuanLy extends javax.swing.JFrame {

    /**
     * Creates new form F_Pos
     */
    public F_QuanLy(){
        initComponents();
        
        Table_HoaDon_Component();
        Table_Header_Component(tb_HD_CTHD);
        
        Table_Sach_Component();
        Table_NhomSach_Component();
        
        Table_PhieuNhap_Component();
        Table_Header_Component(tb_CTPN);
        
        Table_QuanLy_Component();
    }
    
    public void Table_HoaDon_Component(){
        Table_Header_Component(tb_HD_HD);
        LoadHoaDon();
        LoadCB_HD_NV();
    }
    public void Table_Sach_Component(){
        Table_Header_Component(tb_Sach);
        
        TableColumn tinhTrangColumn = tb_Sach.getColumnModel().getColumn(7);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem(rb_Sach_BinhThuong.getText());
        comboBox.addItem(rb_Sach_NgungBan.getText());
        tinhTrangColumn.setCellEditor(new DefaultCellEditor(comboBox));
        
        LoadCB_Sach_ColumnNS();
        LoadCB_Sach_NhomSach();
        LoadSach();
    }
    public void Table_PhieuNhap_Component(){
        Table_Header_Component(tb_PhieuNhap);
        LoadPhieuNhap();
        LoadCB_PN_NV();
        LoadCB_PN_NCC();
    }
    public void Table_NhomSach_Component(){
        Table_Header_Component(tb_NhomSach);
        LoadNhomSach();
    }
    public void Table_QuanLy_Component(){
        try{
            lb_DoanhSo.setText(String.valueOf(BQuanLy.getInstance().TinhDoanhThu()));
            lb_LaiGop.setText(String.valueOf(BQuanLy.getInstance().TinhLai()));
            lb_SoDonHang.setText(String.valueOf(BHoaDon.getInstance().TinhSoDonHang()));
            lb_DHTB.setText(String.valueOf(Math.round(BQuanLy.getInstance().TinhDoanhThu() / BHoaDon.getInstance().TinhSoDonHang())));
            lb_TTK_HangCon.setText(String.valueOf(BSach.getInstance().TinhSoSachTon()));
            lb_TTK_HetHang.setText(String.valueOf(BSach.getInstance().TinhSachHetHang()));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public void Table_Header_Component(JTable table){
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD,15));
        table.getTableHeader().setOpaque(false);
    }
    
    public void ArrayToTableHD(ArrayList<HoaDon> hoaDons){
        if (hoaDons != null) {           
                DefaultTableModel mode = (DefaultTableModel)this.tb_HD_HD.getModel();
                mode.setRowCount(0);   
                for(HoaDon hd:hoaDons){     
                    mode.addRow(new Object[]{hd.getMaDon(), DateBox.ConvertToString(hd.getNgayBan()), hd.getNhanVien(), hd.getKhachHang(), hd.getTongTien()});
                }
            }
    }
    public void ArrayToTableCTHD(ArrayList<ChiTietHoaDon> chiTietHoaDons){
        if (chiTietHoaDons != null) {
                DefaultTableModel mode = (DefaultTableModel)this.tb_HD_CTHD.getModel();
                mode.setRowCount(0);   
                for(ChiTietHoaDon cthd:chiTietHoaDons){     
                    mode.addRow(new Object[]{cthd.getMaSach(),cthd.getTenSach(), cthd.getSoLuong(), cthd.getGiaBan(), cthd.getSoLuong()*cthd.getGiaBan()});
                }
        }  
    }
    public void ArrayToTablePN(ArrayList<PhieuNhap> phieuNhaps){
        if (phieuNhaps != null){
            DefaultTableModel mode = (DefaultTableModel)this.tb_PhieuNhap.getModel();
            mode.setRowCount(0);
            for(PhieuNhap pn:phieuNhaps){
                mode.addRow(new Object[]{pn.getMaPhieuNhap(), DateBox.ConvertToString(pn.getNgayNhap()), pn.getTenNhanVien(), pn.getTenNhaCungCap(),pn.getTongTien()});
            }
        }
    }
    public void ArrayToTableCTPN(ArrayList<ChiTietPhieuNhap> chiTietPhieuNhaps){
        if (chiTietPhieuNhaps != null) {
                DefaultTableModel mode = (DefaultTableModel)this.tb_CTPN.getModel();
                mode.setRowCount(0);   
                for(ChiTietPhieuNhap ctpn:chiTietPhieuNhaps){     
                    mode.addRow(new Object[]{ctpn.getMaSach(),ctpn.getTenSach(),ctpn.getSoLuong(),ctpn.getGiaNhap(), ctpn.getSoLuong() * ctpn.getGiaNhap()});
                }
        }
    } 
    public void ArrayToTableSach(ArrayList<Sach> sachs){
        if (sachs != null) {           
            DefaultTableModel mode = (DefaultTableModel)this.tb_Sach.getModel();
            mode.setRowCount(0);   
            for(Sach s:sachs){     
                mode.addRow(new Object[]{s.getMaSach(), s.getTenSach(), s.getTacGia(), s.getSoLuong(), s.getGiaNhap(), s.getGiaBan(), s.getNhomHang(),s.getTinhTrang()?rb_Sach_BinhThuong.getText():rb_Sach_NgungBan.getText()});
            }
        }
    }
    public void ArrayToTableNhomSach(ArrayList<NhomSach> nhomSachs){
        if (nhomSachs != null) {
            DefaultTableModel mode = (DefaultTableModel) this.tb_NhomSach.getModel();
            mode.setRowCount(0);
            for (NhomSach nhomSach:nhomSachs) {
                mode.addRow(new Object[]{nhomSach.getMaNhomSach(), nhomSach.getTenNhom(),nhomSach.getSoLuong(), nhomSach.isTinhTrang()?rb_NhomSach_BinhThuong.getText():rb_NhomSach_NgungBan.getText()});
            }
        }
    }
            
    public void LoadHoaDon(){
        ArrayList<HoaDon> hoaDons = null;
        try{
            hoaDons = BHoaDon.getInstance().getHoaDons();       
            ArrayToTableHD(hoaDons);
        }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  e.getMessage());
        }
    }
    public void LoadSach(){
        try {
            ArrayList<Sach> sachs = null;
            LoadCB_Sach_ColumnNS();
            sachs = BSach.getInstance().getSachs();
            ArrayToTableSach(sachs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  ex.getMessage());
        }
    }
    public void LoadNhomSach(){
        try{
            ArrayList<NhomSach> nhomSachs = null;
            nhomSachs = BNhomSach.getInstance().getNhomSachs();
            ArrayToTableNhomSach(nhomSachs);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  e.getMessage());
        }
    }
    public void LoadCB_Sach_NhomSach(){
        try{
            ArrayList<NhomSach> nhomSachs = null;
            nhomSachs = BNhomSach.getInstance().getNhomSachs();
            if (nhomSachs != null) {
                cb_NhomSach.removeAllItems();
                cb_NhomSach.addItem(null);
                cb_NhomSach.setSelectedItem(null);
                
                cb_Sach_Them_NhomSach.removeAllItems();
                cb_Sach_Them_NhomSach.setSelectedItem(null);
                
                for (NhomSach nhomSach:nhomSachs) {
                    cb_NhomSach.addItem(nhomSach.getTenNhom());
                    cb_Sach_Them_NhomSach.addItem(nhomSach.getTenNhom());
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  ex.getMessage());
        }
    }
    public void LoadCB_Sach_ColumnNS(){
        try{
            ArrayList<NhomSach> nhomSachs = null;
            nhomSachs = BNhomSach.getInstance().getNhomSachs();
        
            if (nhomSachs != null) {
                TableColumn nhomSachColumn = tb_Sach.getColumnModel().getColumn(6);
                JComboBox comboBox = new JComboBox();
                for (NhomSach nhomSach:nhomSachs) {
                    comboBox.addItem(nhomSach.getTenNhom());
                }
                nhomSachColumn.setCellEditor(new DefaultCellEditor(comboBox));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  ex.getMessage());
        }
    }
    public void LoadPhieuNhap(){
        try {
            ArrayList<PhieuNhap> phieuNhaps = null;
            phieuNhaps = BPhieuNhap.getInstance().getPhieuNhaps();
            ArrayToTablePN(phieuNhaps);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " +  ex.getMessage());
        }
    }
    private void LoadCB_HD_NV() {
        ArrayList<NhanVien> nhanViens = null;
        try{
            nhanViens = BNhanVien.getInstance().getNhanViens();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }
        
        if (nhanViens != null) {
            cb_HD_NV.removeAllItems();
            cb_HD_NV.addItem(null);
            for(NhanVien nv:nhanViens){
                cb_HD_NV.addItem(nv.getTenNhanVien());
            }
        }
    }
    private void LoadCB_PN_NV() {
        ArrayList<NhanVien> nhanViens = null;
        try{
            nhanViens = BNhanVien.getInstance().getNhanViens();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }
        
        if (nhanViens != null) {
            cb_PN_NV.removeAllItems();
            cb_PN_NV.addItem(null);
            for(NhanVien nv:nhanViens){
                cb_PN_NV.addItem(nv.getTenNhanVien());
            }
        }
    }
    private void LoadCB_PN_NCC() {
        ArrayList<NhaCungCap> nhaCungCaps = null;
        try{
            nhaCungCaps = BNhaCungCap.getInstance().getNhaCungCaps();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
        }
        
        if (nhaCungCaps != null) {
            cb_PN_NCC.removeAllItems();
            cb_PN_NCC.addItem(null);
            for(NhaCungCap ncc:nhaCungCaps){
                cb_PN_NCC.addItem(ncc.getTenNhaCC());
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tp_QuanLy = new javax.swing.JTabbedPane();
        pn_TongQuan = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lb_LaiGop = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lb_DoanhSo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_SoDonHang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lb_DHTB = new javax.swing.JLabel();
        PN_DoanhSo = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        cb_TaiChinh = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lb_TC_TienVon = new javax.swing.JLabel();
        lb_TC_TienThu = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lb_TC_LoiNhuan = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        lb_TTK_HangCon = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        lb_TTK_HetHang = new javax.swing.JLabel();
        pn_Sach = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Sach = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        tf_Sach_TenSach = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        tf_Sach_TacGia = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        tf_GiaNhap = new javax.swing.JTextField();
        tf_GiaBan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cb_Sach_Them_NhomSach = new javax.swing.JComboBox<>();
        btn_Sach_Them = new javax.swing.JToggleButton();
        jLabel32 = new javax.swing.JLabel();
        rb_Sach_BinhThuong = new javax.swing.JRadioButton();
        rb_Sach_NgungBan = new javax.swing.JRadioButton();
        btn_Sach_CapNhap = new javax.swing.JToggleButton();
        lb_Sach_New = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        tf_Sach_TenOrMa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cb_NhomSach = new javax.swing.JComboBox<>();
        btn_Sach_Loc = new javax.swing.JToggleButton();
        btn_Sach_BoLoc = new javax.swing.JToggleButton();
        pn_NhomSach = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_NhomSach = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        tf_NhomSach_Ten = new javax.swing.JTextField();
        btn_NhomSach_CapNhap = new javax.swing.JToggleButton();
        btn_NhomSach_Them = new javax.swing.JToggleButton();
        jLabel56 = new javax.swing.JLabel();
        rb_NhomSach_BinhThuong = new javax.swing.JRadioButton();
        rb_NhomSach_NgungBan = new javax.swing.JRadioButton();
        lb_NV_New = new javax.swing.JLabel();
        pn_HoaDon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_HD_HD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_HD_CTHD = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tf_HD_KhachHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        date_HD_NgayBD = new com.toedter.calendar.JDateChooser();
        rb_HD_TheoNgay = new javax.swing.JRadioButton();
        btn_HD_Loc = new javax.swing.JToggleButton();
        jLabel26 = new javax.swing.JLabel();
        cb_HD_NV = new javax.swing.JComboBox<>();
        btn_HD_BoLoc = new javax.swing.JToggleButton();
        date_HD_NgayKT = new com.toedter.calendar.JDateChooser();
        pn_PhieuNhap = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_PhieuNhap = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_CTPN = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tf_PN_MaPN = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        date_PN_NgayBD = new com.toedter.calendar.JDateChooser();
        date_PN_NgayKT = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        cb_PN_NV = new javax.swing.JComboBox<>();
        cb_PN_NCC = new javax.swing.JComboBox<>();
        rb_PN_TheoNgay = new javax.swing.JRadioButton();
        btn_PN_BoLoc = new javax.swing.JToggleButton();
        btn_PN_Loc = new javax.swing.JToggleButton();
        menubar = new javax.swing.JMenuBar();
        mn_Pos = new javax.swing.JMenu();
        mn_QuanLy = new javax.swing.JMenu();
        mn_KhachHang = new javax.swing.JMenu();
        mn_NhanVien = new javax.swing.JMenu();
        mn_HuongDan = new javax.swing.JMenu();
        mni_About = new javax.swing.JMenuItem();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tp_QuanLy.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        tp_QuanLy.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tp_QuanLy.setToolTipText("");
        tp_QuanLy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tp_QuanLy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pn_TongQuan.setBackground(new java.awt.Color(241, 241, 241));

        jPanel12.setBackground(new java.awt.Color(3, 155, 229));
        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        lb_LaiGop.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lb_LaiGop.setForeground(new java.awt.Color(255, 255, 255));
        lb_LaiGop.setText("694000");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Lãi gộp");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(lb_LaiGop, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel43)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_LaiGop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(139, 195, 74));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        lb_DoanhSo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lb_DoanhSo.setForeground(new java.awt.Color(255, 255, 255));
        lb_DoanhSo.setText("694000");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Doanh số");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lb_DoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel40)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_DoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 193, 7));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DHTB:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Trả hàng:");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Số đơn hàng:");

        lb_SoDonHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_SoDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lb_SoDonHang.setText("5");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("0");

        lb_DHTB.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_DHTB.setForeground(new java.awt.Color(255, 255, 255));
        lb_DHTB.setText("138000");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(lb_SoDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_DHTB, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_SoDonHang, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_DHTB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PN_DoanhSo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(39, 117, 239));
        jLabel46.setText("DOANH SỐ");

        javax.swing.GroupLayout PN_DoanhSoLayout = new javax.swing.GroupLayout(PN_DoanhSo);
        PN_DoanhSo.setLayout(PN_DoanhSoLayout);
        PN_DoanhSoLayout.setHorizontalGroup(
            PN_DoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_DoanhSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PN_DoanhSoLayout.setVerticalGroup(
            PN_DoanhSoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PN_DoanhSoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(39, 117, 239));
        jLabel44.setText("TÀI CHÍNH");

        cb_TaiChinh.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cb_TaiChinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Hôm qua", "7 ngày gần đây", "30 ngày gần đây", "Tháng này", "Tháng trước" }));
        cb_TaiChinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TaiChinhActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setText("Tiền thu bán sách:");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel48.setText("Tiền vốn:");

        lb_TC_TienVon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_TC_TienVon.setForeground(new java.awt.Color(255, 51, 51));
        lb_TC_TienVon.setText("0");

        lb_TC_TienThu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_TC_TienThu.setForeground(new java.awt.Color(139, 195, 74));
        lb_TC_TienThu.setText("0");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setText("Lợi nhuận");

        lb_TC_LoiNhuan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_TC_LoiNhuan.setForeground(new java.awt.Color(255, 255, 102));
        lb_TC_LoiNhuan.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addGap(44, 44, 44)
                        .addComponent(lb_TC_LoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_TaiChinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lb_TC_TienThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(lb_TC_TienVon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_TaiChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lb_TC_TienThu))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lb_TC_TienVon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(lb_TC_LoiNhuan))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(39, 117, 239));
        jLabel45.setText("TÌNH TRẠNG KHO");

        lb_TTK_HangCon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_TTK_HangCon.setText("0");

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel53.setText("Sách còn trong kho");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel55.setText("Đầu sách hết hàng");

        lb_TTK_HetHang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_TTK_HetHang.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(lb_TTK_HangCon, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(lb_TTK_HetHang, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(lb_TTK_HangCon))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(lb_TTK_HetHang))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_TongQuanLayout = new javax.swing.GroupLayout(pn_TongQuan);
        pn_TongQuan.setLayout(pn_TongQuanLayout);
        pn_TongQuanLayout.setHorizontalGroup(
            pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_TongQuanLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_TongQuanLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_TongQuanLayout.createSequentialGroup()
                        .addGroup(pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PN_DoanhSo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_TongQuanLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(115, 115, 115))))
        );
        pn_TongQuanLayout.setVerticalGroup(
            pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_TongQuanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31)
                .addComponent(PN_DoanhSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(pn_TongQuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tp_QuanLy.addTab("Tổng quan", pn_TongQuan);

        tb_Sach.setAutoCreateRowSorter(true);
        tb_Sach.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_Sach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Số lượng", "Giá nhập", "Giá bán", "Nhóm sách", "Tình Trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Sach.setGridColor(new java.awt.Color(216, 216, 216));
        tb_Sach.setRowHeight(25);
        tb_Sach.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_Sach.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_Sach.setShowGrid(true);
        tb_Sach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SachMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_Sach);
        if (tb_Sach.getColumnModel().getColumnCount() > 0) {
            tb_Sach.getColumnModel().getColumn(0).setMinWidth(125);
            tb_Sach.getColumnModel().getColumn(0).setPreferredWidth(125);
            tb_Sach.getColumnModel().getColumn(0).setMaxWidth(125);
            tb_Sach.getColumnModel().getColumn(1).setMinWidth(250);
            tb_Sach.getColumnModel().getColumn(1).setPreferredWidth(250);
            tb_Sach.getColumnModel().getColumn(3).setMinWidth(100);
            tb_Sach.getColumnModel().getColumn(3).setPreferredWidth(100);
            tb_Sach.getColumnModel().getColumn(3).setMaxWidth(100);
            tb_Sach.getColumnModel().getColumn(5).setPreferredWidth(150);
            tb_Sach.getColumnModel().getColumn(6).setMinWidth(200);
            tb_Sach.getColumnModel().getColumn(6).setPreferredWidth(200);
            tb_Sach.getColumnModel().getColumn(6).setMaxWidth(200);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "THÊM SÁCH", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel1PropertyChange(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Tên sách");

        tf_Sach_TenSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Tác giả");

        tf_Sach_TacGia.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Giá nhập");

        tf_GiaNhap.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_GiaNhap.setText("0");
        tf_GiaNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_GiaNhapKeyTyped(evt);
            }
        });

        tf_GiaBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_GiaBan.setText("0");
        tf_GiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_GiaBanKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Giá Bán");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("Nhóm sách");

        cb_Sach_Them_NhomSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_Sach_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Sach_Them.setText("Thêm");
        btn_Sach_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sach_ThemActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Tình trạng");

        buttonGroup1.add(rb_Sach_BinhThuong);
        rb_Sach_BinhThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_Sach_BinhThuong.setSelected(true);
        rb_Sach_BinhThuong.setText("Bình thường");

        buttonGroup1.add(rb_Sach_NgungBan);
        rb_Sach_NgungBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_Sach_NgungBan.setText("Ngừng bán");

        btn_Sach_CapNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Sach_CapNhap.setText("Cập nhập");
        btn_Sach_CapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sach_CapNhapActionPerformed(evt);
            }
        });

        lb_Sach_New.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_Sach_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_20px.png"))); // NOI18N
        lb_Sach_New.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_Sach_NewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addGap(22, 22, 22)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(68, 68, 68)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(rb_Sach_BinhThuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(104, 104, 104)
                        .addComponent(rb_Sach_NgungBan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Sach_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Sach_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_Sach_Them_NhomSach, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_Sach_TacGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                    .addComponent(tf_Sach_TenSach, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(tf_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(51, 51, 51)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_Sach_New, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tf_Sach_TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_Sach_New, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Sach_TacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_GiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cb_Sach_Them_NhomSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rb_Sach_BinhThuong)
                        .addComponent(rb_Sach_NgungBan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Sach_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sach_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "TÌM KIẾM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Tên hoặc mã sách");

        tf_Sach_TenOrMa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Nhóm sách");

        cb_NhomSach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_Sach_Loc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Sach_Loc.setText("Lọc");
        btn_Sach_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sach_LocActionPerformed(evt);
            }
        });

        btn_Sach_BoLoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Sach_BoLoc.setText("Bỏ lọc");
        btn_Sach_BoLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Sach_BoLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Sach_TenOrMa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_NhomSach, 0, 434, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btn_Sach_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Sach_BoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Sach_TenOrMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_NhomSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Sach_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Sach_BoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_SachLayout = new javax.swing.GroupLayout(pn_Sach);
        pn_Sach.setLayout(pn_SachLayout);
        pn_SachLayout.setHorizontalGroup(
            pn_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pn_SachLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_SachLayout.setVerticalGroup(
            pn_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_SachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        tp_QuanLy.addTab("Sách", pn_Sach);

        tb_NhomSach.setAutoCreateRowSorter(true);
        tb_NhomSach.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_NhomSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhóm sách", "Tên nhóm sách", "Số lượng", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_NhomSach.setGridColor(new java.awt.Color(216, 216, 216));
        tb_NhomSach.setRowHeight(25);
        tb_NhomSach.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_NhomSach.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_NhomSach.setShowGrid(true);
        tb_NhomSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_NhomSachMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_NhomSach);
        if (tb_NhomSach.getColumnModel().getColumnCount() > 0) {
            tb_NhomSach.getColumnModel().getColumn(0).setMinWidth(125);
            tb_NhomSach.getColumnModel().getColumn(0).setPreferredWidth(125);
            tb_NhomSach.getColumnModel().getColumn(0).setMaxWidth(125);
            tb_NhomSach.getColumnModel().getColumn(1).setMinWidth(250);
            tb_NhomSach.getColumnModel().getColumn(1).setPreferredWidth(250);
            tb_NhomSach.getColumnModel().getColumn(2).setMinWidth(100);
            tb_NhomSach.getColumnModel().getColumn(2).setPreferredWidth(100);
            tb_NhomSach.getColumnModel().getColumn(2).setMaxWidth(100);
            tb_NhomSach.getColumnModel().getColumn(3).setMinWidth(200);
            tb_NhomSach.getColumnModel().getColumn(3).setPreferredWidth(200);
            tb_NhomSach.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setText("Thêm Nhóm Sách");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel52.setText("Tên nhóm sách");

        tf_NhomSach_Ten.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_NhomSach_CapNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NhomSach_CapNhap.setText("Cập nhập");
        btn_NhomSach_CapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhomSach_CapNhapActionPerformed(evt);
            }
        });

        btn_NhomSach_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NhomSach_Them.setText("Thêm");
        btn_NhomSach_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NhomSach_ThemActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel56.setText("Tình trạng");

        buttonGroup2.add(rb_NhomSach_BinhThuong);
        rb_NhomSach_BinhThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_NhomSach_BinhThuong.setSelected(true);
        rb_NhomSach_BinhThuong.setText("Bình thường");

        buttonGroup2.add(rb_NhomSach_NgungBan);
        rb_NhomSach_NgungBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_NhomSach_NgungBan.setText("Ngừng bán");

        lb_NV_New.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_NV_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_20px.png"))); // NOI18N
        lb_NV_New.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_NV_NewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                    .addComponent(rb_NhomSach_BinhThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rb_NhomSach_NgungBan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                    .addComponent(btn_NhomSach_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(btn_NhomSach_CapNhap)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(tf_NhomSach_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_NV_New, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_NhomSach_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_NV_New, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_NhomSach_BinhThuong)
                    .addComponent(rb_NhomSach_NgungBan))
                .addGap(31, 31, 31)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_NhomSach_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NhomSach_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_NhomSachLayout = new javax.swing.GroupLayout(pn_NhomSach);
        pn_NhomSach.setLayout(pn_NhomSachLayout);
        pn_NhomSachLayout.setHorizontalGroup(
            pn_NhomSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_NhomSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pn_NhomSachLayout.setVerticalGroup(
            pn_NhomSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_NhomSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_NhomSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
                .addContainerGap())
        );

        tp_QuanLy.addTab("Nhóm sách", pn_NhomSach);

        tb_HD_HD.setAutoCreateRowSorter(true);
        tb_HD_HD.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_HD_HD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn", "Ngày bán", "Nhân Viên Bán", "Khách hàng", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_HD_HD.setGridColor(new java.awt.Color(216, 216, 216));
        tb_HD_HD.setRowHeight(25);
        tb_HD_HD.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_HD_HD.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_HD_HD.setShowGrid(true);
        tb_HD_HD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_HD_HDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_HD_HD);
        if (tb_HD_HD.getColumnModel().getColumnCount() > 0) {
            tb_HD_HD.getColumnModel().getColumn(0).setMinWidth(75);
            tb_HD_HD.getColumnModel().getColumn(0).setPreferredWidth(75);
            tb_HD_HD.getColumnModel().getColumn(0).setMaxWidth(75);
            tb_HD_HD.getColumnModel().getColumn(1).setMinWidth(50);
            tb_HD_HD.getColumnModel().getColumn(2).setMinWidth(150);
            tb_HD_HD.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_HD_HD.getColumnModel().getColumn(2).setMaxWidth(150);
            tb_HD_HD.getColumnModel().getColumn(3).setMinWidth(150);
            tb_HD_HD.getColumnModel().getColumn(3).setPreferredWidth(150);
            tb_HD_HD.getColumnModel().getColumn(3).setMaxWidth(150);
            tb_HD_HD.getColumnModel().getColumn(4).setMinWidth(120);
            tb_HD_HD.getColumnModel().getColumn(4).setPreferredWidth(120);
            tb_HD_HD.getColumnModel().getColumn(4).setMaxWidth(120);
        }

        tb_HD_CTHD.setAutoCreateRowSorter(true);
        tb_HD_CTHD.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_HD_CTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng", "Giá bán", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_HD_CTHD.setGridColor(new java.awt.Color(216, 216, 216));
        tb_HD_CTHD.setRowHeight(25);
        tb_HD_CTHD.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_HD_CTHD.setShowGrid(true);
        jScrollPane2.setViewportView(tb_HD_CTHD);
        if (tb_HD_CTHD.getColumnModel().getColumnCount() > 0) {
            tb_HD_CTHD.getColumnModel().getColumn(0).setMinWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(0).setPreferredWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(0).setMaxWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_HD_CTHD.getColumnModel().getColumn(2).setMinWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(2).setPreferredWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(2).setMaxWidth(75);
            tb_HD_CTHD.getColumnModel().getColumn(3).setMinWidth(100);
            tb_HD_CTHD.getColumnModel().getColumn(3).setPreferredWidth(100);
            tb_HD_CTHD.getColumnModel().getColumn(3).setMaxWidth(100);
            tb_HD_CTHD.getColumnModel().getColumn(4).setMinWidth(100);
            tb_HD_CTHD.getColumnModel().getColumn(4).setPreferredWidth(100);
            tb_HD_CTHD.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Tìm kiếm");

        tf_HD_KhachHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Tên khách hàng");

        rb_HD_TheoNgay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rb_HD_TheoNgay.setText("Theo ngày");

        btn_HD_Loc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_HD_Loc.setText("Lọc");
        btn_HD_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HD_LocActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Tên nhân viên");

        cb_HD_NV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_HD_BoLoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_HD_BoLoc.setText("Bỏ lọc");
        btn_HD_BoLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HD_BoLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_HD_KhachHang)
                    .addComponent(date_HD_NgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_HD_NV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_HD_TheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btn_HD_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_HD_BoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                    .addComponent(date_HD_NgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_HD_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_HD_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(rb_HD_TheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(date_HD_NgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_HD_NgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_HD_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HD_BoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 332, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_HoaDonLayout = new javax.swing.GroupLayout(pn_HoaDon);
        pn_HoaDon.setLayout(pn_HoaDonLayout);
        pn_HoaDonLayout.setHorizontalGroup(
            pn_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_HoaDonLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_HoaDonLayout.setVerticalGroup(
            pn_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_HoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_HoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tp_QuanLy.addTab("Hoá đơn", pn_HoaDon);

        tb_PhieuNhap.setAutoCreateRowSorter(true);
        tb_PhieuNhap.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_PhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu", "Ngày Nhập", "Người nhập", "Nhà cung cấp", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_PhieuNhap.setGridColor(new java.awt.Color(216, 216, 216));
        tb_PhieuNhap.setRowHeight(25);
        tb_PhieuNhap.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_PhieuNhap.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_PhieuNhap.setShowGrid(true);
        tb_PhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_PhieuNhapMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_PhieuNhap);
        if (tb_PhieuNhap.getColumnModel().getColumnCount() > 0) {
            tb_PhieuNhap.getColumnModel().getColumn(0).setMinWidth(100);
            tb_PhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(100);
            tb_PhieuNhap.getColumnModel().getColumn(0).setMaxWidth(100);
            tb_PhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(100);
            tb_PhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_PhieuNhap.getColumnModel().getColumn(3).setPreferredWidth(200);
            tb_PhieuNhap.getColumnModel().getColumn(4).setMinWidth(120);
        }

        tb_CTPN.setAutoCreateRowSorter(true);
        tb_CTPN.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_CTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng", "Giá nhập", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_CTPN.setGridColor(new java.awt.Color(216, 216, 216));
        tb_CTPN.setRowHeight(25);
        tb_CTPN.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_CTPN.setShowGrid(true);
        jScrollPane5.setViewportView(tb_CTPN);
        if (tb_CTPN.getColumnModel().getColumnCount() > 0) {
            tb_CTPN.getColumnModel().getColumn(0).setMinWidth(75);
            tb_CTPN.getColumnModel().getColumn(0).setPreferredWidth(75);
            tb_CTPN.getColumnModel().getColumn(0).setMaxWidth(75);
            tb_CTPN.getColumnModel().getColumn(1).setMinWidth(200);
            tb_CTPN.getColumnModel().getColumn(1).setPreferredWidth(200);
            tb_CTPN.getColumnModel().getColumn(2).setMinWidth(75);
            tb_CTPN.getColumnModel().getColumn(2).setPreferredWidth(75);
            tb_CTPN.getColumnModel().getColumn(2).setMaxWidth(75);
            tb_CTPN.getColumnModel().getColumn(3).setMinWidth(100);
            tb_CTPN.getColumnModel().getColumn(3).setPreferredWidth(100);
            tb_CTPN.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Tìm kiếm");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Mã phiếu nhập");

        tf_PN_MaPN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Tên nhân viên");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Tên nhà cung cấp");

        cb_PN_NV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cb_PN_NCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        rb_PN_TheoNgay.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rb_PN_TheoNgay.setText("Theo ngày");

        btn_PN_BoLoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_PN_BoLoc.setText("Bỏ lọc");
        btn_PN_BoLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PN_BoLocActionPerformed(evt);
            }
        });

        btn_PN_Loc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_PN_Loc.setText("Lọc");
        btn_PN_Loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PN_LocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_PN_MaPN)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_PN_NV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_PN_NCC, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_PN_NgayBD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_PN_NgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_PN_TheoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 139, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(btn_PN_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_PN_BoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_PN_MaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_PN_NV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_PN_NCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rb_PN_TheoNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_PN_NgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_PN_NgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_PN_Loc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_PN_BoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_PhieuNhapLayout = new javax.swing.GroupLayout(pn_PhieuNhap);
        pn_PhieuNhap.setLayout(pn_PhieuNhapLayout);
        pn_PhieuNhapLayout.setHorizontalGroup(
            pn_PhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_PhieuNhapLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pn_PhieuNhapLayout.setVerticalGroup(
            pn_PhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_PhieuNhapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_PhieuNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tp_QuanLy.addTab("Phiếu nhập", pn_PhieuNhap);

        menubar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menubar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        mn_Pos.setText("POS");
        mn_Pos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_PosMouseClicked(evt);
            }
        });
        menubar.add(mn_Pos);

        mn_QuanLy.setText("QUẢN LÝ");
        mn_QuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QuanLyMouseClicked(evt);
            }
        });
        menubar.add(mn_QuanLy);

        mn_KhachHang.setText("KHÁCH HÀNG");
        mn_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_KhachHangMouseClicked(evt);
            }
        });
        menubar.add(mn_KhachHang);

        mn_NhanVien.setText("NHÂN VIÊN");
        mn_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_NhanVienMouseClicked(evt);
            }
        });
        menubar.add(mn_NhanVien);

        mn_HuongDan.setText("HƯỚNG DẪN");

        mni_About.setText("About");
        mni_About.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mni_AboutMouseClicked(evt);
            }
        });
        mni_About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_AboutActionPerformed(evt);
            }
        });
        mn_HuongDan.add(mni_About);

        menubar.add(mn_HuongDan);

        setJMenuBar(menubar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(tp_QuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(559, 559, 559))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp_QuanLy)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mn_PosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_PosMouseClicked
        BienToanCuc.getInstance().getF_Pos().setVisible(true);
    }//GEN-LAST:event_mn_PosMouseClicked

    private void mn_QuanLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QuanLyMouseClicked
        BienToanCuc.getInstance().getF_QuanLy().setVisible(true);
    }//GEN-LAST:event_mn_QuanLyMouseClicked

    private void mn_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_KhachHangMouseClicked
        BienToanCuc.getInstance().getF_KhachHang().setVisible(true);
    }//GEN-LAST:event_mn_KhachHangMouseClicked

    private void mn_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_NhanVienMouseClicked
        BienToanCuc.getInstance().getF_NhanVien().setVisible(true);
    }//GEN-LAST:event_mn_NhanVienMouseClicked

    private void mni_AboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mni_AboutMouseClicked
        BienToanCuc.getInstance().getF_About().setVisible(true);
    }//GEN-LAST:event_mni_AboutMouseClicked

    private void mni_AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_AboutActionPerformed
        BienToanCuc.getInstance().getF_About().setVisible(true);
    }//GEN-LAST:event_mni_AboutActionPerformed

    private void btn_PN_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PN_LocActionPerformed
        ArrayList<PhieuNhap> phieuNhaps = null;
        try{
            if (rb_PN_TheoNgay.isSelected()) {
                phieuNhaps = BPhieuNhap.getInstance().BoLoc(tf_PN_MaPN.getText().trim(),(String)cb_PN_NV.getSelectedItem(), (String)cb_PN_NCC.getSelectedItem(), date_PN_NgayBD.getDate(), date_PN_NgayKT.getDate());
            }
            else
            phieuNhaps = BPhieuNhap.getInstance().BoLoc(tf_PN_MaPN.getText().trim(),(String)cb_PN_NV.getSelectedItem(), (String)cb_PN_NCC.getSelectedItem(), null,null);

            ArrayToTablePN(phieuNhaps);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }

        DefaultTableModel mode = (DefaultTableModel)this.tb_CTPN.getModel();
        mode.setRowCount(0);
    }//GEN-LAST:event_btn_PN_LocActionPerformed

    private void btn_PN_BoLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PN_BoLocActionPerformed
        tf_PN_MaPN.setText(null);
        cb_PN_NV.setSelectedItem(null);
        cb_PN_NCC.setSelectedItem(null);
        rb_PN_TheoNgay.setSelected(false);
        date_PN_NgayBD.setDate(new Date());
        date_PN_NgayKT.setDate(new Date());

        LoadPhieuNhap();
        DefaultTableModel mode = (DefaultTableModel)this.tb_CTPN.getModel();
        mode.setRowCount(0);
    }//GEN-LAST:event_btn_PN_BoLocActionPerformed

    private void tb_PhieuNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_PhieuNhapMouseClicked
        try {
            // TODO add your handling code here:
            int index = tb_PhieuNhap.getSelectedRow();
            String maPhieuNhap = (String)tb_PhieuNhap.getModel().getValueAt(index,0);
            ArrayList<ChiTietPhieuNhap> chiTietPhieuNhaps = null;
            chiTietPhieuNhaps = BPhieuNhap.getInstance().getChiTietPhieuNhaps(maPhieuNhap);
            ArrayToTableCTPN(chiTietPhieuNhaps);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }

    }//GEN-LAST:event_tb_PhieuNhapMouseClicked

    private void btn_HD_BoLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HD_BoLocActionPerformed
        //Reset
        cb_HD_NV.setSelectedItem(null);
        tf_HD_KhachHang.setText(null);
        rb_HD_TheoNgay.setSelected(false);
        date_HD_NgayBD.setDate(new Date());
        date_HD_NgayKT.setDate(new Date());

        LoadHoaDon();
        DefaultTableModel mode = (DefaultTableModel)this.tb_HD_CTHD.getModel();
        mode.setRowCount(0);
    }//GEN-LAST:event_btn_HD_BoLocActionPerformed

    private void btn_HD_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HD_LocActionPerformed
        ArrayList<HoaDon> hoaDons = null;
        try{
            if (rb_HD_TheoNgay.isSelected()) {
                hoaDons = BHoaDon.getInstance().BoLoc((String)cb_HD_NV.getSelectedItem(), tf_HD_KhachHang.getText().trim(),date_HD_NgayBD.getDate() , date_HD_NgayKT.getDate());
            }
            else
            hoaDons = BHoaDon.getInstance().BoLoc((String)cb_HD_NV.getSelectedItem(), tf_HD_KhachHang.getText().trim(), null, null);

            ArrayToTableHD(hoaDons);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }

        DefaultTableModel mode = (DefaultTableModel)this.tb_HD_CTHD.getModel();
        mode.setRowCount(0);
    }//GEN-LAST:event_btn_HD_LocActionPerformed

    private void tb_HD_HDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HD_HDMouseClicked
        try {
            // TODO add your handling code here:
            int index = tb_HD_HD.getSelectedRow();
            String maHoaDon = (String)tb_HD_HD.getModel().getValueAt(index, 0);
            ArrayList<ChiTietHoaDon> chiTietHoaDons = null;
            chiTietHoaDons = BHoaDon.getInstance().getChiTietHoaDons(maHoaDon.trim());
            ArrayToTableCTHD(chiTietHoaDons);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_tb_HD_HDMouseClicked

    private void btn_Sach_LocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sach_LocActionPerformed
        try{
            ArrayList<Sach> sachs = null;
            sachs = BSach.getInstance().BoLoc(tf_Sach_TenOrMa.getText().trim(),(String)cb_NhomSach.getSelectedItem());
            ArrayToTableSach(sachs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_Sach_LocActionPerformed

    private void btn_Sach_BoLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sach_BoLocActionPerformed
        tf_Sach_TenOrMa.setText(null);
        cb_NhomSach.setSelectedItem(null);
        LoadSach();
    }//GEN-LAST:event_btn_Sach_BoLocActionPerformed

    private void tb_SachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SachMouseClicked
        int index = tb_Sach.getSelectedRow();
        DefaultTableModel mode =(DefaultTableModel) tb_Sach.getModel();
        tf_Sach_TenSach.setText((String) mode.getValueAt(index, 1));
        tf_Sach_TacGia.setText((String) mode.getValueAt(index, 2));
        tf_GiaNhap.setText(String.valueOf(mode.getValueAt(index, 4)));
        tf_GiaBan.setText(String.valueOf(mode.getValueAt(index, 5)));
        cb_Sach_Them_NhomSach.setSelectedItem((String) mode.getValueAt(index, 6));
        if (((String) mode.getValueAt(index, 7)).equals(rb_Sach_BinhThuong.getText()))
            rb_Sach_BinhThuong.setSelected(true);
        else
            rb_Sach_NgungBan.setSelected(true);
    }//GEN-LAST:event_tb_SachMouseClicked

    private void btn_Sach_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sach_ThemActionPerformed
        String thongBao = "";
        try{
            boolean flag = true;
            
            if (tf_Sach_TenSach.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên sách không được để trống";
                flag = false;
            }
            if (tf_Sach_TacGia.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên tác giả không được để trống";
                flag = false;
            }
            if (tf_GiaNhap.getText().trim().length() == 0) {
                thongBao = thongBao + "\nGiá nhập không được để trống";
                flag = false;
            }
            if (tf_GiaBan.getText().trim().length() == 0) {
                thongBao = thongBao + "\nGiá bán không được để trống";
                flag = false;
            }

            if (flag) {
                Sach sach = new Sach();
                sach.setTenSach(tf_Sach_TenSach.getText().trim());
                sach.setTacGia(tf_Sach_TacGia.getText().trim());
                sach.setGiaNhap(Double.valueOf(tf_GiaNhap.getText().trim()));
                sach.setGiaBan(Double.valueOf(tf_GiaBan.getText().trim()));
                sach.setNhomHang((String) cb_Sach_Them_NhomSach.getSelectedItem());
                sach.setTinhTrang(rb_Sach_BinhThuong.isSelected());
                
                if (BSach.getInstance().ThemSach(sach)) {
                    thongBao = "Thêm sách thành công";
                    LoadSach();
                    LoadNhomSach();
                }
                else
                    thongBao = "Thêm sách không thành công";
            }
        }catch(Exception e){
                thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_Sach_ThemActionPerformed

    private void btn_NhomSach_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhomSach_ThemActionPerformed
        String thongBao = "";
        try{
            boolean flag = true;
            if (tf_NhomSach_Ten.getText().trim().length() == 0) {
                thongBao = "\nTên nhóm không được để trống";
                flag = false;
            }else if(BNhomSach.getInstance().IsExistTenNS(tf_NhomSach_Ten.getText().trim())){
                thongBao = "\nTên nhóm đã tồn tại";
                flag = false;
            }

            if (flag) {
                NhomSach ns = new NhomSach();
                ns.setTenNhom(tf_NhomSach_Ten.getText().trim());
                ns.setTinhTrang(rb_NhomSach_BinhThuong.isSelected()?true:false);
                if (BNhomSach.getInstance().ThemNhomSach(ns)) {
                    thongBao = "Thêm nhóm sách thành công";
                    LoadNhomSach();
                    LoadCB_Sach_NhomSach();
                    LoadCB_Sach_ColumnNS();
                }
                else
                    thongBao = "Thêm không thành công";
            }
        }catch(Exception e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_NhomSach_ThemActionPerformed

    private void btn_NhomSach_CapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NhomSach_CapNhapActionPerformed
        String thongBao = "";
        int index = tb_NhomSach.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) tb_NhomSach.getModel();
        try{
            boolean flag = true;
            if (tf_NhomSach_Ten.getText().trim().length() == 0) {
                thongBao = "\nTên nhóm không được để trống";
                flag = false;
            }else {
                NhomSach temp = BNhomSach.getInstance().getNhomSachByTenNhom(tf_NhomSach_Ten.getText().trim());
                if(temp != null &&temp.getMaNhomSach().equals(mode.getValueAt(index, 1))){
                    thongBao = "\nTên nhóm đã tồn tại";
                    flag = false;
                    
                }
            }

            if (flag) {
                NhomSach ns = new NhomSach();
                ns.setMaNhomSach((String)mode.getValueAt(index, 0));
                ns.setTenNhom((String)mode.getValueAt(index, 1));
                ns.setTenNhom(tf_NhomSach_Ten.getText().trim());
                ns.setTinhTrang(rb_NhomSach_BinhThuong.isSelected()?true:false);
                if (BNhomSach.getInstance().UpdateNhomSach(ns)) {
                    thongBao = "Sửa thông tin thành công";
                    LoadNhomSach();
                    LoadCB_Sach_NhomSach();
                    LoadCB_Sach_ColumnNS();
                    LoadSach();
                }
                else
                    thongBao = "Sửa thông tin không thành công";
            }
        }catch(Exception e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_NhomSach_CapNhapActionPerformed

    private void tb_NhomSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NhomSachMouseClicked

        int index = tb_NhomSach.getSelectedRow();
        tf_NhomSach_Ten.setText((String) tb_NhomSach.getModel().getValueAt(index,1));
        if(((String)tb_NhomSach.getModel().getValueAt(index,3)).equals(rb_NhomSach_BinhThuong.getText()))
            rb_NhomSach_BinhThuong.setSelected(true);
        else
            rb_NhomSach_NgungBan.setSelected(true);
        
    }//GEN-LAST:event_tb_NhomSachMouseClicked

    private void btn_Sach_CapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Sach_CapNhapActionPerformed
        String thongBao = "";
        int index = tb_Sach.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) tb_Sach.getModel();
        if (index == -1) {
            return;
        }
        
        try{
            boolean flag = true;
            
            if (tf_Sach_TenSach.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên sách không được để trống";
                flag = false;
            }
            if (tf_Sach_TacGia.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên tác giả không được để trống";
                flag = false;
            }
            if (tf_GiaNhap.getText().trim().length() == 0) {
                thongBao = thongBao + "\nGiá nhập không được để trống";
                flag = false;
            }
            if (tf_GiaBan.getText().trim().length() == 0) {
                thongBao = thongBao + "\nGiá bán không được để trống";
                flag = false;
            }

            if (flag) {
                Sach sach = new Sach();
                sach.setMaSach((String)mode.getValueAt(index, 0));
                sach.setTenSach(tf_Sach_TenSach.getText().trim());
                sach.setTacGia(tf_Sach_TacGia.getText().trim());
                sach.setGiaNhap(Double.valueOf(tf_GiaNhap.getText().trim()));
                sach.setGiaBan(Double.valueOf(tf_GiaBan.getText().trim()));
                sach.setNhomHang((String) cb_Sach_Them_NhomSach.getSelectedItem());
                sach.setTinhTrang(rb_Sach_BinhThuong.isSelected());
                
                if (BSach.getInstance().UpdateSach(sach)) {
                    thongBao = "Sửa thông tin thành công";
                    LoadSach();
                    LoadNhomSach();
                    DefaultTableModel modeCTPN = (DefaultTableModel) tb_CTPN.getModel();
                    modeCTPN.setRowCount(0);
                    DefaultTableModel modeCTHD = (DefaultTableModel) tb_HD_CTHD.getModel();
                    modeCTPN.setRowCount(0);
                }
                else
                    thongBao = "Sửa thông tin không thành công";
            }
        }catch(Exception e){
                thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_Sach_CapNhapActionPerformed

    private void jPanel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1PropertyChange

    private void lb_NV_NewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NV_NewMouseClicked
        // TODO add your handling code here:
        tf_NhomSach_Ten.setText(null);
        rb_NhomSach_BinhThuong.setSelected(true);
        tf_NhomSach_Ten.requestFocusInWindow();
    }//GEN-LAST:event_lb_NV_NewMouseClicked

    private void lb_Sach_NewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_Sach_NewMouseClicked
        tf_Sach_TenSach.setText(null);
        tf_Sach_TacGia.setText(null);
        tf_GiaNhap.setText(String.valueOf(0));
        tf_GiaNhap.setText(String.valueOf(0));
        rb_Sach_BinhThuong.setSelected(true);
        tf_Sach_TenSach.requestFocusInWindow();
    }//GEN-LAST:event_lb_Sach_NewMouseClicked

    private void tf_GiaNhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_GiaNhapKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_tf_GiaNhapKeyTyped

    private void tf_GiaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_GiaBanKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_tf_GiaBanKeyTyped

    private void cb_TaiChinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TaiChinhActionPerformed
        double tienThuBanSach = 0;
        try {
            if (cb_TaiChinh.getSelectedIndex()==0) {  
                    tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(new Date(), new Date()); 
            }
            else if (cb_TaiChinh.getSelectedIndex()==1) { //Hom qua
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -1);
                tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(cal.getTime(),cal.getTime()); 
            }
            else if (cb_TaiChinh.getSelectedIndex()==2) { //7 ngay gan day
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -7);
                tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(cal.getTime(),new Date()); 
            }
            else if (cb_TaiChinh.getSelectedIndex()==3) { //30 ngay gan day
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -30);
                tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(cal.getTime(),new Date()); 
            }
            else if (cb_TaiChinh.getSelectedIndex()==4) { // Thang nay
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, 1);
                tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(cal.getTime(),new Date()); 
            }
            else if (cb_TaiChinh.getSelectedIndex()==5) { // Thang truoc
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                Date ngayCuoiThang = cal.getTime();
                cal.set(Calendar.DAY_OF_MONTH, 1);
                Date ngayDauThang = cal.getTime();
                tienThuBanSach = BQuanLy.getInstance().TinhDoanhThu(ngayDauThang,ngayCuoiThang); 
            }
        } catch (SQLException ex) {
                Logger.getLogger(F_QuanLy.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb_TC_TienThu.setText(String.valueOf(tienThuBanSach));
    }//GEN-LAST:event_cb_TaiChinhActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_QuanLy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_QuanLy().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PN_DoanhSo;
    private javax.swing.JToggleButton btn_HD_BoLoc;
    private javax.swing.JToggleButton btn_HD_Loc;
    private javax.swing.JToggleButton btn_NhomSach_CapNhap;
    private javax.swing.JToggleButton btn_NhomSach_Them;
    private javax.swing.JToggleButton btn_PN_BoLoc;
    private javax.swing.JToggleButton btn_PN_Loc;
    private javax.swing.JToggleButton btn_Sach_BoLoc;
    private javax.swing.JToggleButton btn_Sach_CapNhap;
    private javax.swing.JToggleButton btn_Sach_Loc;
    private javax.swing.JToggleButton btn_Sach_Them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cb_HD_NV;
    private javax.swing.JComboBox<String> cb_NhomSach;
    private javax.swing.JComboBox<String> cb_PN_NCC;
    private javax.swing.JComboBox<String> cb_PN_NV;
    private javax.swing.JComboBox<String> cb_Sach_Them_NhomSach;
    private javax.swing.JComboBox<String> cb_TaiChinh;
    private com.toedter.calendar.JDateChooser date_HD_NgayBD;
    private com.toedter.calendar.JDateChooser date_HD_NgayKT;
    private com.toedter.calendar.JDateChooser date_PN_NgayBD;
    private com.toedter.calendar.JDateChooser date_PN_NgayKT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_DHTB;
    private javax.swing.JLabel lb_DoanhSo;
    private javax.swing.JLabel lb_LaiGop;
    private javax.swing.JLabel lb_NV_New;
    private javax.swing.JLabel lb_Sach_New;
    private javax.swing.JLabel lb_SoDonHang;
    private javax.swing.JLabel lb_TC_LoiNhuan;
    private javax.swing.JLabel lb_TC_TienThu;
    private javax.swing.JLabel lb_TC_TienVon;
    private javax.swing.JLabel lb_TTK_HangCon;
    private javax.swing.JLabel lb_TTK_HetHang;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenu mn_HuongDan;
    private javax.swing.JMenu mn_KhachHang;
    private javax.swing.JMenu mn_NhanVien;
    private javax.swing.JMenu mn_Pos;
    private javax.swing.JMenu mn_QuanLy;
    private javax.swing.JMenuItem mni_About;
    private javax.swing.JPanel pn_HoaDon;
    private javax.swing.JPanel pn_NhomSach;
    private javax.swing.JPanel pn_PhieuNhap;
    private javax.swing.JPanel pn_Sach;
    private javax.swing.JPanel pn_TongQuan;
    private javax.swing.JRadioButton rb_HD_TheoNgay;
    private javax.swing.JRadioButton rb_NhomSach_BinhThuong;
    private javax.swing.JRadioButton rb_NhomSach_NgungBan;
    private javax.swing.JRadioButton rb_PN_TheoNgay;
    private javax.swing.JRadioButton rb_Sach_BinhThuong;
    private javax.swing.JRadioButton rb_Sach_NgungBan;
    private javax.swing.JTable tb_CTPN;
    private javax.swing.JTable tb_HD_CTHD;
    private javax.swing.JTable tb_HD_HD;
    private javax.swing.JTable tb_NhomSach;
    private javax.swing.JTable tb_PhieuNhap;
    private javax.swing.JTable tb_Sach;
    private javax.swing.JTextField tf_GiaBan;
    private javax.swing.JTextField tf_GiaNhap;
    private javax.swing.JTextField tf_HD_KhachHang;
    private javax.swing.JTextField tf_NhomSach_Ten;
    private javax.swing.JTextField tf_PN_MaPN;
    private javax.swing.JTextField tf_Sach_TacGia;
    private javax.swing.JTextField tf_Sach_TenOrMa;
    private javax.swing.JTextField tf_Sach_TenSach;
    private javax.swing.JTabbedPane tp_QuanLy;
    // End of variables declaration//GEN-END:variables

    
}
