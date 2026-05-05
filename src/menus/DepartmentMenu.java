package menus;

import dao.DepartmentDAO;
import models.Department;

import java.util.List;
import java.util.Scanner;

public class DepartmentMenu {

    private DepartmentDAO dao = new DepartmentDAO();
    private Scanner sc;

    public DepartmentMenu(Scanner sc) {
        this.sc = sc;
    }

    // Called from Main — shows the department sub-menu
    public void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║     DEPARTMENT MENU      ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║  1. Add Department       ║");
            System.out.println("║  2. View All Departments ║");
            System.out.println("║  3. Delete Department    ║");
            System.out.println("║  0. Back to Main Menu    ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    viewDepartments();
                    break;
                case 3:
                    deleteDepartment();
                    break;
                case 0:
                    System.out.println("↩ Returning to main menu...");
                    break;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }

    private void addDepartment() {
        System.out.print("Enter department name: ");
        String name = sc.nextLine();
        System.out.print("Enter location: ");
        String loc = sc.nextLine();

        Department d = new Department(name, loc);
        if (dao.addDepartment(d))
            System.out.println("✅ Department added!");
        else
            System.out.println("❌ Failed to add department.");
    }

    private void viewDepartments() {
        List<Department> list = dao.getAllDepartments();
        if (list.isEmpty()) {
            System.out.println("⚠ No departments found.");
            return;
        }
        System.out.println("\n+------+----------------------+----------------------+");
        System.out.println("| ID   | Department Name      | Location             |");
        System.out.println("+------+----------------------+----------------------+");
        for (Department d : list)
            System.out.println(d);
        System.out.println("+------+----------------------+----------------------+");
    }

    private void deleteDepartment() {
        viewDepartments();
        System.out.print("Enter department ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (dao.deleteDepartment(id))
            System.out.println("✅ Department deleted!");
        else
            System.out.println("❌ Delete failed. Check if ID exists.");
    }
}