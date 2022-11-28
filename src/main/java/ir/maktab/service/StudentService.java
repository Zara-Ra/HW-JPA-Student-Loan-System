package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.loans.TuitionLoan;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.enums.RepayType;
import ir.maktab.repository.PaymentRepo;
import ir.maktab.repository.StudentRepo;
import ir.maktab.util.Dates;

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
    private final PaymentRepo paymentRepo = PaymentRepo.getInstance();

    public void singUp(Student student) {
        studentRepo.save(student);
    }

    public Optional<Student> signIn(String username, String password) {
        return studentRepo.getByUserNameAndPassword(username, password);
    }

    public boolean registerForEducationLoan(Student student) {
        //checkDate();
        Loan loan = new EducationLoan(RepayType.EACH_SEMESTER, student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        if(canRegisterForLoan(student,payment)){
            //get card info
            return true;
        }
        return false;
    }
    /*public boolean registerForTuitionLoan(Student student) {
        //Loan loan = new TuitionLoan(RepayType.EACH_SEMESTER, student.getUniversityInfo().getDegree().toDegreeGroup());
        //Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        if(canRegisterForLoan(student,payment)){
            //get card info
            return true;
        }
        return false;
    }*/
    /*public boolean registerForHousingLoan(Student student) {
        //Loan loan = new HousingLoan(RepayType.EACH_GRADE, student.getUniversityInfo().getDegree().toDegreeGroup());
        //Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        if(canRegisterForLoan(student,payment)){
            //check dorm
            //check ismarried

            //get spouse
            //get address
            //get contractNo.
            //get card info
            return true;
        }
        return false;
    }*/
    public boolean canRegisterForLoan(Student student,Payment payment){
        int i = student.getPaymentList().indexOf(payment);
        if (i != -1){
            Payment previousPayment = student.getPaymentList().get(i);
            Date previousPaidDate = previousPayment.getPaidDate();                          //todo if not tested
            Date now = new Date();
            RepayType repayType = payment.getLoan().getRepayType();
            boolean flag;
            switch (repayType){
                case EACH_SEMESTER -> flag = Dates.areDatesInSameSemester(previousPaidDate, now);
                case EACH_GRADE -> flag = Dates.areDatesInSameGrade(previousPaidDate,now,student.getUniversityInfo().getDegree());
                default -> flag = true;
            }
            return !flag;
        }
        return true;
    }

}
