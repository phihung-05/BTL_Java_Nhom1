/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testGiaoDien;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class adad {
 
    public static void main(String[] args) throws IOException {
        List<getUserPass> list = new ArrayList<>();
        String url = "pass.txt";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
//          String content = "This is my content which would be appended " +
//            "at the end of the specified file";
        File file =new File(url);
        if(!file.exists()){
           file.createNewFile();
        }
//        FileWriter fw = new FileWriter(file,true);
//        BufferedWriter bw = new BufferedWriter(fw);
        int lines = 0;
//       
//        bw.write(content);
//        bw.close();
         FileReader fr = new FileReader(url);
         BufferedReader br = new BufferedReader(fr);
         String str,user[]=new String[100],pass[] = new String[100];
         String[] saveStr;
         int i=0;
          boolean check = false;
         while((str=br.readLine())!=null){
             saveStr = str.split(" ");
             for(String item: saveStr){
                 user[i] = saveStr[0];
                 pass[i] = saveStr[1];
             }
             i++;
         }
         for(int j =0;j<user.length;j++){
             if(user[j].contains("docaolong")){
                 check=true;
                break;
             }
             else if(!user[j].contains("docaolong")){
                check=false;
                
             }
         }
           if(check){
                 System.out.println("Thành công");
             }
             else{
                 System.out.println("Thất bại");
             }
         br.close();
        } catch (Exception e) {
           
        }
        finally{
            if(fis!=null){
                fis.close();
            }
           if(fos!=null){
               fos.close();
           }
        }
    }
}
