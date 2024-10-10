package serivce;

import model.Teacher;
import repository.TeacherRepository;

import java.util.List;

public class TeacherService {
    private TeacherRepository teacherRepository;

    public TeacherService() {
        teacherRepository = new TeacherRepository();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.createTeacher(teacher);
    }

    public Teacher getTeacher(int teacherId, String nationalCode) {
        return teacherRepository.findById(teacherId, nationalCode);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
    }

    public void deleteTeacher(int teacherId, String nationalCode) {
        teacherRepository.deleteTeacher(teacherId, nationalCode);
    }

    public List<Teacher> listAllTeachers() {
        return teacherRepository.getAllTeachers();
    }
}
