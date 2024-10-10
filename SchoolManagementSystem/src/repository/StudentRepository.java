package repository;

import model.Student;
import data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.management.remote.JMXConnectorFactory.connect;

public class StudentRepository {
    //language=SQL
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    //language=SQL
    private static final String GET_COUNT_OF_STUDENTS = "SELECT count(*) FROM students";

    private final Database database = new Database();

    public Set<Student> getAllStudents() throws SQLException {
        ResultSet studentsResult = database.getSQLStatement().executeQuery(GET_ALL_STUDENTS_QUERY);
        Set<Student> students = new HashSet<>();
        while (studentsResult.next()) {
            Student student = new Student(
                    studentsResult.getLong("student_id"),
                    studentsResult.getString("first_name"),
                    studentsResult.getString("last_name"),
                    studentsResult.getDate("dob"),
                    studentsResult.getString("national_code"),
                    studentsResult.getDouble("gpu")
            );
            students.add(student);
        }
        return students;
    }

    public int getCountOfStudents() throws SQLException {
        ResultSet countResult = database.getSQLStatement().executeQuery(GET_COUNT_OF_STUDENTS);
        int studentCount = 0;
        while (countResult.next()) {
            studentCount = countResult.getInt("count");
        }
        return studentCount;
    }

    public void createStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, dob, national_code, gpu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setDate(3, new java.sql.Date(student.getDob().getTime()));
            pstmt.setString(4, student.getNationalCode());
            pstmt.setFloat(5, student.getGpu());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating student", e);
        }
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, dob = ?, gpu = ? WHERE student_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getFirstName());
            pstmt.setString(2, student.getLastName());
            pstmt.setDate(3, new Date(student.getDob().getTime()));
            pstmt.setFloat(4, student.getGpu());
            pstmt.setInt(5, student.getStudentId());
            pstmt.setString(6, student.getNationalCode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating student", e);
        }
    }
    public void dedeleteStudent(int studentId, String nationalCode) {
        String sql = "DELETE FROM students WHERE student_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, nationalCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = database.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDob(rs.getDate("dob"));
                student.setNationalCode(rs.getString("national_code"));
                student.setGpu(rs.getFloat("gpu"));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all students", e);
        }
        return students;
    }
    public Student findById(int studentId, String nationalCode) {
        String sql = "SELECT * FROM students WHERE student_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, nationalCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setDob(rs.getDate("dob"));
                student.setNationalCode(rs.getString("national_code"));
                student.setGpu(rs.getFloat("gpu"));
                return student;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading student", e);
        }
        return null;
    }
}

