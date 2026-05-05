package dao;

import database.DBConnection;
import models.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    private Connection conn;

    public EnrollmentDAO() {
        this.conn = DBConnection.getConnection();
    }

    // ─────────────────────────────────────────
    // 1. ENROLL STUDENT INTO COURSE
    // ─────────────────────────────────────────
    public boolean enrollStudent(Enrollment e) {
        // First check if already enrolled
        if (isAlreadyEnrolled(e.getStudentId(), e.getCourseId())) {
            System.out.println("⚠ Student is already enrolled in this course.");
            return false;
        }

        String sql = "INSERT INTO enrollments (student_id, course_id, enroll_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt   (1, e.getStudentId());
            ps.setInt   (2, e.getCourseId());
            ps.setString(3, e.getEnrollDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("❌ Error enrolling student: " + ex.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 2. CHECK IF ALREADY ENROLLED
    // ─────────────────────────────────────────
    private boolean isAlreadyEnrolled(int studentId, int courseId) {
        String sql = "SELECT * FROM enrollments WHERE student_id=? AND course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if a row exists
        } catch (SQLException e) {
            System.out.println("❌ Error checking enrollment: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 3. VIEW ALL ENROLLMENTS  (JOIN QUERY)
    // ─────────────────────────────────────────
    public void printAllEnrollments() {
        // JOIN brings student name and course name together
        String sql = "SELECT e.enrollment_id, s.name AS student_name, " +
                     "c.course_name, e.enroll_date " +
                     "FROM enrollments e " +
                     "JOIN students s ON e.student_id = s.student_id " +
                     "JOIN courses  c ON e.course_id  = c.course_id";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n+------+----------------------+---------------------------+--------------+");
            System.out.println("| ID   | Student Name         | Course Name               | Enroll Date  |");
            System.out.println("+------+----------------------+---------------------------+--------------+");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("| %-4d | %-20s | %-25s | %-12s |%n",
                    rs.getInt   ("enrollment_id"),
                    rs.getString("student_name"),
                    rs.getString("course_name"),
                    rs.getString("enroll_date"));
            }

            if (!found)
                System.out.println("⚠ No enrollments found.");

            System.out.println("+------+----------------------+---------------------------+--------------+");

        } catch (SQLException e) {
            System.out.println("❌ Error fetching enrollments: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // 4. VIEW COURSES OF ONE STUDENT  (JOIN)
    // ─────────────────────────────────────────
    public void printCoursesByStudent(int studentId) {
        String sql = "SELECT c.course_name, c.credits, e.enroll_date " +
                     "FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.course_id " +
                     "WHERE e.student_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n+---------------------------+---------+--------------+");
            System.out.println("| Course Name               | Credits | Enroll Date  |");
            System.out.println("+---------------------------+---------+--------------+");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("| %-25s | %-7d | %-12s |%n",
                    rs.getString("course_name"),
                    rs.getInt   ("credits"),
                    rs.getString("enroll_date"));
            }

            if (!found)
                System.out.println("⚠ No courses found for this student.");

            System.out.println("+---------------------------+---------+--------------+");

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // 5. CANCEL ENROLLMENT
    // ─────────────────────────────────────────
    public boolean cancelEnrollment(int enrollmentId) {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error cancelling enrollment: " + e.getMessage());
            return false;
        }
    }
}