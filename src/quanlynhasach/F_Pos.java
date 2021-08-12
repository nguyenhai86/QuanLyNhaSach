/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

import BLL.BHoaDon;
import BLL.BKhachHang;
import BLL.BSach;
import DTO.DateBox;
import DTO.HoaDon;
import DTO.KhachHang;
import DTO.Sach;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguye
 */
public class F_Pos extends javax.swing.JFrame {

    /**
     * Creates new form F_POS
     */
    public F_Pos() {
        initComponents();
        btn_ThemKH = new JButton();
        btn_ThemKH = new JButton("Thêm");
        btn_ThemKH.setIcon(new ImageIcon("Images/person.png"));
        btn_ThemKH.setBorderPainted(false);
        btn_ThemKH.setFocusPainted(false);
        btn_ThemKH.setContentAreaFilled(false);
        Table_Header_Component(tableView);
        tf_TenNV.setText(BienToanCuc.getInstance().getNguoiDangNhap().getTenNhanVien());
    }

    public void Table_Header_Component(JTable table) {
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
        table.getTableHeader().setOpaque(false);
    }

    public ArrayList<Sach> TableToArray() {
        DefaultTableModel mode = (DefaultTableModel) this.tableView.getModel();
        ArrayList<Sach> sachs = new ArrayList<Sach>();
        Vector data = mode.getDataVector();
        data.forEach((t) -> {
            Sach sach = new Sach();
            Vector temp = (Vector) t;
            sach.setMaSach((String) temp.get(0));
            sach.setTenSach((String) temp.get(1));
            sach.setSoLuong((int) temp.get(2));
            sach.setGiaBan((double) temp.get(3));
            sachs.add(sach);
        });
        return sachs;
    }

    public double TinhTongTien() {
        double tongTien = 0;
        ArrayList<Sach> sachs = TableToArray();
        for (int i = 0; i < sachs.size(); i++) {
            Sach temp = sachs.get(i);
            tongTien = tongTien + temp.getGiaBan() * temp.getSoLuong();
        }
        return tongTien;
    }

    public int GetIndexMaSachInTable(String maSach) {
        maSach = maSach.toUpperCase();
        ArrayList<Sach> sachs = TableToArray();
        for (int i = 0; i < sachs.size(); i++) {
            Sach temp = sachs.get(i);
            if (temp.getMaSach().equals(maSach)) {
                return i;
            }
        }
        return -1;
    }
    public static String CatTen(String value){
        if (value.length() > 21) {
            value = value.substring(0, 21);
            value = value + "...";
        }
        else{
            for (int i = value.length(); i <= 21; i++)
                value = value + " ";
        }
        return value;
    }
    public void PrintBill(){
        try{
        tf_Bill.setText(null);
        tf_Bill.setText("\n********************************************************************");
        tf_Bill.setText(tf_Bill.getText() + "\n                                           CỬA HÀNG NHÀ SÁCH AN NHIÊN                               ");
        tf_Bill.setText(tf_Bill.getText() + "\n                                                 HÓA ĐƠN BÁN HÀNG                                   ");
        tf_Bill.setText(tf_Bill.getText() + "\n********************************************************************");
        tf_Bill.setText(tf_Bill.getText() + "\nMã hóa đơn: " + BHoaDon.getInstance().getMaHoaDonMoiNhat());
        tf_Bill.setText(tf_Bill.getText() + "\t\t\tNgày bán: " + DateBox.ConvertToString(date_NgayBan.getDate()));
        tf_Bill.setText(tf_Bill.getText() + "\nTên nhân viên: " + tf_TenNV.getText());
        tf_Bill.setText(tf_Bill.getText() + "\tKhách hàng: " + khachHang.getTenKhachHang());
        tf_Bill.setText(tf_Bill.getText() + "\n--------------------------------------------------------------------------------------------");
        tf_Bill.setText(tf_Bill.getText() + "\nMã sách\tTên sách\t\tSố lượng\tĐơn giá\tThành tiền");
        tf_Bill.setText(tf_Bill.getText() + "\n--------------------------------------------------------------------------------------------");
        ArrayList<Sach> sach = TableToArray();
        for (int i = 0; i < sach.size(); i++) {
            tf_Bill.setText(tf_Bill.getText() + "\n" + sach.get(i).getMaSach() + "\t" + CatTen(sach.get(i).getTenSach()) + "\t" + sach.get(i).getSoLuong() + "\t" + sach.get(i).getGiaBan() + "\t" + sach.get(i).getGiaBan()*sach.get(i).getSoLuong());
            if (i < sach.size() -1)
                tf_Bill.setText(tf_Bill.getText() + "\n-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
        }                                       
        tf_Bill.setText(tf_Bill.getText() + "\n--------------------------------------------------------------------------------------------");
        tf_Bill.setText(tf_Bill.getText() + "\n"+"\t"+"\t"+"\t"+"\t"+"Tổng tiền: " +"\t"+ lb_TienHang.getText());
        tf_Bill.setText(tf_Bill.getText() + "\n\tCảm ơn và Hẹn gặp lại!");
        //tf_Bill.print();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"ERROR: " + e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        date_NgayBan = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_ThoiLai = new javax.swing.JLabel();
        lb_TienHang = new javax.swing.JLabel();
        tf_TienKhachDua = new java.awt.TextField();
        jLabel11 = new javax.swing.JLabel();
        btn_ThanhToan = new javax.swing.JToggleButton();
        btn_ThemKH = new javax.swing.JButton();
        tf_SoDienThoai = new javax.swing.JTextField();
        tf_TenNV = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        tf_TenKhachHang = new javax.swing.JTextField();
        lb_KH_ThongBao = new javax.swing.JLabel();
        btn_TaoMoi = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tf_Bill = new javax.swing.JTextArea();
        lb_TempPos = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_MaHangHoa = new javax.swing.JTextField();
        btn_Scan = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        lb_ThemSach_ThongBao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("POS");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tableView.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableView.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableView.setGridColor(new java.awt.Color(216, 216, 216));
        tableView.setRowHeight(25);
        tableView.setSelectionBackground(new java.awt.Color(23, 130, 209));
        tableView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableView.setShowGrid(true);
        tableView.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tableViewFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tableViewFocusLost(evt);
            }
        });
        tableView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableViewMouseClicked(evt);
            }
        });
        tableView.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableViewPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tableView);
        if (tableView.getColumnModel().getColumnCount() > 0) {
            tableView.getColumnModel().getColumn(0).setMinWidth(100);
            tableView.getColumnModel().getColumn(0).setPreferredWidth(100);
            tableView.getColumnModel().getColumn(0).setMaxWidth(100);
            tableView.getColumnModel().getColumn(1).setMinWidth(200);
            tableView.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableView.getColumnModel().getColumn(2).setMinWidth(75);
            tableView.getColumnModel().getColumn(2).setPreferredWidth(75);
            tableView.getColumnModel().getColumn(2).setMaxWidth(75);
            tableView.getColumnModel().getColumn(3).setMinWidth(100);
            tableView.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableView.getColumnModel().getColumn(3).setMaxWidth(100);
            tableView.getColumnModel().getColumn(4).setMinWidth(100);
            tableView.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableView.getColumnModel().getColumn(4).setMaxWidth(100);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHI TIẾT ĐƠN HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Ngày bán");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Nhân viên");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Tiền hàng");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Thối lại");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setText("Tiền khách đưa");

        lb_ThoiLai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_ThoiLai.setForeground(new java.awt.Color(255, 0, 0));
        lb_ThoiLai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_ThoiLai.setText("0");
        lb_ThoiLai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lb_TienHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb_TienHang.setForeground(new java.awt.Color(255, 51, 0));
        lb_TienHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb_TienHang.setText("0");
        lb_TienHang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lb_TienHang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lb_TienHangPropertyChange(evt);
            }
        });

        tf_TienKhachDua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tf_TienKhachDua.setText("0");
        tf_TienKhachDua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_TienKhachDuaMouseClicked(evt);
            }
        });
        tf_TienKhachDua.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_TienKhachDuaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_TienKhachDuaFocusLost(evt);
            }
        });
        tf_TienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TienKhachDuaActionPerformed(evt);
            }
        });
        tf_TienKhachDua.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tf_TienKhachDuaTextValueChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Khách hàng");

        btn_ThanhToan.setBackground(new java.awt.Color(34, 177, 239));
        btn_ThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_ThanhToan.setText("THANH TOÁN (F9)");
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        btn_ThemKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_ThemKH.setMaximumSize(new java.awt.Dimension(51, 23));
        btn_ThemKH.setMinimumSize(new java.awt.Dimension(51, 23));
        btn_ThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemKHActionPerformed(evt);
            }
        });

        tf_SoDienThoai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_SoDienThoai.setText("Nhập số điện thoại");
        tf_SoDienThoai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_SoDienThoaiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_SoDienThoaiFocusLost(evt);
            }
        });
        tf_SoDienThoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_SoDienThoaiMouseClicked(evt);
            }
        });
        tf_SoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_SoDienThoaiActionPerformed(evt);
            }
        });
        tf_SoDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_SoDienThoaiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_SoDienThoaiKeyTyped(evt);
            }
        });

        tf_TenNV.setEditable(false);
        tf_TenNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tf_TenNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_TenNVMouseClicked(evt);
            }
        });
        tf_TenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TenNVActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(241, 241, 241));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tf_TenKhachHang.setEditable(false);
        tf_TenKhachHang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_TenKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_TenKhachHangFocusLost(evt);
            }
        });
        tf_TenKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_TenKhachHangMouseClicked(evt);
            }
        });
        tf_TenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_TenKhachHangActionPerformed(evt);
            }
        });
        tf_TenKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_TenKhachHangKeyPressed(evt);
            }
        });

        lb_KH_ThongBao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lb_KH_ThongBao.setForeground(new java.awt.Color(255, 51, 51));

        btn_TaoMoi.setBackground(new java.awt.Color(34, 177, 239));
        btn_TaoMoi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_TaoMoi.setText("TẠO MỚI");
        btn_TaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoMoiActionPerformed(evt);
            }
        });

        tf_Bill.setEditable(false);
        tf_Bill.setColumns(20);
        tf_Bill.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        tf_Bill.setRows(5);
        jScrollPane3.setViewportView(tf_Bill);

        lb_TempPos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lb_TempPosPropertyChange(evt);
            }
        });
        lb_TempPos.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                lb_TempPosVetoableChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb_TempPos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_ThoiLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tf_TienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addComponent(lb_TienHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_KH_ThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(tf_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addComponent(btn_ThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_NgayBan, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(tf_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(date_NgayBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_SoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_KH_ThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_TienHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_TienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_ThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_TempPos))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lb_ThoiLai.setHorizontalAlignment(SwingConstants.RIGHT);
        lb_TienHang.setHorizontalAlignment(SwingConstants.RIGHT);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Mã Sách");

        tf_MaHangHoa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_MaHangHoa.setText("Nhập mã sách");
        tf_MaHangHoa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_MaHangHoaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_MaHangHoaFocusLost(evt);
            }
        });
        tf_MaHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_MaHangHoaMouseClicked(evt);
            }
        });
        tf_MaHangHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_MaHangHoaActionPerformed(evt);
            }
        });
        tf_MaHangHoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_MaHangHoaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_MaHangHoaKeyTyped(evt);
            }
        });

        btn_Scan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_Scan.setText("Scan");
        btn_Scan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ScanActionPerformed(evt);
            }
        });

        btn_TimKiem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        lb_ThemSach_ThongBao.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lb_ThemSach_ThongBao.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_ThemSach_ThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_MaHangHoa, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Scan, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_MaHangHoa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Scan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_ThemSach_ThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(573, 573, 573))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_TienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TienKhachDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TienKhachDuaActionPerformed

    private void tf_SoDienThoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiMouseClicked
        tf_SoDienThoai.selectAll();
    }//GEN-LAST:event_tf_SoDienThoaiMouseClicked

    private void tf_SoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_SoDienThoaiActionPerformed

    private void tf_TenNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_TenNVMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenNVMouseClicked

    private void tf_TenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenNVActionPerformed

    private void tf_SoDienThoaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiFocusLost
        tf_TenKhachHang.setText(null);
        if (tf_SoDienThoai.getText().trim().length() == 0) {
            tf_SoDienThoai.setText("Nhập số điện thoại");
            tf_SoDienThoai.setForeground(Color.GRAY);
            return;
        }

        String thongBao = null;
        try {

            khachHang = BKhachHang.getInstance().getKhachHangBySDT(tf_SoDienThoai.getText().trim());
            if (khachHang != null) {
                if (khachHang.getTinhTrang() == true) {
                    tf_TenKhachHang.setText(khachHang.getTenKhachHang());
                } else {
                    thongBao = "Khách hàng này không được phép mua hàng";
                }
            } else {
                thongBao = "Không tồn tại khách hàng có số điện thoại này";
            }
        } catch (Exception e) {
            thongBao = "ERROR: " + e.getMessage();
        }
        lb_KH_ThongBao.setText(thongBao);
    }//GEN-LAST:event_tf_SoDienThoaiFocusLost

    private void tf_SoDienThoaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiKeyPressed


    }//GEN-LAST:event_tf_SoDienThoaiKeyPressed

    private void tf_TenKhachHangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_TenKhachHangFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenKhachHangFocusLost

    private void tf_TenKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_TenKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenKhachHangMouseClicked

    private void tf_TenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_TenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenKhachHangActionPerformed

    private void tf_TenKhachHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_TenKhachHangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_TenKhachHangKeyPressed

    private void tf_SoDienThoaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiFocusGained
        if (tf_SoDienThoai.getText().equals("Nhập số điện thoại")) {
            tf_SoDienThoai.setText(null);
            tf_SoDienThoai.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tf_SoDienThoaiFocusGained

    private void tf_SoDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_SoDienThoaiKeyTyped
        char c = evt.getKeyChar();
        if ((c < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_tf_SoDienThoaiKeyTyped

    private void btn_ThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemKHActionPerformed
        BienToanCuc.getInstance().getF_ThemKhachHang().setVisible(true);
    }//GEN-LAST:event_btn_ThemKHActionPerformed

    private void tf_MaHangHoaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_MaHangHoaFocusGained
        if (tf_MaHangHoa.getText().equals("Nhập mã sách")) {
            tf_MaHangHoa.setText(null);
            tf_MaHangHoa.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_tf_MaHangHoaFocusGained

    private void tf_MaHangHoaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_MaHangHoaFocusLost
        lb_ThemSach_ThongBao.setText(null);
        if (tf_MaHangHoa.getText().trim().length() == 0) {
            tf_MaHangHoa.setText("Nhập mã sách");
            tf_MaHangHoa.setForeground(Color.GRAY);
            return;
        }
        
        DefaultTableModel mode = (DefaultTableModel) tableView.getModel();
        String maSach = tf_MaHangHoa.getText().trim();
        try{
            Sach sach = BSach.getInstance().getSachByMa(maSach);
            if (sach != null)
                if (sach.getTinhTrang() == true){
                    int index = GetIndexMaSachInTable(tf_MaHangHoa.getText().trim());
                    if (index != -1) {
                        //sach da co trong ds
                        if (sach.getSoLuong() > (int)mode.getValueAt(index,2)){
                            mode.setValueAt((int)mode.getValueAt(index,2) + 1, index, 2);
                            mode.setValueAt((int)mode.getValueAt(index,2) * (double)mode.getValueAt(index,3), index, 4);
                        }
                        else
                            lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " chỉ còn " + sach.getSoLuong() + " cuốn");
                    }
                    else{
                        //Sach chua co trong ds
                        if (sach.getSoLuong() > 0)
                            mode.addRow(new Object[]{sach.getMaSach(), sach.getTenSach(), 1, sach.getGiaBan(), 1*sach.getGiaBan()});
                        else
                            lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " chỉ còn " + sach.getSoLuong() + " cuốn");
                    }
                    lb_TienHang.setText(String.valueOf(TinhTongTien()));
                }
                else
                    lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " đã ngưng bán");
            else
                lb_ThemSach_ThongBao.setText("Mã sách " + maSach + " không tồn tại");
       }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
        tf_MaHangHoa.setText(null);
        tf_MaHangHoa.requestFocusInWindow();
    }//GEN-LAST:event_tf_MaHangHoaFocusLost

    private void ThemSach(String maSach, int soLuong) {
        
    }
    private void tf_MaHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_MaHangHoaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_MaHangHoaMouseClicked

    private void tf_MaHangHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_MaHangHoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_MaHangHoaActionPerformed

    private void tf_MaHangHoaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_MaHangHoaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_MaHangHoaKeyPressed

    private void tf_MaHangHoaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_MaHangHoaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_MaHangHoaKeyTyped

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed

        //while(!BienToanCuc.getInstance().getFlag()){}
        BienToanCuc.getInstance().setFlag(false);
        
        f_TimSach = new F_TimSach();
        f_TimSach.setVisible(true);
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void tableViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableViewMouseClicked
        
    }//GEN-LAST:event_tableViewMouseClicked
    private void Reset() {
        date_NgayBan.setDate(new Date());
        tf_SoDienThoai.setText(null);
        tf_TenKhachHang.setText(null);
        lb_ThemSach_ThongBao.setText(null);
        tf_TienKhachDua.setText("0");
        lb_ThoiLai.setText("0");
        lb_TienHang.setText("0");
//        tf_Bill.setText(null);
        DefaultTableModel mode = (DefaultTableModel) tableView.getModel();
        mode.setRowCount(0);
    }
    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        String thongBao = "";
        boolean flag = true;
        ArrayList<Sach> sachs = TableToArray();
        
        try {
            if (sachs.isEmpty()) {
                thongBao = thongBao + "\nDanh sách đơn hàng rỗng";
                flag = false;
            }
            if (khachHang == null) {
                khachHang = BKhachHang.getInstance().getKhachHangByMa("KH001");
            }
            if (Double.valueOf(lb_ThoiLai.getText()) < 0) {
                thongBao = thongBao + "\nTien khach dua chua du";
                flag = false;
            }
            if (flag) {
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setNhanVien(BienToanCuc.getInstance().getNguoiDangNhap().getMaNhanVien());
                    hoaDon.setKhachHang(khachHang.getMaKhachHang());
                    hoaDon.setNgayBan(date_NgayBan.getDate());
                    if (BHoaDon.getInstance().ThemHoaDon(hoaDon, sachs)) {
                        thongBao = "Thanh toán thành công";
                        PrintBill();
                        Reset();
                        khachHang = null;
                        if (f_TimSach != null) {
                            f_TimSach.LoadSach();
                            f_TimSach.Reset();
                        }
                    } else {
                        thongBao = "Thanh toán không thành công";
                    }
            }
        }catch (SQLException e) {
            thongBao = "ERROR: " + e.getMessage();
        }
        khachHang = null;
        tf_SoDienThoai.setText(null);
        tf_TenKhachHang.setText(null);
        JOptionPane.showMessageDialog(this, thongBao);
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void tableViewPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableViewPropertyChange

        int index =  tableView.getSelectedRow();
        DefaultTableModel mode = (DefaultTableModel) tableView.getModel();
        
        try{
            if (index == -1)
                return;
            else if ((int)mode.getValueAt(index, 2) < 0)
                mode.setValueAt(1, index, 2);
            else if( (int)mode.getValueAt(index, 2) == 0 )
                mode.removeRow(index);
            else{
                Sach sach = BSach.getInstance().getSachByMa((String)mode.getValueAt(index, 0));
                if (sach.getSoLuong() >= (int)mode.getValueAt(index, 2))
                    mode.setValueAt((int)mode.getValueAt(index, 2), index, 2);
                else{
                    lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " chỉ còn " + sach.getSoLuong() + " cuốn");
                    //Cai tien old value
                    mode.setValueAt(1, index, 2);
                }
                mode.setValueAt((int)mode.getValueAt(index,2) * (double)mode.getValueAt(index,3), index, 4);
            }
            
            lb_TienHang.setText(String.valueOf(TinhTongTien()));
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_tableViewPropertyChange

    private void lb_TienHangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lb_TienHangPropertyChange
        lb_ThoiLai.setText(String.valueOf((Double.valueOf(tf_TienKhachDua.getText()) - Double.valueOf(lb_TienHang.getText()))));
    }//GEN-LAST:event_lb_TienHangPropertyChange

    private void tf_TienKhachDuaTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_tf_TienKhachDuaTextValueChanged
        if (tf_TienKhachDua.getText().length() == 0)
            lb_ThoiLai.setText(String.valueOf((0 - Double.valueOf(lb_TienHang.getText()))));
        else
            lb_ThoiLai.setText(String.valueOf((Double.valueOf(tf_TienKhachDua.getText()) - Double.valueOf(lb_TienHang.getText()))));
    }//GEN-LAST:event_tf_TienKhachDuaTextValueChanged

    private void tf_TienKhachDuaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_TienKhachDuaFocusLost
        if (tf_TienKhachDua.getText().length() == 0) {
            tf_TienKhachDua.setText("0");
        }
    }//GEN-LAST:event_tf_TienKhachDuaFocusLost

    private void tf_TienKhachDuaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_TienKhachDuaFocusGained
        if (tf_TienKhachDua.getText().equals("0")) {
            tf_TienKhachDua.setText(null);
        }
    }//GEN-LAST:event_tf_TienKhachDuaFocusGained

    private void tf_TienKhachDuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_TienKhachDuaMouseClicked
        tf_TienKhachDua.selectAll();
    }//GEN-LAST:event_tf_TienKhachDuaMouseClicked

    private void tableViewFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableViewFocusLost
        
    }//GEN-LAST:event_tableViewFocusLost

    private void tableViewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tableViewFocusGained
       
    }//GEN-LAST:event_tableViewFocusGained

    private void btn_TaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoMoiActionPerformed
       Reset();
    }//GEN-LAST:event_btn_TaoMoiActionPerformed

    private void lb_TempPosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_lb_TempPosVetoableChange
        
    }//GEN-LAST:event_lb_TempPosVetoableChange

    private void lb_TempPosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lb_TempPosPropertyChange

        DefaultTableModel mode = (DefaultTableModel) tableView.getModel();
        String maSach = lb_TempPos.getText();
        if (maSach == null) 
            return;
        else if (maSach.length() == 0)
            return;
            
        try{
            Sach sach = BSach.getInstance().getSachByMa(maSach);
            if (sach != null)
                if (sach.getTinhTrang() == true){
                    int index = GetIndexMaSachInTable(maSach.trim());
                    if (index != -1) {
                        //sach da co trong ds
                        if (sach.getSoLuong() > (int)mode.getValueAt(index,2)){
                            mode.setValueAt((int)mode.getValueAt(index,2) + 1, index, 2);
                            mode.setValueAt((int)mode.getValueAt(index,2) * (double)mode.getValueAt(index,3), index, 4);
                        }
                        else
                            lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " chỉ còn " + sach.getSoLuong() + " cuốn");
                    }
                    else{
                        //Sach chua co trong ds
                        if (sach.getSoLuong() > 0)
                            mode.addRow(new Object[]{sach.getMaSach(), sach.getTenSach(), 1, sach.getGiaBan(), 1*sach.getGiaBan()});
                        else
                            lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " chỉ còn " + sach.getSoLuong() + " cuốn");
                    }
                    lb_TienHang.setText(String.valueOf(TinhTongTien()));
                }
                else
                    lb_ThemSach_ThongBao.setText("Sách " + sach.getTenSach() + " đã ngưng bán");
            else
                lb_ThemSach_ThongBao.setText("Mã sách " + maSach + " không tồn tại");
       }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e.getMessage());
        }
        lb_TempPos.setText(null);
    }//GEN-LAST:event_lb_TempPosPropertyChange

    private void btn_ScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ScanActionPerformed
        F_Scan.getInstance();
        
    }//GEN-LAST:event_btn_ScanActionPerformed
    public static void lb_TempPosPosChangeValue(String value){
        lb_TempPos.setText(value);
    }
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
            java.util.logging.Logger.getLogger(F_Pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_Pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_Pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_Pos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new F_Pos().setVisible(true);
            }
        });
    }
    private F_TimSach f_TimSach = null;
    private KhachHang khachHang = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Scan;
    private javax.swing.JToggleButton btn_TaoMoi;
    private javax.swing.JToggleButton btn_ThanhToan;
    private javax.swing.JButton btn_ThemKH;
    private javax.swing.JButton btn_TimKiem;
    private com.toedter.calendar.JDateChooser date_NgayBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_KH_ThongBao;
    private static javax.swing.JLabel lb_TempPos;
    private javax.swing.JLabel lb_ThemSach_ThongBao;
    private javax.swing.JLabel lb_ThoiLai;
    private javax.swing.JLabel lb_TienHang;
    private javax.swing.JTable tableView;
    private javax.swing.JTextArea tf_Bill;
    private javax.swing.JTextField tf_MaHangHoa;
    private javax.swing.JTextField tf_SoDienThoai;
    private javax.swing.JTextField tf_TenKhachHang;
    private javax.swing.JTextField tf_TenNV;
    private java.awt.TextField tf_TienKhachDua;
    // End of variables declaration//GEN-END:variables
}
