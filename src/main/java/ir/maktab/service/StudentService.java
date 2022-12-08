package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.RepayType;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.repository.StudentRepo;
import ir.maktab.util.date.DateUtil;

import java.util.Date;
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

    public Optional<Student> signIn(String username, String password) {
        return studentRepo.getByUserNameAndPassword(username, password);
    }
    public boolean checkTuitionLoanConditions(Student student) {
        return !(student.getUniversityInfo().getUniversityType() == UniversityType.PUBLIC_DAILY);
    }
    public boolean hasPreviousLoanPayment(Student student, Payment payment) {
        int i = student.getPaymentList().indexOf(payment);
        boolean flag = false;
        if (i != -1) {
            Payment previousPayment = student.getPaymentList().get(i);
            Date previousPaidDate = previousPayment.getPaidDate();
            Date now = DateUtil.getToday();
            RepayType repayType = payment.getLoan().getRepayType();
            switch (repayType) {
                case EACH_SEMESTER -> flag = DateUtil.areDatesInSameSemester(previousPaidDate, now);
                case EACH_GRADE ->
                        flag = DateUtil.areDatesInSameGrade(previousPaidDate, now, student.getUniversityInfo().getDegree());
                default -> flag = true;
            }
        }
        return flag;
    }

    public boolean isGraduated(Student student) {
        return DateUtil.compare(calculateGraduationDate(student), DateUtil.getToday()) <= 0;
    }

    public Date calculateGraduationDate(Student student) {
        int graduateYears = student.getUniversityInfo().getDegree().graduateYears;
        Date entryDate = student.getUniversityInfo().getEntryDate();
        return DateUtil.addYearsToDate(entryDate, graduateYears);
    }
}
