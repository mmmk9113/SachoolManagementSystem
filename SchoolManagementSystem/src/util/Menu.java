package util;

import model.Course;
import model.Student;
import model.Teacher;

import serivce.CourseService;
import serivce.StudentService;
import serivce.TeacherService;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static StudentService studentService = new StudentService();
    public static CourseService courseService = new CourseService();
    public static TeacherService teacherService = new TeacherService();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("School Management System");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Manage Teachers");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageCourses();
                    break;
                case 3:
                    manageTeachers();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageStudents() {
        System.out.println("1. Add Student");
        System.out.println("2. View Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. List All Students");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                viewStudent();
                break;
            case 3:
                updateStudent();
                break;
            case 4:
                deleteStudent();
                break;
            case 5:
                listAllStudents();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addStudent() {
        System.out.println("Enter first name:");
        String firstName = scanner.next();
        System.out.println("Enter last name:");
        String lastName = scanner.next();
        System.out.println("Enter date of birth (yyyy-mm-dd):");
        String dobStr = scanner.next();
        Date dob = java.sql.Date.valueOf(dobStr);
        System.out.println("Enter national code:");
        String nationalCode = scanner.next();
        System.out.println("Enter GPA:");
        float gpu = scanner.nextFloat();

        Student student = new Student();

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDob((java.sql.Date) dob);
        student.setNationalCode(nationalCode);
        student.setGpu(gpu);
        studentService.addStudent(student);
        System.out.println("Student added successfully!");
    }

    private static void viewStudent() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        System.out.println("Enter national code:");
        String code = scanner.next();
        Student student = studentService.getStudent(id, code);
        if (student != null) {
            System.out.println("Student details: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        System.out.println("Enter national code:");
        String code = scanner.next();

        Student student = studentService.getStudent(id, code);
        if (student != null) {
            System.out.println("Enter new first name:");
            String firstName = scanner.next();
            System.out.println("Enter new last name:");
            String lastName = scanner.next();
            System.out.println("Enter new date of birth (yyyy-mm-dd):");
            String dobStr = scanner.next();
            Date dob = java.sql.Date.valueOf(dobStr);
            System.out.println("Enter new GPA:");
            float gpu = scanner.nextFloat();

            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDob((java.sql.Date) dob);
            student.setGpu(gpu);
            studentService.updateStudent(student);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        System.out.println("Enter national code:");
        String code = scanner.next();

        studentService.deleteStudent(id, code);
        System.out.println("Student deleted successfully!");
    }

    private static void listAllStudents() {
        List<Student> students = studentService.listAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void manageCourses() {
        System.out.println("1. Add Course");
        System.out.println("2. View Course");
        System.out.println("3. Update Course");
        System.out.println("4. Delete Course");
        System.out.println("5. List All Courses");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCourse();
                break;
            case 2:
                viewCourse();
                break;
            case 3:
                updateCourse();
                break;
            case 4:
                deleteCourse();
                break;
            case 5:
                listAllCourses();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addCourse() {
        System.out.println("Enter course title:");
        String courseTitle = scanner.next();
        System.out.println("Enter course unit:");
        int courseUnit = scanner.nextInt();

        Course course = new Course();

        course.setCourseTitle(courseTitle);
        course.setCourseUnit(courseUnit);
        courseService.addCourse(course);
        System.out.println("Course added successfully!");
    }

    private static void viewCourse() {
        System.out.println("Enter course ID:");
        int id = scanner.nextInt();
        Course course = courseService.getCourse(id);
        if (course != null) {
            System.out.println("Course details: " + course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void updateCourse() {
        System.out.println("Enter course ID:");
        int id = scanner.nextInt();
        Course course = courseService.getCourse(id);
        if (course != null) {
            System.out.println("Enter new course title:");
            String courseTitle = scanner.next();
            System.out.println("Enter new course unit:");
            int courseUnit = scanner.nextInt();
            course.setCourseTitle(courseTitle);
            course.setCourseUnit(courseUnit);
            courseService.updateCourse(course);
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void deleteCourse() {
        System.out.println("Enter course ID:");
        int id = scanner.nextInt();
        courseService.deleteCourse(id);
        System.out.println("Course deleted successfully!");
    }

    private static void listAllCourses() {
        List<Course> courses = courseService.listAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    private static void manageTeachers() {
        System.out.println("1. Add Teacher");
        System.out.println("2. View Teacher");
        System.out.println("3. Update Teacher");
        System.out.println("4. Delete Teacher");
        System.out.println("5. List All Teachers");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addTeacher();
                break;
            case 2:
                viewTeacher();
                break;
            case 3:
                updateTeacher();
                break;
            case 4:
                deleteTeacher();
                break;
            case 5:
                listAllTeachers();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addTeacher() {
        System.out.println("Enter first name:");
        String firstName = scanner.next();
        System.out.println("Enter last name:");
        String lastName = scanner.next();
        System.out.println("Enter date of birth (yyyy-mm-dd):");
        String dobStr = scanner.next();
        Date dob = java.sql.Date.valueOf(dobStr);
        System.out.println("Enter national code:");
        String nationalCode = scanner.next();
        System.out.println("Enter course ID:");
        int courseId = scanner.nextInt();
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setDob(dob);
        teacher.setNationalCode(nationalCode);
        teacher.setCourseId(courseId);
        teacherService.addTeacher(teacher);
        System.out.println("Teacher added successfully!");
    }

    private static void viewTeacher() {
        System.out.println("Enter Teacher ID:");
        int id = scanner.nextInt();
        System.out.println("Enter National Code:");
        String code = scanner.next();

        Teacher teacher = teacherService.getTeacher(id, code);
        if (teacher != null) {
            System.out.println("Teacher ID: " + teacher.getTeacherId());
            System.out.println("First Name: " + teacher.getFirstName());
            System.out.println("Last Name: " + teacher.getLastName());
            System.out.println("Date of Birth: " + teacher.getDob());
            System.out.println("National Code: " + teacher.getNationalCode());
            System.out.println("Course ID: " + teacher.getCourseId());
        } else {
            System.out.println("Teacher not found!");
        }
    }

    private static void updateTeacher() {
        System.out.println("Enter Teacher ID:");
        int id = scanner.nextInt();
        System.out.println("Enter National Code:");
        String code = scanner.next();

        Teacher teacher = teacherService.getTeacher(id, code);
        if (teacher != null) {
            System.out.println("Enter new First Name:");
            teacher.setFirstName(scanner.next());
            System.out.println("Enter new Last Name:");
            teacher.setLastName(scanner.next());
            System.out.println("Enter new Date of Birth (YYYY-MM-DD):");
            teacher.setDob(java.sql.Date.valueOf(scanner.next()));
            System.out.println("Enter new Course ID:");
            teacher.setCourseId(scanner.nextInt());

            teacherService.updateTeacher(teacher);
            System.out.println("Teacher updated successfully!");
        } else {
            System.out.println("Teacher not found!");
        }
    }

    public static void deleteTeacher() {
        System.out.println("Enter Teacher ID:");
        int id = scanner.nextInt();
        System.out.println("Enter National Code:");
        String code = scanner.next();

        teacherService.deleteTeacher(id, code);
        System.out.println("Teacher deleted successfully!");
    }

    private static void listAllTeachers() {
        List<Teacher> teachers = teacherService.listAllTeachers();
        if (teachers.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }
}



