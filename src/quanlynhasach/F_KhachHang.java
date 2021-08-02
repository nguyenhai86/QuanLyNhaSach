/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;


import BLL.BKhachHang;
import BLL.BNhaCungCap;
import BLL.BNhanVien;
import BLL.BNhomKhachHang;
import DTO.DateBox;
import DTO.KhachHang;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.NhomKhachHang;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class F_KhachHang extends javax.swing.JFrame {

    public F_KhachHang(){
        initComponents();
        
        Table_KhachHang_Component();
        Table_NhomKhachHang_Component();
        Table_NhaCungCap_Component();
    }
    
    public void Table_KhachHang_Component(){
        Table_Header_Component(tb_KhachHang);
        LoadKhachHang();
        LoadCbNhomKhachHang();
        lb_KH_NhanVien.setText(BienToanCuc.getInstance().getNguoiDangNhap().getTenNhanVien());
    }    
    public void Table_NhomKhachHang_Component(){
        Table_Header_Component(tb_NKH);
        LoadNhomKhachHang();
        lb_NKH_NhanVien.setText(BienToanCuc.getInstance().getNguoiDangNhap().getTenNhanVien());
    } 
    public void Table_NhaCungCap_Component(){
        Table_Header_Component(tb_NCC);
        LoadNhaCungCap();
        lb_NCC_NhanVien.setText(BienToanCuc.getInstance().getNguoiDangNhap().getTenNhanVien());
    }
    public void Table_Header_Component(JTable table){
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD,15));
        table.getTableHeader().setOpaque(false);
    }
    
    public void ArrayListToTableKH(ArrayList<KhachHang> khachHangs){
        if (khachHangs != null) {
            
            DefaultTableModel mode = (DefaultTableModel)this.tb_KhachHang.getModel();
            mode.setRowCount(0);   
            for(KhachHang kh:khachHangs){     
                mode.addRow(new Object[]{kh.getMaKhachHang(),kh.getTenKhachHang(),kh.getDienThoai(),kh.getNhomKhachHang(),DateBox.ConvertToString(kh.getLanCuoiMuaHang()),kh.getTongTien(),kh.getTinhTrang()?rb_KH_BinhThuong.getText():rb_KH_NgungBan.getText()});
            }
        }
    }
    public void ArrayListToTableNKH(ArrayList<NhomKhachHang> nhomKhachHangs){
        if (nhomKhachHangs != null) {
            DefaultTableModel mode = (DefaultTableModel)this.tb_NKH.getModel();
            mode.setRowCount(0);
            for(NhomKhachHang nkh:nhomKhachHangs){
                mode.addRow(new Object[]{nkh.getMaNhom(), nkh.getTenNhom(),nkh.getGhiChu()});
            }
        }
    }
    public void ArrayListToTableNCC(ArrayList<NhaCungCap> nhaCungCaps){
        if (nhaCungCaps != null) {
            DefaultTableModel mode = (DefaultTableModel)this.tb_NCC.getModel();
            mode.setRowCount(0);
            for(NhaCungCap ncc:nhaCungCaps){
                mode.addRow(new Object[]{ncc.getMaNhaCC(),ncc.getTenNhaCC(),ncc.getDienThoai(),ncc.getTinhTrang()?rb_NCC_BinhThuong.getText():rb_NCC_NgungLaySach.getText()});
            }
        }
    }
    
    public void LoadKhachHang(){
        ArrayList<KhachHang> khachHangs = null;
        try{
            khachHangs = BKhachHang.getInstance().getKhachHangs();
            ArrayListToTableKH(khachHangs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + e.getMessage());
        }
        
    }
    public void LoadNhomKhachHang(){
        ArrayList<NhomKhachHang> nhomKhachHangs = null;
        try{
            nhomKhachHangs = BNhomKhachHang.getInstance().getNhomKhachHangs();
            ArrayListToTableNKH(nhomKhachHangs);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + e.getMessage());
        }
    }
    public void LoadNhaCungCap(){
        ArrayList<NhaCungCap> nhaCungCaps = null;
        try{
            nhaCungCaps = BNhaCungCap.getInstance().getNhaCungCaps();
            ArrayListToTableNCC(nhaCungCaps);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + e.getMessage());
        }
        
    }
    public void LoadCbNhomKhachHang(){
        try {
            ArrayList<NhomKhachHang> nhomKhachHangs = null;
            nhomKhachHangs = BNhomKhachHang.getInstance().getNhomKhachHangs();
            
            if(nhomKhachHangs != null){
                cb_KH_NKH.removeAllItems();
                for (NhomKhachHang item:nhomKhachHangs) {
                    cb_KH_NKH.addItem(item.getTenNhom());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + ex.getMessage());
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
        btnGroup_KH_TinhTrang = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tp_KhachHang = new javax.swing.JTabbedPane();
        pn_NhomKhachHang = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tb_NKH = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        tf_NKH_Search = new javax.swing.JTextField();
        btn_NKH_Search = new javax.swing.JToggleButton();
        lb_NKH_NhanVien = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        tf_NKH_TenNhom = new javax.swing.JTextField();
        tf_NKH_ChiChu = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        btn_NKH_Them = new javax.swing.JToggleButton();
        btn_NKH_CapNhap = new javax.swing.JToggleButton();
        lb_NKH_New = new javax.swing.JLabel();
        pn_KhachHang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_KhachHang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        tf_KH_Search = new javax.swing.JTextField();
        btn_KH_Search = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        lb_TenNhanVien = new javax.swing.JLabel();
        lb_KH_NhanVien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        tf_KH_TenKH = new javax.swing.JTextField();
        tf_KH_DienThoai = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btn_KH_Them = new javax.swing.JToggleButton();
        btn_KH_CapNhap = new javax.swing.JToggleButton();
        jLabel37 = new javax.swing.JLabel();
        rb_KH_BinhThuong = new javax.swing.JRadioButton();
        rb_KH_NgungBan = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        cb_KH_NKH = new javax.swing.JComboBox<>();
        lb_KH_New = new javax.swing.JLabel();
        pn_NhaCungCap = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        tf_NCC_Search = new javax.swing.JTextField();
        btn_NCC_Search = new javax.swing.JToggleButton();
        lb_NCC_NhanVien = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_NCC = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        tf_NCC_TenNCC = new javax.swing.JTextField();
        btn_NCC_Them = new javax.swing.JToggleButton();
        btn_NCC_CapNhap = new javax.swing.JToggleButton();
        jLabel36 = new javax.swing.JLabel();
        tf_NCC_DienThoai = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        rb_NCC_BinhThuong = new javax.swing.JRadioButton();
        rb_NCC_NgungLaySach = new javax.swing.JRadioButton();
        lb_NCC_New = new javax.swing.JLabel();
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
        setTitle("Khách Hàng");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setIconImages(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tp_KhachHang.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        tp_KhachHang.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tp_KhachHang.setToolTipText("");
        tp_KhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tp_KhachHang.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        tb_NKH.setAutoCreateRowSorter(true);
        tb_NKH.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_NKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhóm", "Tên nhóm", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_NKH.setGridColor(new java.awt.Color(216, 216, 216));
        tb_NKH.setRowHeight(25);
        tb_NKH.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_NKH.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_NKH.setShowGrid(true);
        tb_NKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_NKHMouseClicked(evt);
            }
        });
        tb_NKH.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                tb_NKHComponentResized(evt);
            }
        });
        jScrollPane6.setViewportView(tb_NKH);
        if (tb_NKH.getColumnModel().getColumnCount() > 0) {
            tb_NKH.getColumnModel().getColumn(0).setMinWidth(150);
            tb_NKH.getColumnModel().getColumn(0).setPreferredWidth(150);
            tb_NKH.getColumnModel().getColumn(0).setMaxWidth(150);
            tb_NKH.getColumnModel().getColumn(1).setResizable(false);
            tb_NKH.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jPanel10.setBackground(new java.awt.Color(39, 117, 239));
        jPanel10.setForeground(new java.awt.Color(0, 39, 188));

        tf_NKH_Search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tf_NKH_Search.setForeground(new java.awt.Color(128, 128, 128));
        tf_NKH_Search.setText("Nhập mã hoặc tên nhóm để tìm kiếm");
        tf_NKH_Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_NKH_SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_NKH_SearchFocusLost(evt);
            }
        });
        tf_NKH_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_NKH_SearchMouseClicked(evt);
            }
        });
        tf_NKH_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_NKH_SearchActionPerformed(evt);
            }
        });

        btn_NKH_Search.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NKH_Search.setText("Tìm");
        btn_NKH_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NKH_SearchActionPerformed(evt);
            }
        });

        lb_NKH_NhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lb_NKH_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_NKH_NhanVien.setText("Nguyễn Duy Hải");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_NKH_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_NKH_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
                .addComponent(lb_NKH_NhanVien)
                .addGap(47, 47, 47))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lb_NKH_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_NKH_Search)
                    .addComponent(btn_NKH_Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Tên nhóm");

        tf_NKH_TenNhom.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tf_NKH_ChiChu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Ghi chú");

        btn_NKH_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NKH_Them.setText("Thêm");
        btn_NKH_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NKH_ThemActionPerformed(evt);
            }
        });

        btn_NKH_CapNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NKH_CapNhap.setText("Cập nhập");
        btn_NKH_CapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NKH_CapNhapActionPerformed(evt);
            }
        });

        lb_NKH_New.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_NKH_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_20px.png"))); // NOI18N
        lb_NKH_New.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_NKH_NewMouseClicked(evt);
            }
        });
        lb_NKH_New.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lb_NKH_NewKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 162, Short.MAX_VALUE)
                        .addComponent(btn_NKH_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_NKH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_NKH_ChiChu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(tf_NKH_TenNhom)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_NKH_New, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(tf_NKH_TenNhom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_NKH_New, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tf_NKH_ChiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_NKH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NKH_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pn_NhomKhachHangLayout = new javax.swing.GroupLayout(pn_NhomKhachHang);
        pn_NhomKhachHang.setLayout(pn_NhomKhachHangLayout);
        pn_NhomKhachHangLayout.setHorizontalGroup(
            pn_NhomKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_NhomKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_NhomKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_NhomKhachHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_NhomKhachHangLayout.setVerticalGroup(
            pn_NhomKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_NhomKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_NhomKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tp_KhachHang.addTab("Nhóm khách hàng", pn_NhomKhachHang);

        tb_KhachHang.setAutoCreateRowSorter(true);
        tb_KhachHang.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Điện thoại", "Nhóm khách hàng", "Lần cuối mua hàng", "Tổng tiền", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_KhachHang.setGridColor(new java.awt.Color(216, 216, 216));
        tb_KhachHang.setRowHeight(25);
        tb_KhachHang.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_KhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_KhachHang.setShowGrid(true);
        tb_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_KhachHang);
        if (tb_KhachHang.getColumnModel().getColumnCount() > 0) {
            tb_KhachHang.getColumnModel().getColumn(0).setMinWidth(110);
            tb_KhachHang.getColumnModel().getColumn(0).setPreferredWidth(110);
            tb_KhachHang.getColumnModel().getColumn(0).setMaxWidth(110);
            tb_KhachHang.getColumnModel().getColumn(1).setMinWidth(160);
            tb_KhachHang.getColumnModel().getColumn(1).setPreferredWidth(160);
            tb_KhachHang.getColumnModel().getColumn(1).setMaxWidth(160);
            tb_KhachHang.getColumnModel().getColumn(2).setMinWidth(100);
            tb_KhachHang.getColumnModel().getColumn(2).setPreferredWidth(100);
            tb_KhachHang.getColumnModel().getColumn(2).setMaxWidth(100);
            tb_KhachHang.getColumnModel().getColumn(3).setMinWidth(140);
            tb_KhachHang.getColumnModel().getColumn(3).setPreferredWidth(140);
            tb_KhachHang.getColumnModel().getColumn(3).setMaxWidth(140);
            tb_KhachHang.getColumnModel().getColumn(4).setMinWidth(140);
            tb_KhachHang.getColumnModel().getColumn(4).setPreferredWidth(140);
            tb_KhachHang.getColumnModel().getColumn(4).setMaxWidth(140);
            tb_KhachHang.getColumnModel().getColumn(5).setMinWidth(120);
            tb_KhachHang.getColumnModel().getColumn(5).setPreferredWidth(120);
            tb_KhachHang.getColumnModel().getColumn(5).setMaxWidth(200);
            tb_KhachHang.getColumnModel().getColumn(6).setMinWidth(125);
            tb_KhachHang.getColumnModel().getColumn(6).setPreferredWidth(125);
            tb_KhachHang.getColumnModel().getColumn(6).setMaxWidth(125);
        }

        jPanel4.setBackground(new java.awt.Color(39, 117, 239));
        jPanel4.setForeground(new java.awt.Color(0, 39, 188));

        tf_KH_Search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tf_KH_Search.setForeground(new java.awt.Color(128, 128, 128));
        tf_KH_Search.setText("Nhập mã khách hàng, tên khách hàng hoặc số điện thoại");
        tf_KH_Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_KH_SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_KH_SearchFocusLost(evt);
            }
        });
        tf_KH_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_KH_SearchMouseClicked(evt);
            }
        });
        tf_KH_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_KH_SearchActionPerformed(evt);
            }
        });

        btn_KH_Search.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_KH_Search.setText("Tìm");
        btn_KH_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_KH_SearchMouseClicked(evt);
            }
        });
        btn_KH_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KH_SearchActionPerformed(evt);
            }
        });

        lb_TenNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lb_TenNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_TenNhanVien.setText("Nguyễn Duy Hải");

        lb_KH_NhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lb_KH_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_KH_NhanVien.setText("Nguyễn Duy Hải");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_KH_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_KH_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_KH_NhanVien, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(55, 55, 55))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(585, 585, 585)
                    .addComponent(lb_TenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(586, 586, 586)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_KH_NhanVien)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_KH_Search)
                            .addComponent(btn_KH_Search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(lb_TenNhanVien)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Tên khách hàng:");

        tf_KH_TenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tf_KH_DienThoai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Điện thoại");

        btn_KH_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_KH_Them.setText("Thêm");
        btn_KH_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KH_ThemActionPerformed(evt);
            }
        });

        btn_KH_CapNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_KH_CapNhap.setText("Cập nhập");
        btn_KH_CapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KH_CapNhapActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setText("Tình trạng");

        btnGroup_KH_TinhTrang.add(rb_KH_BinhThuong);
        rb_KH_BinhThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_KH_BinhThuong.setSelected(true);
        rb_KH_BinhThuong.setText("Bình Thường");

        btnGroup_KH_TinhTrang.add(rb_KH_NgungBan);
        rb_KH_NgungBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_KH_NgungBan.setText("Ngừng bán");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Nhóm khách hàng");

        cb_KH_NKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_KH_NKHActionPerformed(evt);
            }
        });

        lb_KH_New.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_KH_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_20px.png"))); // NOI18N
        lb_KH_New.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_KH_NewMouseClicked(evt);
            }
        });
        lb_KH_New.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lb_KH_NewKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rb_KH_BinhThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_KH_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_KH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tf_KH_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_KH_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_KH_NKH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rb_KH_NgungBan))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_KH_New, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(tf_KH_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_KH_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(cb_KH_NKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_KH_BinhThuong)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb_KH_NgungBan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_KH_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_KH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lb_KH_New, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_KhachHangLayout = new javax.swing.GroupLayout(pn_KhachHang);
        pn_KhachHang.setLayout(pn_KhachHangLayout);
        pn_KhachHangLayout.setHorizontalGroup(
            pn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_KhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_KhachHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_KhachHangLayout.setVerticalGroup(
            pn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_KhachHangLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addGroup(pn_KhachHangLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tp_KhachHang.addTab("Khách Hàng", pn_KhachHang);

        jPanel12.setBackground(new java.awt.Color(39, 117, 239));
        jPanel12.setForeground(new java.awt.Color(0, 39, 188));

        tf_NCC_Search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tf_NCC_Search.setForeground(new java.awt.Color(128, 128, 128));
        tf_NCC_Search.setText("Nhập mã nhà cung cấp, tên nhà cung cấp hoặc số điện thoại");
        tf_NCC_Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_NCC_SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_NCC_SearchFocusLost(evt);
            }
        });
        tf_NCC_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_NCC_SearchMouseClicked(evt);
            }
        });
        tf_NCC_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_NCC_SearchActionPerformed(evt);
            }
        });

        btn_NCC_Search.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NCC_Search.setText("Tìm");
        btn_NCC_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NCC_SearchActionPerformed(evt);
            }
        });

        lb_NCC_NhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lb_NCC_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lb_NCC_NhanVien.setText("Nguyễn Duy Hải");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_NCC_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_NCC_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addComponent(lb_NCC_NhanVien)
                .addGap(44, 44, 44))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lb_NCC_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tf_NCC_Search)
                    .addComponent(btn_NCC_Search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tb_NCC.setAutoCreateRowSorter(true);
        tb_NCC.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tb_NCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Điện thoại", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_NCC.setGridColor(new java.awt.Color(216, 216, 216));
        tb_NCC.setRowHeight(25);
        tb_NCC.setSelectionBackground(new java.awt.Color(23, 130, 208));
        tb_NCC.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_NCC.setShowGrid(true);
        tb_NCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_NCCMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tb_NCC);
        if (tb_NCC.getColumnModel().getColumnCount() > 0) {
            tb_NCC.getColumnModel().getColumn(0).setMinWidth(150);
            tb_NCC.getColumnModel().getColumn(0).setPreferredWidth(150);
            tb_NCC.getColumnModel().getColumn(0).setMaxWidth(150);
            tb_NCC.getColumnModel().getColumn(1).setMinWidth(300);
            tb_NCC.getColumnModel().getColumn(1).setPreferredWidth(200);
            tb_NCC.getColumnModel().getColumn(2).setMinWidth(150);
            tb_NCC.getColumnModel().getColumn(2).setPreferredWidth(150);
            tb_NCC.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Tên nhà cung cấp:");

        tf_NCC_TenNCC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_NCC_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NCC_Them.setText("Thêm");
        btn_NCC_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NCC_ThemActionPerformed(evt);
            }
        });

        btn_NCC_CapNhap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_NCC_CapNhap.setText("Cập nhập");
        btn_NCC_CapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NCC_CapNhapActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Điện thoại:");

        tf_NCC_DienThoai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setText("Tình trạng");

        buttonGroup1.add(rb_NCC_BinhThuong);
        rb_NCC_BinhThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_NCC_BinhThuong.setSelected(true);
        rb_NCC_BinhThuong.setText("Bình Thường");

        buttonGroup1.add(rb_NCC_NgungLaySach);
        rb_NCC_NgungLaySach.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_NCC_NgungLaySach.setText("Ngừng lấy hàng");

        lb_NCC_New.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_NCC_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_plus_20px.png"))); // NOI18N
        lb_NCC_New.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_NCC_NewMouseClicked(evt);
            }
        });
        lb_NCC_New.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lb_NCC_NewKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(tf_NCC_TenNCC))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(btn_NCC_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_NCC_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(rb_NCC_BinhThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb_NCC_NgungLaySach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tf_NCC_DienThoai))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_NCC_New, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(tf_NCC_TenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_NCC_New, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(tf_NCC_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_NCC_BinhThuong)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_NCC_NgungLaySach))
                .addGap(38, 38, 38)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_NCC_CapNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NCC_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pn_NhaCungCapLayout = new javax.swing.GroupLayout(pn_NhaCungCap);
        pn_NhaCungCap.setLayout(pn_NhaCungCapLayout);
        pn_NhaCungCapLayout.setHorizontalGroup(
            pn_NhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_NhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_NhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_NhaCungCapLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_NhaCungCapLayout.setVerticalGroup(
            pn_NhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_NhaCungCapLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_NhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tp_KhachHang.addTab("Nhà cung cấp", pn_NhaCungCap);

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
                .addComponent(tp_KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 1442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(559, 559, 559))
            .addComponent(tp_KhachHang, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_NKH_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_NKH_SearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NKH_SearchMouseClicked

    private void tf_NKH_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_NKH_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NKH_SearchActionPerformed

    private void tf_NCC_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_NCC_SearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NCC_SearchMouseClicked

    private void tf_NCC_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_NCC_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_NCC_SearchActionPerformed

    private void cb_KH_NKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_KH_NKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_KH_NKHActionPerformed

    private void btn_KH_CapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KH_CapNhapActionPerformed
        String thongBao = "";
        int index = tb_KhachHang.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) tb_KhachHang.getModel();
        if (index == -1) {
            return;
        }
        
        try{
            boolean flag = true;
            if (tf_KH_TenKH.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên khách hàng không được để trống";
                flag = false;
            }
            if (tf_KH_DienThoai.getText().trim().length() == 0) {
                thongBao = thongBao + "\nSố điện thoại không được để trống";
                flag = false;
            }
            else{
                KhachHang temp = BKhachHang.getInstance().getKhachHangBySDT(tf_KH_DienThoai.getText().trim());
                if (temp != null && !temp.getMaKhachHang().equals((String)mode.getValueAt(index, 0))) {
                    thongBao = thongBao + "\nSố điện thoại đã được đăng ký bởi khách hàng " + "\t"+ temp.getMaKhachHang() + "\t" + temp.getTenKhachHang();
                    flag = false;
                }
            }

            if (flag) {
                KhachHang khachHang = new KhachHang();
                
                khachHang.setMaKhachHang((String) mode.getValueAt(index, 0));
                khachHang.setTenKhachHang(tf_KH_TenKH.getText().trim());
                khachHang.setDienThoai(tf_KH_DienThoai.getText().trim());
                khachHang.setNhomKhachHang((String) cb_KH_NKH.getSelectedItem());
                khachHang.setTinhTrang(rb_KH_BinhThuong.isSelected()?true:false);

                if (BKhachHang.getInstance().UpdateKhachHang(khachHang)) {
                    thongBao = "Sửa thông tin thành công";
                    LoadKhachHang();
                }
                else{
                    thongBao = "Sửa thông tin không thành công";
                }
            }
        }catch(Exception e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_KH_CapNhapActionPerformed

    private void btn_NKH_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NKH_ThemActionPerformed
        String thongBao = "";
        try {
            boolean flag = true;
            if (tf_NKH_TenNhom.getText().trim().length() == 0){
                thongBao = thongBao + "\nTên nhóm không được để trống";
                flag = false;
            }
            else if(BNhomKhachHang.getInstance().IsExistNKH(tf_NKH_TenNhom.getText().trim())){
                thongBao = thongBao + "\nTên nhóm đã tồn tại";
                flag = false;
            }

            NhomKhachHang nkh = new NhomKhachHang();
            if (flag != false) {
                
                    nkh.setTenNhom(tf_NKH_TenNhom.getText().trim());
                    nkh.setGhiChu(tf_NKH_ChiChu.getText().trim());

                    if (BNhomKhachHang.getInstance().ThemNhomKhachHang(nkh)) {
                        thongBao = "\nThêm nhóm thành công";
                        LoadNhomKhachHang();
                        LoadCbNhomKhachHang();
                    }
                    else
                        thongBao = "\nThêm nhóm không thành công";
            }
        }catch (SQLException ex) {
           thongBao = "ERROR: " +  ex.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);      
    }//GEN-LAST:event_btn_NKH_ThemActionPerformed

    private void btn_NKH_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NKH_SearchActionPerformed
        if (tf_NKH_Search.getText().trim().length() == 0 || tf_NKH_Search.getText().equals("Nhập mã hoặc tên nhóm để tìm kiếm")){
            LoadNhomKhachHang();
            return;
        }
        
        ArrayList<NhomKhachHang> nhomKhachHangs = null;
        try{
            nhomKhachHangs = BNhomKhachHang.getInstance().searchNKH(tf_NKH_Search.getText().trim());
            ArrayListToTableNKH(nhomKhachHangs);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage() );
        }
    }//GEN-LAST:event_btn_NKH_SearchActionPerformed

    private void tf_NKH_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NKH_SearchFocusGained
        // TODO add your handling code here:
        if (tf_NKH_Search.getText().equals("Nhập mã hoặc tên nhóm để tìm kiếm")) {
            tf_NKH_Search.setText(null);
            tf_NKH_Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tf_NKH_SearchFocusGained

    private void tf_NKH_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NKH_SearchFocusLost
        // TODO add your handling code here:
        if (tf_NKH_Search.getText().trim().length() == 0) {
            tf_NKH_Search.setText("Nhập mã hoặc tên nhóm để tìm kiếm");
            tf_NKH_Search.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tf_NKH_SearchFocusLost

    private void btn_NKH_CapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NKH_CapNhapActionPerformed
        int index = tb_NKH.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) tb_NKH.getModel();
        if (index == -1)
            return;
        
        String thongBao = "";
        try {
            boolean flag = true;
            if (tf_NKH_TenNhom.getText().trim().length() == 0){
                thongBao = thongBao + "\nTên nhóm không được để trống";
                flag = false;
            }
            else{
                //KhachHang temp = BKhachHang.getInstance().getKhachHangByMa(thongBao)
                thongBao = thongBao + "\nTên nhóm đã tồn tại";
                flag = false;
            }

            NhomKhachHang nkh = new NhomKhachHang();
            if (flag != false) {
                    nkh.setMaNhom((String)mode.getValueAt(index, 0));
                    nkh.setTenNhom(tf_NKH_TenNhom.getText().trim());
                    nkh.setGhiChu(tf_NKH_ChiChu.getText().trim());

                    if (BNhomKhachHang.getInstance().UpdateNhomKhachHang(nkh)) {
                        thongBao = "\nSửa thông tin thành công";
                        LoadNhomKhachHang();
                        LoadCbNhomKhachHang(); 
                        LoadKhachHang();
                    }
                    else
                        thongBao = "\nSửa thông tin không thành công";
            }
        }catch (SQLException ex) {
           thongBao = "ERROR: " +  ex.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
        
    }//GEN-LAST:event_btn_NKH_CapNhapActionPerformed

    private void tf_KH_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_KH_SearchActionPerformed

    }//GEN-LAST:event_tf_KH_SearchActionPerformed

    private void tf_KH_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_KH_SearchMouseClicked
        // TODO add your handling code here:
        if(tf_KH_Search.getText().equals("Nhập tên hoặc mã hàng hoá"))
        tf_KH_Search.setText(null);
    }//GEN-LAST:event_tf_KH_SearchMouseClicked

    private void btn_KH_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KH_SearchActionPerformed
        if (tf_KH_Search.getText().trim().length() == 0 || tf_KH_Search.getText().equals("Nhập mã khách hàng, tên khách hàng hoặc số điện thoại")){
            LoadKhachHang();
            return;
        }
        
        ArrayList<KhachHang> khachHangs = null;
        try{
            khachHangs = BKhachHang.getInstance().searchKH(tf_KH_Search.getText().trim());
            ArrayListToTableKH(khachHangs);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage() );
        }
    }//GEN-LAST:event_btn_KH_SearchActionPerformed

    private void btn_KH_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_KH_SearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_KH_SearchMouseClicked

    private void btn_KH_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KH_ThemActionPerformed
        String thongBao = "";
        try{
            boolean flag = true;
            if (tf_KH_TenKH.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên khách hàng không được để trống";
                flag = false;
            }
            if (tf_KH_DienThoai.getText().trim().length() == 0) {
                thongBao = thongBao + "\nSố điện thoại không được để trống";
                flag = false;
            }
            else{
                KhachHang temp = BKhachHang.getInstance().getKhachHangBySDT(tf_KH_DienThoai.getText().trim());
                if (temp != null) {
                    thongBao = thongBao + "\nSố điệnh thoại đã được đăng ký bởi khách hàng " + "\t"+ temp.getMaKhachHang() + "\t" + temp.getTenKhachHang();
                    flag = false;
                }
            }

            if (flag) {
                KhachHang khachHang = new KhachHang();
                khachHang.setTenKhachHang(tf_KH_TenKH.getText().trim());
                khachHang.setDienThoai(tf_KH_DienThoai.getText().trim());
                khachHang.setNhomKhachHang((String) cb_KH_NKH.getSelectedItem());
                khachHang.setTinhTrang(rb_KH_BinhThuong.isSelected()?true:false);

                if (BKhachHang.getInstance().ThemKhachHang(khachHang)) {
                    thongBao = "Thêm khách hàng thành công";
                    LoadKhachHang();
                }
                else{
                    thongBao = "Thêm khách hàng không thành công";
                }
            }
        }catch(Exception e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_KH_ThemActionPerformed

    private void tb_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_KhachHangMouseClicked
        // TODO add your handling code here:
        int indexSelectRow = tb_KhachHang.getSelectedRow();
        
        DefaultTableModel mode = (DefaultTableModel)this.tb_KhachHang.getModel();

        tf_KH_TenKH.setText((String)mode.getValueAt(indexSelectRow,1));
        tf_KH_DienThoai.setText((String)mode.getValueAt(indexSelectRow,2));
        cb_KH_NKH.setSelectedItem((String)mode.getValueAt(indexSelectRow,3));
        if (((String)mode.getValueAt(indexSelectRow,6)).equals(rb_KH_BinhThuong.getText()))
            rb_KH_BinhThuong.setSelected(true);
        else
            rb_KH_NgungBan.setSelected(true);
        
    }//GEN-LAST:event_tb_KhachHangMouseClicked

    private void tf_KH_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_KH_SearchFocusGained
        if (tf_KH_Search.getText().equals("Nhập mã khách hàng, tên khách hàng hoặc số điện thoại")) {
            tf_KH_Search.setText(null);
            tf_KH_Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tf_KH_SearchFocusGained

    private void tf_KH_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_KH_SearchFocusLost
        if (tf_KH_Search.getText().trim().length() == 0) {
            tf_KH_Search.setText("Nhập mã khách hàng, tên khách hàng hoặc số điện thoại");
            tf_KH_Search.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tf_KH_SearchFocusLost

    private void tf_NCC_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NCC_SearchFocusGained
        if (tf_NCC_Search.getText().equals("Nhập mã nhà cung cấp, tên nhà cung cấp hoặc số điện thoại")) {
            tf_NCC_Search.setText(null);
            tf_NCC_Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tf_NCC_SearchFocusGained

    private void tf_NCC_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NCC_SearchFocusLost
        if (tf_NCC_Search.getText().trim().length() == 0) {
            tf_NCC_Search.setText("Nhập mã nhà cung cấp, tên nhà cung cấp hoặc số điện thoại");
            tf_NCC_Search.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_tf_NCC_SearchFocusLost

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

    private void btn_NCC_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NCC_SearchActionPerformed
        if (tf_NCC_Search.getText().trim().length() == 0 || tf_NCC_Search.getText().equals("Nhập mã nhà cung cấp, tên nhà cung cấp hoặc số điện thoại")){
            LoadNhaCungCap();
            return;
        }
        
        ArrayList<NhaCungCap> nhaCungCaps = null;
        try{
            nhaCungCaps = BNhaCungCap.getInstance().searchNCC(tf_NCC_Search.getText().trim());
            ArrayListToTableNCC(nhaCungCaps);
        }catch(Exception ex){
             JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage() );
        }
    }//GEN-LAST:event_btn_NCC_SearchActionPerformed

    private void tb_NKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NKHMouseClicked
        int indexSelectRow = this.tb_NKH.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) this.tb_NKH.getModel();
        
        tf_NKH_TenNhom.setText((String) mode.getValueAt(indexSelectRow, 1));
        tf_NKH_ChiChu.setText((String) mode.getValueAt(indexSelectRow, 2));
        
    }//GEN-LAST:event_tb_NKHMouseClicked

    private void tb_NKHComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tb_NKHComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NKHComponentResized

    private void tb_NCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NCCMouseClicked
        int indexSelectRow = this.tb_NCC.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) this.tb_NCC.getModel();
        
        tf_NCC_TenNCC.setText((String) mode.getValueAt(indexSelectRow, 1));
        tf_NCC_DienThoai.setText((String) mode.getValueAt(indexSelectRow, 2));
        
        if (((String)mode.getValueAt(indexSelectRow, 3)).equals(rb_NCC_BinhThuong.getText()))
           rb_NCC_BinhThuong.setSelected(true);
        else
           rb_NCC_NgungLaySach.setSelected(true);
        
    }//GEN-LAST:event_tb_NCCMouseClicked

    private void lb_NCC_NewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NCC_NewMouseClicked

        tf_NCC_TenNCC.setText(null);
        tf_NCC_DienThoai.setText(null);
        
        rb_NCC_BinhThuong.setSelected(true);
        tf_NCC_TenNCC.requestFocusInWindow();
    }//GEN-LAST:event_lb_NCC_NewMouseClicked

    private void lb_NCC_NewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lb_NCC_NewKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_NCC_NewKeyPressed

    private void btn_NCC_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NCC_ThemActionPerformed
        String thongBao = "";
        try{
            boolean flag = true;
            if (tf_NCC_TenNCC.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên nhà cung cấp không được để trống";
                flag = false;
            }
            else{
                NhaCungCap nhaCungCap = BNhaCungCap.getInstance().getNhaCungCapByTenNCC(tf_NCC_TenNCC.getText().trim());
                if (nhaCungCap != null) {
                    thongBao = thongBao + "\nTên nhà cung cấp da ton tai";
                    flag = false;
                }
            }
            
            if (flag) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setTenNhaCC(tf_NCC_TenNCC.getText().trim());
                ncc.setDienThoai(tf_NCC_DienThoai.getText().trim());
                ncc.setTinhTrang(rb_NCC_BinhThuong.isSelected()?true:false);
                
                if (BNhaCungCap.getInstance().ThemNhaCungCap(ncc)) {
                    thongBao = "Đã thêm nhà cung câp";
                    LoadNhaCungCap();
                }
                else{
                    thongBao = "Thêm không thành công";
                }
            }
        }catch(SQLException e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_NCC_ThemActionPerformed

    private void lb_KH_NewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lb_KH_NewKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_lb_KH_NewKeyPressed

    private void lb_KH_NewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_KH_NewMouseClicked
        // TODO add your handling code here:
        tf_KH_TenKH.setText(null);
        tf_KH_DienThoai.setText(null);
        cb_KH_NKH.setSelectedIndex(0);
        rb_KH_BinhThuong.setSelected(true);
        tf_KH_TenKH.requestFocusInWindow();
    }//GEN-LAST:event_lb_KH_NewMouseClicked

    private void lb_NKH_NewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_NKH_NewMouseClicked
        tf_NKH_TenNhom.setText(null);
        tf_NKH_ChiChu.setText(null);
        tf_NKH_TenNhom.requestFocusInWindow();
    }//GEN-LAST:event_lb_NKH_NewMouseClicked

    private void lb_NKH_NewKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lb_NKH_NewKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lb_NKH_NewKeyPressed

    private void btn_NCC_CapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NCC_CapNhapActionPerformed
        String thongBao = "";
        DefaultTableModel mode = (DefaultTableModel) tb_NCC.getModel();
        int index =  tb_NCC.getSelectedRow();
        if (index  == -1)
            return;
        
        try{
            boolean flag = true;
            if (tf_NCC_TenNCC.getText().trim().length() == 0) {
                thongBao = thongBao + "\nTên nhà cung cấp không được để trống";
                flag = false;
            }
            else{
                NhaCungCap nhaCungCap = BNhaCungCap.getInstance().getNhaCungCapByTenNCC(tf_NCC_TenNCC.getText().trim());
                if (nhaCungCap != null&& !nhaCungCap.getMaNhaCC().equals((String)mode.getValueAt(index, 0))) {
                    thongBao = thongBao + "\nTên nhà cung cấp da ton tai";
                    flag = false;
                }
            }

            if (flag) {
                
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNhaCC((String) mode.getValueAt(index, 0));
                ncc.setTenNhaCC(tf_NCC_TenNCC.getText().trim());
                ncc.setDienThoai(tf_NCC_DienThoai.getText().trim());
                ncc.setTinhTrang(rb_NCC_BinhThuong.isSelected()?true:false);
                
                if (BNhaCungCap.getInstance().UpdateNhaCungCap(ncc)) {
                    thongBao = "Sửa thông tin thành công";
                    LoadNhaCungCap();
                }
                else{
                    thongBao = "Sửa thông tin không thành công";
                }
            }
        }catch(SQLException e){
            thongBao = "ERROR: " + e.getMessage();
        }
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_NCC_CapNhapActionPerformed

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
            java.util.logging.Logger.getLogger(F_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_KhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new F_KhachHang().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup_KH_TinhTrang;
    private javax.swing.JToggleButton btn_KH_CapNhap;
    private javax.swing.JToggleButton btn_KH_Search;
    private javax.swing.JToggleButton btn_KH_Them;
    private javax.swing.JToggleButton btn_NCC_CapNhap;
    private javax.swing.JToggleButton btn_NCC_Search;
    private javax.swing.JToggleButton btn_NCC_Them;
    private javax.swing.JToggleButton btn_NKH_CapNhap;
    private javax.swing.JToggleButton btn_NKH_Search;
    private javax.swing.JToggleButton btn_NKH_Them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_KH_NKH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lb_KH_New;
    private javax.swing.JLabel lb_KH_NhanVien;
    private javax.swing.JLabel lb_NCC_New;
    private javax.swing.JLabel lb_NCC_NhanVien;
    private javax.swing.JLabel lb_NKH_New;
    private javax.swing.JLabel lb_NKH_NhanVien;
    private javax.swing.JLabel lb_TenNhanVien;
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
    private javax.swing.JPanel pn_KhachHang;
    private javax.swing.JPanel pn_NhaCungCap;
    private javax.swing.JPanel pn_NhomKhachHang;
    private javax.swing.JRadioButton rb_KH_BinhThuong;
    private javax.swing.JRadioButton rb_KH_NgungBan;
    private javax.swing.JRadioButton rb_NCC_BinhThuong;
    private javax.swing.JRadioButton rb_NCC_NgungLaySach;
    private javax.swing.JTable tb_KhachHang;
    private javax.swing.JTable tb_NCC;
    private javax.swing.JTable tb_NKH;
    private javax.swing.JTextField tf_KH_DienThoai;
    private javax.swing.JTextField tf_KH_Search;
    private javax.swing.JTextField tf_KH_TenKH;
    private javax.swing.JTextField tf_NCC_DienThoai;
    private javax.swing.JTextField tf_NCC_Search;
    private javax.swing.JTextField tf_NCC_TenNCC;
    private javax.swing.JTextField tf_NKH_ChiChu;
    private javax.swing.JTextField tf_NKH_Search;
    private javax.swing.JTextField tf_NKH_TenNhom;
    private javax.swing.JTabbedPane tp_KhachHang;
    // End of variables declaration//GEN-END:variables
}
