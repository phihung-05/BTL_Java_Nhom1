/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduleStudents;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


public class DoAllObject {


    public DoAllObject() {

    }
    String url = "data_Students.txt";
    String url2 = "data.txt";
    String url3 = "data_copy.txt";
    File file = new File(url);

    public int findMsv(String msv) {
        String lines;
        Vector Savemsv = new Vector();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DoAllObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(file));
            Object[] s;
            while ((lines = buffRead.readLine()) != null) {
                s = lines.split(";");
                Savemsv.add(s[0]);
            }
            Enumeration e = Savemsv.elements();
            while (e.hasMoreElements()) {
                if (msv.equalsIgnoreCase(e.nextElement().toString())) {
                    return 1;
                }
            }
        } catch (Exception e) {

        }
        return -1;
    }

  

   

    public int delete(String MSV) {
        String file1 = "data.txt", file2 = "data_copy.txt";
        File file = new File(file2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
               
            }
            System.out.println("Đã tạo file copy tại " + file.getAbsolutePath());
        }
        FileReader fr = null;
        BufferedReader br = null;
        DoAllObject DAO = new DoAllObject();
        DAO.copyData("data_Students.txt", file2);
        String search = DAO.search(MSV);
        try {
            FileWriter fw = new FileWriter(file1);
            fr = new FileReader(file2);
            br = new BufferedReader(fr);
            String lines;
            while ((lines = br.readLine()) != null) {
                if (lines.equalsIgnoreCase(search)) {
                    lines = lines.replace(search, "0000000000");
                }
                if (!lines.equalsIgnoreCase("0000000000")) {

                    fw.write(lines + "\n");
                }
            }
            fw.close();
            DAO.copyData("data.txt", "data_Students.txt");
            file.delete();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
        return 1;
    }

    public String search(String msv) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Đã tạo 1 file mới tại " + file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(DoAllObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(url);
            br = new BufferedReader(fr);
            String lines, save[];
            while ((lines = br.readLine()) != null) {
                if (lines == null) {
                    break;
                }
                save = lines.split(";");
                if (save[0].equalsIgnoreCase(msv)) {
                    return lines;
                }
            }
        } catch (Exception e) {

        }
        return null;
    }

    public int edit(String msv, String hoten, String malop, Double diemlythuyet, Double diemthuchanh, Double diemtrungbinh, String ketqua) {
        String file1 = "data.txt", file2 = "data_copy.txt";
        File file = new File(file2);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                
            }
            System.out.println("Đã tạo file copy tại " + file.getAbsolutePath());
        }
        FileReader fr = null;
        BufferedReader br = null;
        DoAllObject DAO = new DoAllObject();
        DAO.copyData("data_Students.txt", file2);
        String search = DAO.search(msv);
        try {
            FileWriter fw = new FileWriter(file1);
            fr = new FileReader(file2);
            br = new BufferedReader(fr);
            String lines;
            diemtrungbinh = (diemlythuyet + diemthuchanh) / 2;
            if (diemtrungbinh >= 5) {
                ketqua = "Passed";
            } else {
                ketqua = "Failed";
            }
            String lineEdit = msv + ";" + hoten + ";" + malop + ";" + diemlythuyet + ";" + diemthuchanh + ";" + diemtrungbinh + ";" + ketqua;
            while ((lines = br.readLine()) != null) {
                if (lines.equalsIgnoreCase(search)) {
                    lines = lines.replace(search, lineEdit);
                }
                if (!lines.equalsIgnoreCase("0000000000")) {
                    fw.write(lines + "\n");
                }
            }
            fw.close();
            DAO.copyData("data.txt", "data_Students.txt");
            file.delete();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
        return 1;
    }

    public void copyData(String file, String fileCopy) {
        File creatFile = new File(fileCopy);
        if (!creatFile.exists()) {
            try {
                creatFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(DoAllObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(fileCopy);
            int lengthFile;
            byte[] byteData = new byte[1024];
            while ((lengthFile = fis.read(byteData)) > 0) {
                fos.write(byteData, 0, lengthFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(DoAllObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addDatatoFile(String msv, String hoten, String malop, double diemlythuyet, double diemthuchanh, double diemtrungbinh, String ketqua) throws IOException {
        String url = "data_Students.txt";
        File file = new File(url);
        diemtrungbinh = (diemlythuyet + diemthuchanh) / 2;
        if (diemtrungbinh >= 5 && diemtrungbinh <= 10) {
            ketqua = "Passed";
        } else if (diemtrungbinh >= 0 && diemtrungbinh < 5) {
            ketqua = "Failed";
        }
        String subStr = msv + ";" + hoten + ";" + malop + ";" + diemlythuyet + ";" + diemthuchanh + ";" + diemtrungbinh + ";" + ketqua;
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Sucessfully created a file at " + file.getAbsolutePath());
        }
        try {
            FileWriter fileWriter = new FileWriter(url, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write(subStr);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(DoAllObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void swap(long a, long b) {
        long temp = a;
        a = b;
        b = temp;
    }

    public void sortTangDan() {
        String file1 = "data_copy.txt";
        String file2 = "data.txt";
        String fileSource = "data_Students.txt";
        DoAllObject DAO = new DoAllObject();
        File f1 = new File(file1);
        File f2 = new File(file2);
        File fSource = new File(fileSource);
        DAO.copyData(fileSource, file1);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(file2);
            String lines;
            ArrayList<String> saveStudents = new ArrayList<>();
            while ((lines = br.readLine()) != null) {
                saveStudents.add(lines);
            }
            Collections.sort(saveStudents, (o1, o2) -> o1.compareTo(o2));
            Iterator itr = saveStudents.iterator();
            while (itr.hasNext()) {
                fw.write(itr.next().toString() + "\n");
            }
            fw.close();
            f1.delete();
            DAO.copyData(file2, fileSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortGiamDan() {
        String file1 = "data_copy.txt";
        String file2 = "data.txt";
        String fileSource = "data_Students.txt";
        DoAllObject DAO = new DoAllObject();
        File f1 = new File(file1);
        File f2 = new File(file2);
        File fSource = new File(fileSource);
        DAO.copyData(fileSource, file1);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(file2);
            String lines;
            ArrayList<String> saveStudents = new ArrayList<>();
            while ((lines = br.readLine()) != null) {
                saveStudents.add(lines);
            }
            Collections.sort(saveStudents, (o1, o2) -> o2.compareTo(o1));
            Iterator itr = saveStudents.iterator();
            while (itr.hasNext()) {
                fw.write(itr.next().toString() + "\n");
            }
            fw.close();
            f1.delete();
            DAO.copyData(file2, fileSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSelectedTbl(JTable tblStudent, String text) {
        try {
            int rows = tblStudent.getRowCount();
            Object[] m = new Object[rows];
            for (int i = 0; i < rows - 1; i++) {
                m[i] = tblStudent.getValueAt(i, 0);
            }
            for (int i = 0; i < m.length; i++) {
                if (m[i].equals(text)) {
                    tblStudent.setRowSelectionInterval(i, i);
                }
            }
        } catch (Exception ex) {
        }
    }

}
