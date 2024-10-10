package repository;

import model.Course;
import data.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CourseRepository {
    private final Database database = new Database();

    public void createCourse(Course course) {
        String sql = "INSERT INTO courses (course_title, course_unit) VALUES (?, ?)";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseTitle());
            pstmt.setInt(2, course.getCourseUnit());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating course", e);
        }
    }

//    public Course readCourse(int courseId) {
//        String sql = "SELECT * FROM courses WHERE course_id = ?";
//        try (Connection conn = database.getDatabaseConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, courseId);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                Course course = new Course();
//                course.setCourseId(rs.getInt("course_id"));
//                course.setCourseTitle(rs.getString("course_title"));
//                course.setCourseUnit(rs.getInt("course_unit"));
//                return course;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error reading course", e);
//        }
//        return null;
//    }

    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET course_title = ?, course_unit = ? WHERE course_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, course.getCourseTitle());
            pstmt.setInt(2, course.getCourseUnit());
            pstmt.setInt(3, course.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating course", e);
        }
    }

    public void deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course", e);
        }
    }

//    public List<Course> getAllCourses() {
//        List<Course> courses = new ArrayList<>();
//        String sql = "SELECT * FROM courses";
//        try (Connection conn = database.getDatabaseConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Course course = new Course();
//                course.setCourseId(rs.getInt("course_id"));
//                course.setCourseTitle(rs.getString("course_title"));
//                course.setCourseUnit(rs.getInt("course_unit"));
//                courses.add(course);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error getting all courses", e);
//        }
//        return courses;
//
//    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection conn = database.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setCourseUnit(rs.getInt("course_unit"));
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all courses", e);
        }
        return courses;
    }

    public Course findById(int courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try (Connection conn = database.getDatabaseConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setCourseUnit(rs.getInt("course_unit"));
                return course;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading course", e);
        }
        return null;
    }


}

