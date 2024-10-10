package repository;

import data.Database;
import model.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.management.remote.JMXConnectorFactory.connect;

public class ExamRepository {

    private final Database database = new Database();

    public void create(Exam exam) {
        String sql = "INSERT INTO exams (exam_date, exam_grade) VALUES (?, ?)";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(exam.getExam_date().getTime()));
            pstmt.setFloat(2, exam.getExam_grade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating exam", e);
        }
    }

    public void update(Exam exam) {
        String sql = "UPDATE exams SET exam_date = ?, exam_grade = ? WHERE exam_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(exam.getExam_date().getTime()));
            pstmt.setFloat(2, exam.getExam_grade());
            pstmt.setInt(3, exam.getExam_id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating exam", e);
        }
    }

    public void delete(int examId) {
        String sql = "DELETE FROM exams WHERE exam_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting exam", e);
        }
    }


    public Exam findById(int examId) {
        String sql = "SELECT * FROM exams WHERE exam_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, examId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Exam exam = new Exam();
                exam.setExam_id(rs.getInt("exam_id"));
                exam.setExam_date(rs.getDate("exam_date"));
                exam.setExam_grade(rs.getFloat("exam_grade"));
                return exam;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading exam", e);
        }
        return null;
    }

    public List<Exam> findAll() {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM exams";
        try (Connection conn = database.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Exam exam = new Exam();
                exam.setExam_id(rs.getInt("exam_id"));
                exam.setExam_date(rs.getDate("exam_date"));
                exam.setExam_grade(rs.getFloat("exam_grade"));
                exams.add(exam);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all exams", e);
        }
        return exams;
    }
}
