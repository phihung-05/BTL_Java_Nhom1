/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testGiaoDien;

import view.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class login extends JFrame {

    private JLabel lblTitle = new JLabel();
    private JLabel lblUser = new JLabel();
    private JTextField txtUser = new JTextField();
    private JLabel lblPass = new JLabel();
    private JPasswordField txtPass = new JPasswordField();
    private JButton btnLogin = new JButton();
    private JButton btnCreat = new JButton();
//    private String getUser = "cntt";
//    private String getPass = "123";

    public login() {
        init();
    }

    public void init() {
        setTitle("ĐĂNG NHẬP VÀO HỆ THỐNG");
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        JPanel JPnMain = new JPanel();
        JPnMain.setBorder(BorderFactory.createLineBorder(Color.green));
        JPnMain.setLayout(new BoxLayout(JPnMain, BoxLayout.Y_AXIS));

        JPanel JPnTitle = new JPanel();
        JPnTitle.setPreferredSize(new Dimension(100, 10));
        JPnTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 12));
        lblTitle.setText("ĐĂNG NHẬP VÀO HỆ THỐNG");
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
        btnLogin.setText("Login");
        btnLogin.setFont(new Font("Tahoma", 1, 15));
        btnLogin.setPreferredSize(new Dimension(100, 40));
        btnLogin.setBackground(Color.ORANGE);
        JPnLogin.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnLoginActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnCreat.setText("Creat");
        btnCreat.setFont(new Font("Tahoma", 1, 15));
        btnCreat.setPreferredSize(new Dimension(100, 40));
        btnCreat.setBackground(Color.ORANGE);
        JPnLogin.add(btnCreat);
        btnCreat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnCreateActionPerformed(e);
            }
        });
        JPnMain.add(JPnLogin);

        container.add(JPnMain);
    }

    public void btnLoginActionPerformed(ActionEvent e) throws IOException {
        getUserPass getUP = new getUserPass();
        String pass = new String(txtPass.getPassword());
        String s=txtUser.getText()+" " +pass;
        String url = "pass.txt";
        FileReader fileRead = null;
        BufferedReader bufferedRead = null;
        String lines;
        String[] saveTkMk;
        boolean check = false;
        if (txtUser.getText().isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ Username và Password", "Yêu cầu", 1);
        } else {
            fileRead = new FileReader(url);
            bufferedRead = new BufferedReader(fileRead);
            while ((lines = bufferedRead.readLine()) != null) {
                if(lines.equalsIgnoreCase(s)){
                    check=true;
                    break;
                }
            }
           if(check){
               this.hide();
               new GUI().setVisible(true);
               JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công!","Thông báo",1);
           }
           else{
               JOptionPane.showMessageDialog(rootPane, "Đăng nhập thất bại!","Lỗi",0);
           }
            bufferedRead.close();
            fileRead.close();
        }
    }

    public void btnCreateActionPerformed(ActionEvent e) {
        new NewWindow();
    }

}
