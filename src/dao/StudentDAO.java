package dao;

import database.DBConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection conn;

    public StudentDAO() {
        this.conn = DBConnection.getConnection();
    }

    // ─────────────────────────────────────────
    // 1. ADD STUDENT  (INSERT)
    // ─────────────────────────────────────────
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students (name, email, phone, dept_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setInt   (4, s.getDeptId());

            int rows = ps.executeUpdate();
            return rows > 0;  // true if insert was successful

        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 2. VIEW ALL STUDENTS  (SELECT)
    // ─────────────────────────────────────────
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Read each column from the result row
                Student s = new Student(
                    rs.getInt   ("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getInt   ("dept_id")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching students: " + e.getMessage());
        }
        return list;
    }

    // ─────────────────────────────────────────
    // 3. SEARCH STUDENT BY ID  (SELECT WHERE)
    // ─────────────────────────────────────────
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getInt   ("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getInt   ("dept_id")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error finding student: " + e.getMessage());
        }
        return null; // student not found
    }

    // ─────────────────────────────────────────
    // 4. UPDATE STUDENT  (UPDATE)
    // ─────────────────────────────────────────
    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, dept_id=? WHERE student_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getPhone());
            ps.setInt   (4, s.getDeptId());
            ps.setInt   (5, s.getStudentId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 5. DELETE STUDENT  (DELETE)
    // ─────────────────────────────────────────
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
            return false;
        }
    }
}