package menus;

import dao.EnrollmentDAO;
import models.Enrollment;

import java.util.Scanner;

public class EnrollmentMenu {

    private EnrollmentDAO dao = new EnrollmentDAO();
    private Scanner sc;

    public EnrollmentMenu(Scanner sc) {
        this.sc = sc;
    }

    public void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║       ENROLLMENT MENU          ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║  1. Enroll Student in Course   ║");
            System.out.println("║  2. View All Enrollments       ║");
            System.out.println("║  3. View Courses by Student    ║");
            System.out.println("║  4. Cancel Enrollment          ║");
            System.out.println("║  0. Back to Main Menu          ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    dao.printAllEnrollments();
                    break;
                case 3:
                    viewByStudent();
                    break;
                case 4:
                    cancelEnrollment();
                    break;
                case 0:
                    System.out.println("↩ Returning to main menu...");
                    break;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }

    private void enrollStudent() {
        System.out.print("Enter student ID : ");
        int studentId = sc.nextInt();
        System.out.print("Enter course ID  : ");
        int courseId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        Enrollment e = new Enrollment(studentId, courseId, date);
        if (dao.enrollStudent(e))
            System.out.println("✅ Student enrolled successfully!");
        else
            System.out.println("❌ Enrollment failed.");
    }

    private void viewByStudent() {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.printCoursesByStudent(id);
    }

    private void cancelEnrollment() {
        dao.printAllEnrollments();
        System.out.print("Enter enrollment ID to cancel: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (dao.cancelEnrollment(id))
            System.out.println("✅ Enrollment cancelled!");
        else
            System.out.println("❌ Cancel failed.");
    }
}