package models;

public class Enrollment {

    private int    enrollmentId;
    private int    studentId;
    private int    courseId;
    private String enrollDate;

    public Enrollment(int enrollmentId, int studentId, int courseId, String enrollDate) {
        this.enrollmentId = enrollmentId;
        this.studentId    = studentId;
        this.courseId     = courseId;
        this.enrollDate   = enrollDate;
    }

    public Enrollment(int studentId, int courseId, String enrollDate) {
        this.studentId  = studentId;
        this.courseId   = courseId;
        this.enrollDate = enrollDate;
    }

    public int    getEnrollmentId() { return enrollmentId; }
    public int    getStudentId()    { return studentId; }
    public int    getCourseId()     { return courseId; }
    public String getEnrollDate()   { return enrollDate; }

    @Override
    public String toString() {
        return String.format("| %-6d | %-10d | %-10d | %-12s |",
                enrollmentId, studentId, courseId, enrollDate);
    }
}