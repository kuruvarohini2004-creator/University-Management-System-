package main;

import database.DBConnection;
import menus.CourseMenu;
import menus.DepartmentMenu;
import menus.EnrollmentMenu;
import menus.StudentMenu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentMenu deptMenu       = new DepartmentMenu(sc);
        StudentMenu    studentMenu    = new StudentMenu(sc);
        CourseMenu     courseMenu     = new CourseMenu(sc);
        EnrollmentMenu enrollmentMenu = new EnrollmentMenu(sc);

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║   UNIVERSITY MANAGEMENT SYSTEM   ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║  1. Department Management        ║");
            System.out.println("║  2. Student Management           ║");
            System.out.println("║  3. Course Management            ║");
            System.out.println("║  4. Enrollment Management        ║");
            System.out.println("║  0. Exit                         ║");
            System.out.println("╚══════════════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    deptMenu.show();
                    break;
                case 2:
                    studentMenu.show();
                    break;
                case 3:
                    courseMenu.show();
                    break;
                case 4:
                    enrollmentMenu.show();
                    break;
                case 0:
                    System.out.println("👋 Goodbye!");
                    DBConnection.closeConnection();
                    break;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }

        sc.close();
    }
}