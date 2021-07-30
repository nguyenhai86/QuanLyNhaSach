/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.text.SimpleDateFormat;  
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class DateBox {
    private DateBox(){}
    public static Date getDate(String strDate){
        if (strDate == null) {
            return null;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy"); 
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException ex) {
            
        }
        return date;
    }
    public static String ConvertToString(Date dt)
    {
        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        String strDate = null;
        try{
            strDate = formatDate.format(dt);
        }catch(Exception ex){
            
        }
        return strDate;
    }
}
