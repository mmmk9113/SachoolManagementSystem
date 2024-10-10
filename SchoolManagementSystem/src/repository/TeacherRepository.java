package repository;

import model.Teacher;
import data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    private final Database database = new Database();

    public void createTeacher(Teacher teacher) {
        String sql = "INSERT INTO teachers (first_name, last_name, dob, national_code, course_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getLastName());
            pstmt.setDate(3, new java.sql.Date(teacher.getDob().getTime()));
            pstmt.setString(4, teacher.getNationalCode());
            pstmt.setInt(5, teacher.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating teacher", e);
        }
    }

//    public Teacher readTeacher(int teacherId, String nationalCode) {
//        String sql = "SELECT * FROM teachers WHERE teachers_id = ? AND national_code = ?";
//        try (Connection conn = database.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, teacherId);
//            pstmt.setString(2, nationalCode);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                Teacher teacher = new Teacher();
//                teacher.setTeacherId(rs.getInt("teachers_id"));
//                teacher.setFirstName(rs.getString("first_name"));
//                teacher.setLastName(rs.getString("last_name"));
//                teacher.setDob(rs.getDate("dob"));
//                teacher.setNationalCode(rs.getString("national_code"));
//                teacher.setCourseId(rs.getInt("course_id"));
//                return teacher;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error reading teacher", e);
//        }
//        return null;
//    }

    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE teachers SET first_name = ?, last_name = ?, dob = ?, course_id = ? WHERE teachers_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getFirstName());
            pstmt.setString(2, teacher.getLastName());
            pstmt.setDate(3, new java.sql.Date(teacher.getDob().getTime()));
            pstmt.setInt(4, teacher.getCourseId());
            pstmt.setInt(5, teacher.getTeacherId());
            pstmt.setString(6, teacher.getNationalCode());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating teacher", e);
        }
    }

    public void deleteTeacher(int teacherId, String nationalCode) {
        String sql = "DELETE FROM teachers WHERE teachers_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            pstmt.setString(2, nationalCode);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting teacher", e);
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Connection conn = database.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teachers_id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setDob(rs.getDate("dob"));
                teacher.setNationalCode(rs.getString("national_code"));
                teacher.setCourseId(rs.getInt("course_id"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all teachers", e);
        }
        return teachers;
    }
    public Teacher findById(int teacherId, String nationalCode) {
        String sql = "SELECT * FROM teachers WHERE teacher_id = ? AND national_code = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            pstmt.setString(2, nationalCode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setDob(rs.getDate("dob"));
                teacher.setNationalCode(rs.getString("national_code"));
                teacher.setCourseId(rs.getInt("course_id"));
                return teacher;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading teacher", e);
        }
        return null;
    }
}
