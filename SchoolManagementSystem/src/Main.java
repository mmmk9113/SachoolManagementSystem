import model.Student;
import serivce.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.printCountOfStudents();
        studentService.printAllStudentList();
    }
}