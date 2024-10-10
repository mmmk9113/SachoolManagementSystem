package serivce;

import model.Course;
import repository.CourseRepository;


import java.util.List;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService() {
        courseRepository = new CourseRepository();
    }

    public void addCourse(Course course) {
        courseRepository.createCourse(course);
    }

    public Course getCourse(int courseId) {
        return courseRepository.findById(courseId);
    }

    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    public void deleteCourse(int courseId) {
        courseRepository.deleteCourse(courseId);
    }

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }
}
