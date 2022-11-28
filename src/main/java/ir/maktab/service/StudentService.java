package ir.maktab.service;

import ir.maktab.data.entity.user.Student;
import ir.maktab.repository.StudentRepo;

import java.util.Optional;

public class StudentService {
    private static final StudentService studentService = new StudentService();

    private StudentService() {
    }

    public static StudentService getInstance() {
        return studentService;
    }

    private final StudentRepo studentRepo = StudentRepo.getInstance();

    public void singUp(Student student) {
        studentRepo.save(student);
    }

    public void signIn(String username,String password){
        Optional<Student> student = studentRepo.getByUserNameAndPassword(username, password);
        student.ifPresent(System.out::println);
    }


}
