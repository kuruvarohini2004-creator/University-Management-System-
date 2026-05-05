package models;

public class Student {

    // Fields — match exactly with DB columns
    private int    studentId;
    private String name;
    private String email;
    private String phone;
    private int    deptId;

    // --- Constructor (used when fetching from DB) ---
    public Student(int studentId, String name, String email, String phone, int deptId) {
        this.studentId = studentId;
        this.name      = name;
        this.email     = email;
        this.phone     = phone;
        this.deptId    = deptId;
    }

    // --- Constructor (used when adding new student, no ID yet) ---
    public Student(String name, String email, String phone, int deptId) {
        this.name   = name;
        this.email  = email;
        this.phone  = phone;
        this.deptId = deptId;
    }

    // --- Getters ---
    public int    getStudentId() { return studentId; }
    public String getName()      { return name; }
    public String getEmail()     { return email; }
    public String getPhone()     { return phone; }
    public int    getDeptId()    { return deptId; }

    // --- Setters ---
    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDeptId(int deptId)  { this.deptId = deptId; }

    // --- Display nicely in console ---
    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-25s | %-12s | %-6d |",
                studentId, name, email, phone, deptId);
    }
}