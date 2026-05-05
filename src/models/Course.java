package models;

public class Course {

    private int    courseId;
    private String courseName;
    private int    credits;
    private int    deptId;

    public Course(int courseId, String courseName, int credits, int deptId) {
        this.courseId   = courseId;
        this.courseName = courseName;
        this.credits    = credits;
        this.deptId     = deptId;
    }

    public Course(String courseName, int credits, int deptId) {
        this.courseName = courseName;
        this.credits    = credits;
        this.deptId     = deptId;
    }

    public int    getCourseId()   { return courseId; }
    public String getCourseName() { return courseName; }
    public int    getCredits()    { return credits; }
    public int    getDeptId()     { return deptId; }

    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCredits(int credits)          { this.credits = credits; }
    public void setDeptId(int deptId)            { this.deptId = deptId; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-25s | %-7d | %-6d |",
                courseId, courseName, credits, deptId);
    }
}