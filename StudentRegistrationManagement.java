import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class Collage {
    public final static String c_name = "GPA";
}

class Student {
    String enrollmentNo;
    String name;
    float spi;
    float cgpa;

    public Student(String enrollmentNo, String name, float spi, float cgpa) {
        this.enrollmentNo = enrollmentNo;
        this.name = name;
        this.spi = spi;
        this.cgpa = cgpa;
    }
}

public class StudentRegistrationManagement {

    private static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton collageNameBtn = new JButton("Print Collage Name");
        JButton addStudentBtn = new JButton("Add Student");
        JButton checkDetailsBtn = new JButton("Check Details");
        JButton printAllBtn = new JButton("Print All Students");
        JButton checkResultBtn = new JButton("Check Result");
        JButton top3Btn = new JButton("Top 3 Students");

        panel.add(collageNameBtn);
        panel.add(addStudentBtn);
        panel.add(checkDetailsBtn);
        panel.add(printAllBtn);
        panel.add(checkResultBtn);
        panel.add(top3Btn);

        frame.add(panel, BorderLayout.NORTH);

        // Text area to display output
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listeners for buttons
        collageNameBtn.addActionListener(e -> {
            outputArea.setText("Collage Name: " + Collage.c_name);
        });

        addStudentBtn.addActionListener(e -> {
            String enrollmentNo = JOptionPane.showInputDialog("Enter Enrollment Number:");
            String name = JOptionPane.showInputDialog("Enter Name:");
            float spi = Float.parseFloat(JOptionPane.showInputDialog("Enter SPI:"));
            float cgpa = Float.parseFloat(JOptionPane.showInputDialog("Enter CGPA:"));

            studentList.add(new Student(enrollmentNo, name, spi, cgpa));
            outputArea.setText("Student Added Successfully!");
        });

        checkDetailsBtn.addActionListener(e -> {
            String enrollmentNo = JOptionPane.showInputDialog("Enter Enrollment Number:");
            Student student = studentList.stream()
                .filter(s -> s.enrollmentNo.equals(enrollmentNo))
                .findFirst()
                .orElse(null);

            if (student != null) {
                outputArea.setText("Enrollment No: " + student.enrollmentNo + "\nName: " + student.name +
                        "\nSPI: " + student.spi + "\nCGPA: " + student.cgpa);
            } else {
                outputArea.setText("Student not found.");
            }
        });

        printAllBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            for (Student student : studentList) {
                sb.append("Enrollment No: ").append(student.enrollmentNo).append("  Name: ").append(student.name)
                        .append("  SPI: ").append(student.spi).append("  CGPA: ").append(student.cgpa).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        checkResultBtn.addActionListener(e -> {
            String enrollmentNo = JOptionPane.showInputDialog("Enter Enrollment Number:");
            Student student = studentList.stream()
                .filter(s -> s.enrollmentNo.equals(enrollmentNo))
                .findFirst()
                .orElse(null);

            if (student != null) {
                outputArea.setText("SPI: " + student.spi + "\nCGPA: " + student.cgpa);
            } else {
                outputArea.setText("Student not found.");
            }
        });

        top3Btn.addActionListener(e -> {
            studentList.sort((s1, s2) -> Float.compare(s2.spi, s1.spi));
            StringBuilder sb = new StringBuilder("Top 3 Students:\n");
            for (int i = 0; i < Math.min(3, studentList.size()); i++) {
                Student student = studentList.get(i);
                sb.append("Enrollment No: ").append(student.enrollmentNo).append("  Name: ").append(student.name)
                        .append("  SPI: ").append(student.spi).append("  CGPA: ").append(student.cgpa).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        frame.setVisible(true);
    }
}
