package dao;

import model.Student;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao {

    private static final String FILE_NAME = "data_Students.txt";
    private File file = new File(FILE_NAME);

    public StudentDao() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Student> loadAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 7) {
                    String msv = data[0];
                    String hoten = data[1];
                    String malop = data[2];
                    double diemlythuyet = Double.parseDouble(data[3]);
                    double diemthuchanh = Double.parseDouble(data[4]);
                    students.add(new Student(msv, hoten, malop, diemlythuyet, diemthuchanh));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student findStudentByMsv(String msv) {
        List<Student> students = loadAllStudents();
        for (Student student : students) {
            if (student.getMsv().equalsIgnoreCase(msv)) {
                return student;
            }
        }
        return null;
    }

    public void addStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(student.toString());
            writer.newLine();
        }
    }

    public int updateStudent(Student updatedStudent) {
        List<Student> students = loadAllStudents();
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMsv().equals(updatedStudent.getMsv())) {
                students.set(i, updatedStudent);
                found = true;
                break;
            }
        }
        if (found) {
            writeAllStudents(students);

            return 1;
        }

        return 0;
    }

    public int deleteStudent(String msv) throws IOException {
        List<Student> students = loadAllStudents();
        students.removeIf(student -> student.getMsv().equals(msv));
        writeAllStudents(students);
        return 1;
    }

    private void writeAllStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sortStudentsAscending() throws IOException {
        List<Student> students = loadAllStudents();
        students.sort(Comparator.comparing(Student::getMsv));
        writeAllStudents(students);
    }

    public void sortStudentsDescending() throws IOException {
        List<Student> students = loadAllStudents();
        students.sort((s1, s2) -> s2.getMsv().compareTo(s1.getMsv()));
        writeAllStudents(students);
    }

    public int findMsv(String text) {
        List<Student> students = loadAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getMsv().equalsIgnoreCase(text)) {
                return i;
            }
        }
        return -1;
    }

    public void setSelectedTbl(JTable tblStudent, String text) {
        try {
            int rows = tblStudent.getRowCount();
            for (int i = 0; i < rows; i++) {
                Object value = tblStudent.getValueAt(i, 0);
                if (value != null && value.toString().equals(text)) {
                    tblStudent.setRowSelectionInterval(i, i);
                    tblStudent.scrollRectToVisible(tblStudent.getCellRect(i, 0, true));
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String search(String userSelected) {
        List<Student> students = loadAllStudents();
        for (Student student : students) {
            if (student.getMsv().equalsIgnoreCase(userSelected)) {
                return student.toString();
            }
        }
        return null;
    }
}
