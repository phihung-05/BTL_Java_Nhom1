/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import model.Student;
import dao.ClockDao;
import dao.StudentDao;


public class GUI extends JFrame {

    private JMenuBar JMenubar = new JMenuBar();
    private JLabel lblTitle = new JLabel("THÔNG TIN SINH VIÊN");
    private JLabel lblThongBao = new JLabel();
    private JLabel lblMsv = new JLabel("Mã sinh viên");
    private JTextField txtMsv = new JTextField();
    private JLabel lblHoTen = new JLabel("Họ và Tên");
    private JTextField txtHoTen = new JTextField();
    private JLabel lblMaLop = new JLabel("Mã lớp");
    private JComboBox cbMaLop;
    private final DefaultComboBoxModel defaultcbMaLop = new DefaultComboBoxModel();
    private JLabel lblDiemLyThuyet = new JLabel("Điểm lý thuyết");
    private JTextField txtDiemLyThuyet = new JTextField();
    private JLabel lblDiemThucHanh = new JLabel("Điểm thực hành");
    private JTextField txtDiemThucHanh = new JTextField();
    private JLabel lblDiemTrungBinh = new JLabel("Điểm trung bình");
    private JTextField txtDiemTrungBinh = new JTextField();
    private JLabel lblKetQua = new JLabel("Kết quả");
    private JTextField txtKetQua = new JTextField();
    private JButton btnResult = new JButton("Kết quả");
    private JButton btnAdd = new JButton("Thêm");
    private JButton btnClear = new JButton("Clear");
    private JButton btnSave = new JButton("Lưu");
    private JButton btnDelete = new JButton("Xóa");
    private JButton btnFix = new JButton("Sửa");
    private JButton btnSearch = new JButton("Tìm");
    private JButton btnExit = new JButton("Thoát");
    Vector titleTbl = new Vector();
    Vector dataStudents = new Vector();
    JTable tblStudent = new JTable();
    DefaultTableModel model = (DefaultTableModel) tblStudent.getModel();
    private JButton btnFirst = new JButton("<<");
    private JButton btnUp = new JButton("<");
    private JButton btnDown = new JButton(">");
    private JButton btnEnd = new JButton(">>");
    private JLabel lblCount = new JLabel("Count: 0/0");
    private JLabel lblClock = new JLabel();
    private JLabel lblIP = new JLabel();

    public GUI() {
        actionPerformed();
        init();
    }

    public void actionPerformed() {
        btnResult.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnResultActionPerformed(e);
            }
        });
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddActionPerformed(e);
            }
        });
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearActionPerformed(e);
            }
        });
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnSaveActionPerformed(e);
            }
        });
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteActionPerformed(e);
            }
        });
        btnFix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnFixActionPerformed(e);
            }
        });
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnSearchActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnExitActionPerformed(e);
            }
        });
        tblStudent.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                loadDataFromJTable();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnFirst.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveRow(e);
            }
        });
        btnUp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveRow(e);
            }
        });
        btnDown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveRow(e);
            }
        });
        btnEnd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moveRow(e);
            }
        });

    }

    public void init() {
        setSize(940, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Chương trình Quản lý Sinh viên");
        JMenu JMenuFile = new JMenu("File");
        JMenuItem itemQLSV = new JMenuItem("QLSV");
       
        
        JMenuFile.add(itemQLSV);
   
        JMenu JMenuAbout = new JMenu("Edit");
        JMenu MenuSortByMsv = new JMenu("Sắp xếp");
        JMenuItem itemSortTang = new JMenuItem("Tăng dần");
        JMenuItem itemSortGiam = new JMenuItem("Giảm dần");
        MenuSortByMsv.add(itemSortTang);
        MenuSortByMsv.add(itemSortGiam);
        JMenuItem itemAddanewClass = new JMenuItem("Add a new class");
        JMenuAbout.add(MenuSortByMsv);
        JMenuItem itemRefresh = new JMenuItem("Restart");
        itemSortTang.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DAO.sortStudentsAscending();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    showAllData();
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        itemSortGiam.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DAO.sortStudentsDescending();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    showAllData();
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        itemAddanewClass.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddANewClass(e);
            }
        });
        itemRefresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        JMenuAbout.add(itemAddanewClass);
        JMenuAbout.add(itemRefresh);
        JMenu JMenuHelp = new JMenu("Help");
        JMenuItem itemHelp = new JMenuItem("Help");
        JMenuItem itemAbout = new JMenuItem("About");
        JMenuHelp.add(itemHelp);
        JMenuHelp.add(itemAbout);
        JMenubar.add(JMenuFile);
        JMenubar.add(JMenuAbout);
        JMenubar.add(JMenuHelp);
        setJMenuBar(JMenubar);

        Container container = getContentPane();
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(240, 0));
        JPanel pnInforStudent = new JPanel();
        pnInforStudent.setLayout(new BoxLayout(pnInforStudent, BoxLayout.Y_AXIS));
        pnInforStudent.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnLeft.add(pnInforStudent);
        JPanel pnTitle = new JPanel();
        pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnInforStudent.add(pnTitle);
        pnTitle.setPreferredSize(null);
        pnTitle.setPreferredSize(new Dimension(0, 20));
        pnTitle.add(lblTitle);
        lblTitle.setFont(new Font("Tahoma", 1, 14));
        JPanel pntb = new JPanel();
        pnInforStudent.add(pntb);
        pntb.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pntb.add(lblThongBao);
        lblThongBao.setForeground(Color.red);

        JPanel pnInput = new JPanel();
        pnInforStudent.add(pnInput);
        pnInput.setLayout(new BoxLayout(pnInput, BoxLayout.Y_AXIS));
        JPanel pnMsv = new JPanel();
        pnInput.add(pnMsv);

        pnMsv.setLayout(new BorderLayout(10, 10));
        int heightTextField = 30, widthTextField = 220;
        pnMsv.add(lblMsv, BorderLayout.NORTH);
        pnMsv.add(txtMsv);
        txtMsv.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtMsv.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel pnHoTen = new JPanel();
        pnInput.add(pnHoTen);
        pnHoTen.setLayout(new BorderLayout(20, 10));
        pnHoTen.add(lblHoTen, BorderLayout.NORTH);
        pnHoTen.add(txtHoTen);
        txtHoTen.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtHoTen.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel pnMaLop = new JPanel();
        pnInput.add(pnMaLop);
        pnMaLop.setLayout(new BorderLayout(20, 10));
        pnMaLop.add(lblMaLop, BorderLayout.NORTH);
        defaultcbMaLop.addElement("CNTT01");
        defaultcbMaLop.addElement("CNTT02");
        defaultcbMaLop.addElement("KTPM01");
        defaultcbMaLop.addElement("KTPM02");
        defaultcbMaLop.addElement("HTTT01");
        defaultcbMaLop.addElement("HTTT02");
        defaultcbMaLop.addElement("KHMT01");
        cbMaLop = new JComboBox(defaultcbMaLop);
        cbMaLop.setPreferredSize(new Dimension(widthTextField, heightTextField));
        cbMaLop.setBackground(Color.white);
        JScrollPane JScMaLop = new JScrollPane(cbMaLop);
        pnMaLop.add(JScMaLop);

        JPanel pnDlt = new JPanel();
        pnInput.add(pnDlt);
        pnDlt.setLayout(new BorderLayout(20, 10));
        pnDlt.add(lblDiemLyThuyet, BorderLayout.NORTH);
        pnDlt.add(txtDiemLyThuyet);
        txtDiemLyThuyet.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtDiemLyThuyet.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel pnDth = new JPanel();
        pnInput.add(pnDth);
        pnDth.setLayout(new BorderLayout(20, 10));
        pnDth.add(lblDiemThucHanh, BorderLayout.NORTH);
        pnDth.add(txtDiemThucHanh);
        txtDiemThucHanh.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtDiemThucHanh.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        JPanel pnDtb = new JPanel();
        pnInput.add(pnDtb);
        pnDtb.setLayout(new BorderLayout(20, 10));
        pnDtb.add(lblDiemTrungBinh, BorderLayout.NORTH);
        pnDtb.add(txtDiemTrungBinh);
        txtDiemTrungBinh.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtDiemTrungBinh.setEditable(false);

        JPanel pnKq = new JPanel();
        pnInput.add(pnKq);
        pnKq.setLayout(new BorderLayout(20, 10));
        pnKq.add(lblKetQua, BorderLayout.NORTH);
        pnKq.add(txtKetQua);
        txtKetQua.setPreferredSize(new Dimension(widthTextField, heightTextField));
        txtKetQua.setEditable(false);

        JPanel pnJButton = new JPanel();
        pnJButton.setPreferredSize(new Dimension(0, 120));
        pnInforStudent.add(pnJButton);
        pnJButton.setLayout(new GridLayout(3, 3, 5, 5));
        pnJButton.add(btnResult);
        pnJButton.add(btnAdd);
        pnJButton.add(btnClear);
        pnJButton.add(btnSave);
        pnJButton.add(btnDelete);
        pnJButton.add(btnFix);
        pnJButton.add(btnSearch);
        pnJButton.add(btnExit);

        /*JPanel Right Table*/
        JPanel pnRight = new JPanel();
        pnRight.setLayout(new BorderLayout());
        JSplitPane splitCon = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
        splitCon.setOneTouchExpandable(true);
        JPanel JPnTable = new JPanel();
        pnRight.add(JPnTable);
        JPnTable.setLayout(new BoxLayout(JPnTable, BoxLayout.Y_AXIS));
        tblStudent.setFillsViewportHeight(true);
        tblStudent.setPreferredScrollableViewportSize(new Dimension(620, 534));
        titleTbl.add("Mã SV");
        titleTbl.add("Họ tên");
        titleTbl.add("Mã lớp");
        titleTbl.add("Lý thuyết");
        titleTbl.add("Thực hành");
        titleTbl.add("Trung bình");
        titleTbl.add("Kết quả");
        model.setColumnIdentifiers(titleTbl);
        tblStudent.setAutoCreateRowSorter(true);
        tblStudent.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblStudent.setSelectionBackground(Color.BLACK);
        tblStudent.setSelectionForeground(Color.white);
        JScrollPane JScP = new JScrollPane(tblStudent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPnTable.add(JScP);
        JPanel JPnBtnTable = new JPanel();
        JPnBtnTable.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        try {
            lblIP.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblIP.setPreferredSize(new Dimension(170, 20));
        JPnBtnTable.add(lblIP);
        JPnBtnTable.add(btnFirst);
        JPnBtnTable.add(btnUp);
        lblCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblCount.setPreferredSize(new Dimension(100, 20));
        JPnBtnTable.add(lblCount);
        JPnBtnTable.add(btnDown);
        JPnBtnTable.add(btnEnd);
        lblClock.setPreferredSize(new Dimension(120, 20));
        lblClock.setHorizontalAlignment(SwingConstants.RIGHT);
        JPnBtnTable.add(lblClock);
        JPnTable.add(JPnBtnTable);

        container.add(splitCon);
        setVisible(true);
        pack();
        
        ClockDao clockDao = new ClockDao(lblClock);
        clockDao.start();
        try {
            showAllData();
            tblStudent.setRowSelectionInterval(0, 0);
            loadDataFromJTable();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadDataFromJTable() {
        lblThongBao.setText("");
        int row = tblStudent.getSelectedRow();
        String msv = tblStudent.getValueAt(row, 0).toString();
        String hoten = tblStudent.getValueAt(row, 1).toString();
        String malop = tblStudent.getValueAt(row, 2).toString();
        String dlt = tblStudent.getValueAt(row, 3).toString();
        String dth = tblStudent.getValueAt(row, 4).toString();
        String dtb = tblStudent.getValueAt(row, 5).toString();
        String kq = tblStudent.getValueAt(row, 6).toString();
        txtMsv.setText(msv);
        txtHoTen.setText(hoten);
        cbMaLop.setSelectedItem(malop);
        txtDiemLyThuyet.setText(dlt);
        txtDiemThucHanh.setText(dth);
        txtDiemTrungBinh.setText(dtb);
        txtKetQua.setText(kq);
        lblCount.setText("" + "Count: " + (row + 1) + "/" + tblStudent.getRowCount());
    }

    public void moveRow(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        int row = tblStudent.getSelectedRow();
        switch (cmd) {
            case "<<":
                lblThongBao.setText("");
                tblStudent.setRowSelectionInterval(0, 0);
                lblCount.setText("" + "Count: 1" + "/" + tblStudent.getRowCount());
                loadDataFromJTable();
                break;
            case "<":
                lblThongBao.setText("");
                try {
                    if (row == 0) {
                        tblStudent.setRowSelectionInterval(tblStudent.getRowCount() - 1, tblStudent.getRowCount() - 1);
                        lblCount.setText("" + "Count: " + tblStudent.getRowCount() + "/" + tblStudent.getRowCount());
                    } else {
                        tblStudent.setRowSelectionInterval(row - 1, row - 1);
                        lblCount.setText("" + "Count: " + row + "/" + tblStudent.getRowCount());
                    }
                } catch (Exception e) {
                    tblStudent.setRowSelectionInterval(0, 0);
                }
                loadDataFromJTable();
                break;
            case ">":
                lblThongBao.setText("");
                try {
                    tblStudent.setRowSelectionInterval(row + 1, row + 1);
                    lblCount.setText("" + "Count: " + (tblStudent.getSelectedRow() + 1) + "/" + tblStudent.getRowCount());
                } catch (Exception e) {
                    lblCount.setText("" + "Count: 1" + "/" + tblStudent.getRowCount());
                    tblStudent.setRowSelectionInterval(0, 0);
                }
                loadDataFromJTable();
                break;
            case ">>":
                lblThongBao.setText("");
                tblStudent.setRowSelectionInterval(tblStudent.getRowCount() - 1, tblStudent.getRowCount() - 1);
                lblCount.setText("" + "Count: " + tblStudent.getRowCount() + "/" + tblStudent.getRowCount());
                loadDataFromJTable();
                break;
            default:
                break;
        }
    }

    public void refresh() {
        this.hide();
        new GUI().setVisible(true);
    }

    

    public void AddANewClass(ActionEvent e) {
        String addClass = JOptionPane.showInputDialog(rootPane, "Nhập mã lớp", "Thêm lớp", JOptionPane.OK_CANCEL_OPTION);
        defaultcbMaLop.addElement(addClass);
        cbMaLop.setSelectedItem(addClass);
    }

    public void showAllData() throws IOException {
        lblThongBao.setText("");
        ((DefaultTableModel) tblStudent.getModel()).setRowCount(0);
        String url = "data_Students.txt";
        File file = new File(url);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(file));
            Object[] tableLines = buffRead.lines().toArray();
            for (int i = 0; i < tableLines.length; i++) {
                String lines = tableLines[i].toString().trim();
                String dataRow[] = lines.split(";");
                Vector addData = new Vector();
                addData.add(dataRow[0]);
                addData.add(dataRow[1]);
                addData.add(dataRow[2]);
                addData.add(dataRow[3]);
                addData.add(dataRow[4]);
                addData.add(dataRow[5]);
                addData.add(dataRow[6]);
                dataStudents.add(addData);
                tblStudent.setModel(new DefaultTableModel(dataStudents, titleTbl));
            }
            lblCount.setText("" + "Count: 0" + "/" + tblStudent.getRowCount());
        } catch (Exception e) {

        }

    }

    StudentDao DAO = new StudentDao();

    public boolean isNumberic(String str) {
        int countDOT = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                countDOT++;
            }
        }
        if (countDOT == 1 || countDOT == 0) {
            if (str.contains(".")) {
                str = str.replace(".", "0");
            }
        } else {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {

            } else {
                return false;
            }
        }
        return true;
    }

    public void resetForm() {
        txtMsv.setText(null);
        txtHoTen.setText(null);
        txtDiemLyThuyet.setText(null);
        txtDiemTrungBinh.setText(null);
        txtDiemThucHanh.setText(null);
        txtDiemTrungBinh.setText(null);
        txtKetQua.setText(null);

    }

    public boolean checkForm() {
        if (txtMsv.getText().isEmpty() || txtHoTen.getText().isEmpty() || txtDiemLyThuyet.getText().isEmpty() || txtDiemThucHanh.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public void fillTable() {
        Vector record = new Vector();
        record.add(txtMsv.getText());
        record.add(txtHoTen.getText());
        if (cbMaLop.getSelectedIndex() != -1) {
            record.add((String) cbMaLop.getItemAt(cbMaLop.getSelectedIndex()));
        }
        record.add(txtDiemLyThuyet.getText());
        record.add(txtDiemThucHanh.getText());
        Double dlt = Double.parseDouble(txtDiemLyThuyet.getText());
        Double dth = Double.parseDouble(txtDiemThucHanh.getText());
        Double result = (dlt + dth) / 2;
        record.add(String.valueOf(result));
        if (result >= 5) {
            record.add("Passed");
        } else {
            record.add("Failed");
        }
        dataStudents.add(record);
        tblStudent.setModel(new DefaultTableModel(dataStudents, titleTbl));
    }

    public void btnResultActionPerformed(ActionEvent e) {
        if (!isNumberic(txtDiemLyThuyet.getText()) || !isNumberic(txtDiemThucHanh.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Điểm phải là 1 số!");
            return;
        }
        if (checkForm()) {
            Double diemlythuyet = Double.parseDouble(txtDiemLyThuyet.getText());
            Double diemthuchanh = Double.parseDouble(txtDiemThucHanh.getText());
            if (diemlythuyet < 0 || diemthuchanh > 10) {
                JOptionPane.showMessageDialog(rootPane, "Điểm phải >=0 và <=10.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Double result = 0.00;
            result = (diemlythuyet + diemthuchanh) / 2;
            txtDiemTrungBinh.setText(String.valueOf(result));
            if (result >= 5) {
                txtKetQua.setForeground(Color.black);
                txtKetQua.setText("Passed");
            } else {
                txtKetQua.setForeground(Color.red);
                txtKetQua.setText("Failed");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ các trường thông tin", "Thông báo", 1);
        }
    }

    public void btnAddActionPerformed(ActionEvent e) {
        lblThongBao.setText("Bạn đang thêm mới 1 sinh viên");
        txtMsv.setEditable(true);
        resetForm();
    }

    public void btnClearActionPerformed(ActionEvent e) {
        resetForm();
        cbMaLop.setSelectedIndex(0);
    }

    public void btnSaveActionPerformed(ActionEvent evt) {
        if (!isNumberic(txtDiemLyThuyet.getText()) || !isNumberic(txtDiemThucHanh.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Điểm phải là 1 số!");
            return;
        }
        if (DAO.findMsv(txtMsv.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Sinh viên " + txtMsv.getText() + " đã có trong danh sách", "Thông báo", 1);
            return;
        }
        if (checkForm()) {
            // Tạo đối tượng Student
            Student student = new Student(
                    txtMsv.getText(),
                    txtHoTen.getText(),
                    (String) cbMaLop.getSelectedItem(),
                    Double.parseDouble(txtDiemLyThuyet.getText()),
                    Double.parseDouble(txtDiemThucHanh.getText())
            );

            try {
                DAO.addStudent(student);
                showAllData(); // Hiển thị lại dữ liệu
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            lblThongBao.setText("");
            JOptionPane.showMessageDialog(rootPane, "Đã thêm Sinh viên " + txtMsv.getText() + " vào danh sách.", "Thông báo", 1);
            DAO.setSelectedTbl(tblStudent, txtMsv.getText()); // Chọn sinh viên vừa thêm vào bảng
        } else {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đủ các trường thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void btnDeleteActionPerformed(ActionEvent e) {
        try {
            String sv = JOptionPane.showInputDialog(rootPane, "Nhập mã sinh viên", "Xóa sinh viên", 1);

            if (sv != null && !sv.isEmpty()) {
                if (DAO.findMsv(sv) > 0) {
                    if (DAO.deleteStudent(sv) == 1) {
                        try {
                            showAllData();
                        } catch (IOException ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(rootPane, "Đã xóa sinh viên " + sv + " khỏi danh sách.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                        tblStudent.setRowSelectionInterval(0, 0);
                        loadDataFromJTable();
                        lblCount.setText("Count: " + tblStudent.getRowCount());
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Sinh viên " + sv + " không tồn tại trong danh sách.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Mã sinh viên không hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Đã xảy ra lỗi khi xóa sinh viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        resetForm();
    }


    public void btnFixActionPerformed(ActionEvent e) {
        String oldMsv = tblStudent.getValueAt(tblStudent.getSelectedRow(), 0).toString();

        if (!isNumberic(txtDiemLyThuyet.getText()) || !isNumberic(txtDiemThucHanh.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Điểm phải là 1 số!");
            return;
        }
        if (!checkForm()) {
            return;
        }

        if (!txtMsv.getText().equals(oldMsv)) {
            JOptionPane.showMessageDialog(rootPane, "Không được sửa Mã sinh viên", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Student student = new Student(
                txtMsv.getText(),
                txtHoTen.getText(),
                (String) cbMaLop.getSelectedItem(),
                Double.parseDouble(txtDiemLyThuyet.getText()),
                Double.parseDouble(txtDiemThucHanh.getText())
        );

        if (DAO.updateStudent(student) == 1) {
            if (JOptionPane.showConfirmDialog(rootPane, "Đồng ý sửa sinh viên " + txtMsv.getText() + "?", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                txtMsv.setEditable(true);
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Error!");
        }
        try {
            showAllData();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnSearchActionPerformed(ActionEvent evt) throws IOException {
        resetForm();
        try {
            String userSelected = JOptionPane.showInputDialog(null, "Nhập mã sinh viên", "Tìm kiếm", 1);
            if (userSelected == null) {
                return;
            }
            String svFind;
            if ((svFind = DAO.search(userSelected)) != null) {
                String[] tachChuoi = svFind.split(";");
                txtMsv.setText(tachChuoi[0]);
                txtHoTen.setText(tachChuoi[1]);
                cbMaLop.setSelectedItem(tachChuoi[2]);
                txtDiemLyThuyet.setText(tachChuoi[3]);
                txtDiemThucHanh.setText(tachChuoi[4]);
                Double kq = (Double.parseDouble(tachChuoi[3]) + Double.parseDouble(tachChuoi[4])) / 2;
                txtDiemTrungBinh.setText(String.valueOf(kq));
                if (kq >= 5) {
                    txtKetQua.setText("Passed");
                } else {
                    txtKetQua.setText("Failed");
                }

                DAO.setSelectedTbl(tblStudent, txtMsv.getText());
                lblCount.setText("" + "Count: " + (tblStudent.getSelectedRow() + 1) + "/" + tblStudent.getRowCount());
            } else {
                JOptionPane.showMessageDialog(rootPane, "Không tìm thấy " + userSelected.toString(), "Not Found", 1);
            }
        } catch (Error error) {
            System.out.println(error);
        }
    }

    public void btnExitActionPerformed(ActionEvent e) {
        if (DAO.findMsv(txtMsv.getText()) == 1 || txtMsv.getText().isEmpty()) {
            System.exit(0);
        } else {
            if (JOptionPane.showConfirmDialog(rootPane, "Sinh viên " + txtMsv.getText() + " chưa được lưu? Đồng ý thoát", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
