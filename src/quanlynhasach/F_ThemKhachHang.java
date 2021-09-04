/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

import BLL.BKhachHang;
import BLL.BNhomKhachHang;
import DTO.KhachHang;
import DTO.NhomKhachHang;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenHai
 */
public class F_ThemKhachHang extends javax.swing.JFrame {

    /**
     * Creates new form F_ThemKhachHang
     */
    public F_ThemKhachHang() {
        initComponents();
        LoadCbNhomKhachHang();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        tf_KH_TenKH = new javax.swing.JTextField();
        tf_KH_DienThoai = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btn_KH_Them = new javax.swing.JToggleButton();
        jLabel37 = new javax.swing.JLabel();
        rb_KH_BinhThuong = new javax.swing.JRadioButton();
        rb_KH_NgungBan = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        cb_KH_NKH = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Tên khách hàng:");

        tf_KH_TenKH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        tf_KH_DienThoai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tf_KH_DienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_KH_DienThoaiKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Điện thoại");

        btn_KH_Them.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_KH_Them.setText("Thêm");
        btn_KH_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KH_ThemActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setText("Tình trạng");

        buttonGroup1.add(rb_KH_BinhThuong);
        rb_KH_BinhThuong.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_KH_BinhThuong.setSelected(true);
        rb_KH_BinhThuong.setText("Bình Thường");

        buttonGroup1.add(rb_KH_NgungBan);
        rb_KH_NgungBan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rb_KH_NgungBan.setText("Ngừng bán");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Nhóm khách hàng");

        cb_KH_NKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_KH_NKHActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_KH_DienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_KH_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_KH_NKH, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb_KH_NgungBan)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rb_KH_BinhThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142))
                            .addComponent(btn_KH_Them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
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
                .addComponent(btn_KH_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
                    this.dispose();
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

    private void cb_KH_NKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_KH_NKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_KH_NKHActionPerformed

    private void tf_KH_DienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_KH_DienThoaiKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9') {
            evt.consume();
        }
    }//GEN-LAST:event_tf_KH_DienThoaiKeyTyped

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
            java.util.logging.Logger.getLogger(F_ThemKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_ThemKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_ThemKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_ThemKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new F_ThemKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_KH_Them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_KH_NKH;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rb_KH_BinhThuong;
    private javax.swing.JRadioButton rb_KH_NgungBan;
    private javax.swing.JTextField tf_KH_DienThoai;
    private javax.swing.JTextField tf_KH_TenKH;
    // End of variables declaration//GEN-END:variables
}
