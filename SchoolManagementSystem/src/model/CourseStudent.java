package model;

public class CourseStudent {
    private int id;
    private int courseId;
    private int studentId;
    private String nationalCode;

    public CourseStudent(int id, int courseId, int studentId, String nationalCode) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.nationalCode = nationalCode;
    }

    public CourseStudent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
