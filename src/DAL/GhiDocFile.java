package DAL;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
        
/**
 *
 * @author nguye
 */
public class GhiDocFile {
    private static GhiDocFile instance;
    public static GhiDocFile getInstance() {
        if (instance == null) {
            instance = new GhiDocFile();
        }
        return instance;
    }
    private static void setInstance(GhiDocFile instance) {
        GhiDocFile.instance = instance;
    }
    private GhiDocFile(){};
    
    public boolean GhiFile(String value){
        try{
            FileOutputStream fis = new FileOutputStream("src/Database/connectionUrl.ini");
            OutputStreamWriter osw = new OutputStreamWriter(fis,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(value);
            bw.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public String DocFile()
    {
        String str = null;
        try{
            FileInputStream fis = new FileInputStream("src/Database/connectionUrl.ini");
            InputStreamReader iSr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(iSr);

            str = null;
            str = br.readLine();
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        return str;
    }
}
