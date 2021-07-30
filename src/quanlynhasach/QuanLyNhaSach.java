/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author Nguyen Hai
 */
public class QuanLyNhaSach {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FlatLightLaf.setup();
        BienToanCuc.getInstance().getF_Login().setVisible(true);
    }
    
}
