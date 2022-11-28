package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.RepayType;
import ir.maktab.repository.PaymentRepo;
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
    private final PaymentRepo paymentRepo = PaymentRepo.getInstance();

    public void singUp(Student student) {
        studentRepo.save(student);
    }

    public Optional<Student> signIn(String username, String password) {
        Optional<Student> student = studentRepo.getByUserNameAndPassword(username, password);
        return student;
    }

    public void registerForEducationLoan(Student student) {
        Loan loan = new EducationLoan(RepayType.EACH_SEMESTER, student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        int i = student.getPaymentList().indexOf(payment);
        if (i != -1){
            Payment previousPayment = student.getPaymentList().get(i);
            if(previousPayment.getPaidDate().)
        } else if(i == -1){
            paymentRepo.save();
        }
    }

}
