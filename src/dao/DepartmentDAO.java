package dao;

import database.DBConnection;
import models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private Connection conn;

    public DepartmentDAO() {
        this.conn = DBConnection.getConnection();
    }

    public boolean addDepartment(Department d) {
        String sql = "INSERT INTO departments (dept_name, location) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getDeptName());
            ps.setString(2, d.getLocation());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
            return false;
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                    rs.getInt("dept_id"),
                    rs.getString("dept_name"),
                    rs.getString("location")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        return list;
    }

    public boolean deleteDepartment(int id) {
        String sql = "DELETE FROM departments WHERE dept_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
            return false;
        }
    }
}