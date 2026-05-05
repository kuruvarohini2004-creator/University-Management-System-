package dao;

import database.DBConnection;
import models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection conn;

    public CourseDAO() {
        this.conn = DBConnection.getConnection();
    }

    // ─────────────────────────────────────────
    // 1. ADD COURSE
    // ─────────────────────────────────────────
    public boolean addCourse(Course c) {
        String sql = "INSERT INTO courses (course_name, credits, dept_id) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCourseName());
            ps.setInt   (2, c.getCredits());
            ps.setInt   (3, c.getDeptId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error adding course: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 2. VIEW ALL COURSES
    // ─────────────────────────────────────────
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(
                    rs.getInt   ("course_id"),
                    rs.getString("course_name"),
                    rs.getInt   ("credits"),
                    rs.getInt   ("dept_id")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching courses: " + e.getMessage());
        }
        return list;
    }

    // ─────────────────────────────────────────
    // 3. UPDATE COURSE
    // ─────────────────────────────────────────
    public boolean updateCourse(Course c) {
        String sql = "UPDATE courses SET course_name=?, credits=?, dept_id=? WHERE course_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCourseName());
            ps.setInt   (2, c.getCredits());
            ps.setInt   (3, c.getDeptId());
            ps.setInt   (4, c.getCourseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error updating course: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 4. DELETE COURSE
    // ─────────────────────────────────────────
    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error deleting course: " + e.getMessage());
            return false;
        }
    }

    // ─────────────────────────────────────────
    // 5. GET COURSE BY ID
    // ─────────────────────────────────────────
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Course(
                    rs.getInt   ("course_id"),
                    rs.getString("course_name"),
                    rs.getInt   ("credits"),
                    rs.getInt   ("dept_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error finding course: " + e.getMessage());
        }
        return null;
    }
}