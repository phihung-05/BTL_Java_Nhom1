/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testGiaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NewWindow extends JFrame {

    private JLabel lblTitle = new JLabel();
    private JLabel lblUser = new JLabel();
    private JTextField txtUser = new JTextField();
    private JLabel lblPass = new JLabel();
    private JPasswordField txtPass = new JPasswordField();
    private JButton btnLogin = new JButton();
    private JButton btnCreat = new JButton();

    public NewWindow() {
        setTitle("ĐĂNG KÝ TÀI KHOẢN");
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Container container = getContentPane();
        JPanel JPnMain = new JPanel();
        JPnMain.setBorder(BorderFactory.createLineBorder(Color.green));
        JPnMain.setLayout(new BoxLayout(JPnMain, BoxLayout.Y_AXIS));

        JPanel JPnTitle = new JPanel();
        JPnTitle.setPreferredSize(new Dimension(100, 10));
        JPnTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
        lblTitle.setText("ĐĂNG KÝ TÀI KHOẢN");
        lblTitle.setFont(new Font("Time New Roman", 1, 20));
        JPnTitle.add(lblTitle);
        JPnMain.add(JPnTitle);

        JPanel JPnUsePass = new JPanel();
        JPnUsePass.setLayout(new BoxLayout(JPnUsePass, BoxLayout.Y_AXIS));
        JPanel JPnUser = new JPanel();
        JPnUser.setPreferredSize(new Dimension(100, 5));
        lblUser.setText("Username");
        lblUser.setFont(new Font("Time New Roman", 0, 15));
        lblUser.setPreferredSize(new Dimension(100, 36));
        txtUser.setPreferredSize(new Dimension(200, 42));
        txtUser.setFont(new Font("Time New Roman", 0, 16));
        JPnUser.add(lblUser);
        JPnUser.add(txtUser);
        JPanel JPnPass = new JPanel();
        JPnPass.setPreferredSize(new Dimension(100, 5));
        lblPass.setText("Password");
        lblPass.setFont(new Font("Time New Roman", 0, 15));
        lblPass.setPreferredSize(new Dimension(100, 36));
        JPnPass.add(lblPass);
        txtPass.setPreferredSize(new Dimension(200, 42));
        txtPass.setFont(new Font("Time New Roman", 0, 16));
        JPnPass.add(txtPass);
        JPnUsePass.add(JPnUser);
        JPnUsePass.add(JPnPass);
        JPnMain.add(JPnUsePass);

        JPanel JPnLogin = new JPanel();
        JPnLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnLogin.setText("SUBMIT");
        btnLogin.setFont(new Font("Tahoma", 1, 15));
        btnLogin.setPreferredSize(new Dimension(100, 40));
        btnLogin.setBackground(Color.ORANGE);
        JPnLogin.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    btnSubmitAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(NewWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JPnMain.add(JPnLogin);

        container.add(JPnMain);
    }

    public void btnSubmitAction(ActionEvent e) throws IOException {
        String pass = new String(txtPass.getPassword());
        String user = new String(txtUser.getText());
//        getUserPass getdata = new getUserPass(txtUser.getText(), pass);
        String url = "pass.txt";
//        FileOutputStream fos = new FileOutputStream("data.txt",true);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
        File file = new File(url);
        if(!file.exists()){
            System.out.println("File don't exist!");
            file.createNewFile();
            System.out.println("Created a file: "+ file.toString()+"; Path: "+file.getAbsolutePath());
        }
        else{
            System.out.println("File exists. Successfully!"+"\n\n\n");
        }
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (txtUser.getText().isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ thông tin!", "Thông báo", 1);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Đăng kí thành công!");
            this.hide();
            bw.write(user+" "+pass);
            bw.newLine();
            bw.close();
            System.out.println("Data successfully appended at the next line of file!"+"\n");
        }
    }
}
