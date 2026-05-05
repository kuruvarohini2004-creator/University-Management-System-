package models;

public class Department {

    private int    deptId;
    private String deptName;
    private String location;

    public Department(int deptId, String deptName, String location) {
        this.deptId   = deptId;
        this.deptName = deptName;
        this.location = location;
    }

    public Department(String deptName, String location) {
        this.deptName = deptName;
        this.location = location;
    }

    public int    getDeptId()   { return deptId; }
    public String getDeptName() { return deptName; }
    public String getLocation() { return location; }

    public void setDeptName(String deptName) { this.deptName = deptName; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-20s |", deptId, deptName, location);
    }
}