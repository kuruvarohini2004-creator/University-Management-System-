package menus;

import dao.StudentDAO;
import models.Student;

import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    private StudentDAO dao = new StudentDAO();
    private Scanner sc;

    public StudentMenu(Scanner sc) {
        this.sc = sc;
    }

    public void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║      STUDENT MENU        ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║  1. Add Student          ║");
            System.out.println("║  2. View All Students    ║");
            System.out.println("║  3. Search Student by ID ║");
            System.out.println("║  4. Update Student       ║");
            System.out.println("║  5. Delete Student       ║");
            System.out.println("║  0. Back to Main Menu    ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 0:
                    System.out.println("↩ Returning to main menu...");
                    break;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter name       : ");
        String name = sc.nextLine();
        System.out.print("Enter email      : ");
        String email = sc.nextLine();
        System.out.print("Enter phone      : ");
        String phone = sc.nextLine();
        System.out.print("Enter dept ID    : ");
        int deptId = sc.nextInt();
        sc.nextLine();

        Student s = new Student(name, email, phone, deptId);
        if (dao.addStudent(s))
            System.out.println("✅ Student added successfully!");
        else
            System.out.println("❌ Failed to add student.");
    }

    private void viewStudents() {
        List<Student> list = dao.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("⚠ No students found.");
            return;
        }
        System.out.println("\n+------+----------------------+---------------------------+--------------+--------+");
        System.out.println("| ID   | Name                 | Email                     | Phone        | DeptID |");
        System.out.println("+------+----------------------+---------------------------+--------------+--------+");
        for (Student s : list)
            System.out.println(s);
        System.out.println("+------+----------------------+---------------------------+--------------+--------+");
    }

    private void searchStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student s = dao.getStudentById(id);
        if (s != null) {
            System.out.println("\nStudent found:");
            System.out.println(s);
        } else {
            System.out.println("⚠ No student found with ID: " + id);
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student existing = dao.getStudentById(id);
        if (existing == null) {
            System.out.println("⚠ Student not found.");
            return;
        }

        System.out.println("Current: " + existing);
        System.out.print("New name  (or press Enter to keep): ");
        String name = sc.nextLine();
        System.out.print("New email (or press Enter to keep): ");
        String email = sc.nextLine();
        System.out.print("New phone (or press Enter to keep): ");
        String phone = sc.nextLine();

        // Keep old value if user presses Enter
        if (!name.trim().isEmpty())  existing.setName(name);
        if (!email.trim().isEmpty()) existing.setEmail(email);
        if (!phone.trim().isEmpty()) existing.setPhone(phone);

        if (dao.updateStudent(existing))
            System.out.println("✅ Student updated!");
        else
            System.out.println("❌ Update failed.");
    }

    private void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (dao.deleteStudent(id))
            System.out.println("✅ Student deleted!");
        else
            System.out.println("❌ Delete failed. Check if ID exists.");
    }
}