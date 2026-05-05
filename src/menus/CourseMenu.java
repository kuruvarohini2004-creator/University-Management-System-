package menus;

import dao.CourseDAO;
import models.Course;

import java.util.List;
import java.util.Scanner;

public class CourseMenu {

    private CourseDAO dao = new CourseDAO();
    private Scanner sc;

    public CourseMenu(Scanner sc) {
        this.sc = sc;
    }

    public void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║      COURSE MENU         ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║  1. Add Course           ║");
            System.out.println("║  2. View All Courses     ║");
            System.out.println("║  3. Update Course        ║");
            System.out.println("║  4. Delete Course        ║");
            System.out.println("║  0. Back to Main Menu    ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 0:
                    System.out.println("↩ Returning to main menu...");
                    break;
                default:
                    System.out.println("⚠ Invalid choice.");
            }
        }
    }

    private void addCourse() {
        System.out.print("Enter course name : ");
        String name = sc.nextLine();
        System.out.print("Enter credits     : ");
        int credits = sc.nextInt();
        System.out.print("Enter dept ID     : ");
        int deptId = sc.nextInt();
        sc.nextLine();

        Course c = new Course(name, credits, deptId);
        if (dao.addCourse(c))
            System.out.println("✅ Course added!");
        else
            System.out.println("❌ Failed to add course.");
    }

    public void viewCourses() {
        List<Course> list = dao.getAllCourses();
        if (list.isEmpty()) {
            System.out.println("⚠ No courses found.");
            return;
        }
        System.out.println("\n+------+---------------------------+---------+--------+");
        System.out.println("| ID   | Course Name               | Credits | DeptID |");
        System.out.println("+------+---------------------------+---------+--------+");
        for (Course c : list)
            System.out.println(c);
        System.out.println("+------+---------------------------+---------+--------+");
    }

    private void updateCourse() {
        System.out.print("Enter course ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course existing = dao.getCourseById(id);
        if (existing == null) {
            System.out.println("⚠ Course not found.");
            return;
        }

        System.out.println("Current: " + existing);
        System.out.print("New course name (or press Enter to keep): ");
        String name = sc.nextLine();
        System.out.print("New credits     (0 to keep)             : ");
        int credits = sc.nextInt();
        sc.nextLine();

        if (!name.trim().isEmpty())  existing.setCourseName(name);
        if (credits != 0)            existing.setCredits(credits);

        if (dao.updateCourse(existing))
            System.out.println("✅ Course updated!");
        else
            System.out.println("❌ Update failed.");
    }

    private void deleteCourse() {
        viewCourses();
        System.out.print("Enter course ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (dao.deleteCourse(id))
            System.out.println("✅ Course deleted!");
        else
            System.out.println("❌ Delete failed.");
    }
}