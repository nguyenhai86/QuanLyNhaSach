/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhasach;

/**
 *
 * @author NguyenHai
 */
public class Test {
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
    public static void main(String[] args) {
        System.out.println(CatTen("Villette"));
    }
}
