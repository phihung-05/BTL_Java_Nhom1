/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testGiaoDien;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class dichPass {

    public static void main(String[] args) throws IOException {
        try {
            FileInputStream fis = new FileInputStream("data.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            getUserPass ss = (getUserPass) ois.readObject();
            System.out.println(ss);
        } catch (Exception e) {

        }
    }
}
