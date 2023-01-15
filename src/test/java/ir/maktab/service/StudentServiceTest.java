package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.City;
import ir.maktab.data.enums.Degree;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.util.date.DateUtil;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static ir.maktab.presentation.StudentLoanSystem.loanService;
import static ir.maktab.presentation.StudentLoanSystem.paymentService;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {
    private static final StudentService studentService = StudentService.getInstance();
    private static Student student;


    @BeforeAll
    public static void setUpMockStudent() {
        AccountInfo accountInfo = new AccountInfo(null, "0080218725", "aA1@zzzz");

        LocalDateTime localDate1 = LocalDateTime.of(2021, 2, 26, 0, 0);
        Date entry = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185193", "Tehran University"
                , UniversityType.PRIVATE, entry, Degree.BACHELOR);

        LocalDateTime localDate = LocalDateTime.of(1988, 2, 26, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        student = new Student(null, "Zahra", "Rahimi", "Tahere", "Reza"
                , "34123", "0080218725", birth, false, false, City.TEHRAN
                , null, accountInfo, universityInfo);

        studentService.singUp(student);
    }

    @Order(1)
    @Test
    public void invalidUsernameSignInTest() {
        Optional<Student> optionalStudent = studentService.signIn("12345678", "aA1@zzzz");
        assertFalse(optionalStudent.isPresent());
    }

    @Order(2)
    @Test
    public void invalidPasswordSignInTest() {
        Optional<Student> optionalStudent = studentService.signIn("0080218725", "aaaaaaaa");
        assertFalse(optionalStudent.isPresent());
    }

    @Order(3)
    @Test
    public void isGraduatedTest() {
        LocalDateTime localDate1 = LocalDateTime.of(2000, 2, 26, 0, 0);
        Date entry = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.CAMPUS, entry, Degree.PHD);

        LocalDateTime localDate = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        Student student = new Student(null, "Graduated", "Hosseini", "Maryam", "Mohsen"
                , "12345", "0081790171", birth, false, false, City.OTHER
                , null, null, universityInfo);

        assertTrue(studentService.isGraduated(student));
    }

    @Order(4)
    @Test
    public void isNotGraduatedTest() {
        LocalDateTime localDate1 = LocalDateTime.of(2022, 2, 26, 0, 0);
        Date entry = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.CAMPUS, entry, Degree.PHD);

        LocalDateTime localDate = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        Student student = new Student(null, "NotGraduated", "Hosseini", "Maryam", "Mohsen"
                , "12345", "0081790171", birth, false, false, City.OTHER
                , null, null, universityInfo);

        assertFalse(studentService.isGraduated(student));
    }

    @Order(5)
    @Test
    public void signInTest() {
        Optional<Student> optionalStudent = studentService.signIn("0080218725", "aA1@zzzz");
        student = optionalStudent.get();
        assertTrue(optionalStudent.isPresent());
    }

    @Order(6)
    @Test
    public void hasPreviousLoanPaymentForTuitionLoanTest() {
        Loan loan = loanService.getTuitionLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        assertFalse(studentService.hasPreviousLoanPayment(student, payment));
        paymentService.registerPayment(payment);
        assertTrue(studentService.hasPreviousLoanPayment(student, payment));
    }

    @Order(7)
    @Test
    public void hasPreviousLoanPaymentForEducationLoanTest() {
        Loan loan = loanService.getEducationLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        assertFalse(studentService.hasPreviousLoanPayment(student, payment));
        paymentService.registerPayment(payment);
        assertTrue(studentService.hasPreviousLoanPayment(student, payment));
    }

    @Order(8)
    @Test
    public void hasPreviousLoanPaymentForHousingLoanTest() {
        Loan loan = loanService.getHousingLoan(student.getCity().type);
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        assertFalse(studentService.hasPreviousLoanPayment(student, payment));
        paymentService.registerPayment(payment);
        assertTrue(studentService.hasPreviousLoanPayment(student, payment));
    }

    @Order(9)
    @Test
    public void InvalidTuitionLoanConditions() {
        LocalDateTime localDate1 = LocalDateTime.of(2022, 2, 26, 0, 0);
        Date entry = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.PUBLIC_DAILY, entry, Degree.PHD);

        LocalDateTime localDate = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        Student student = new Student(null, "StudentOfPublicUni", "Hosseini", "Maryam", "Mohsen"
                , "12345", "0081790171", birth, false, false, City.OTHER
                , null, null, universityInfo);

        assertFalse(studentService.checkTuitionLoanConditions(student));
    }

    @Order(10)
    @Test
    public void validTuitionLoanConditions() {
        LocalDateTime localDate1 = LocalDateTime.of(2022, 2, 26, 0, 0);
        Date entry = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.PAYAME_NOUR, entry, Degree.PHD);

        LocalDateTime localDate = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth = DateUtil.localDateTimeToDate(localDate);

        Student student = new Student(null, "StudentOfPayamNor", "Hosseini", "Maryam", "Mohsen"
                , "12345", "0081790171", birth, false, false, City.OTHER
                , null, null, universityInfo);

        assertTrue(studentService.checkTuitionLoanConditions(student));
    }

    @Order(11)
    @Test
    public void calculateGraduationDateForPHDTest(){
        LocalDateTime entry = LocalDateTime.of(2022, 1, 1, 0, 0);
        Date entryDate = DateUtil.localDateTimeToDate(entry);

        LocalDateTime graduation = LocalDateTime.of(2027,1,1,0,0);
        Date graduationDate = DateUtil.localDateTimeToDate(graduation);

        UniversityInfo universityInfo = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.PAYAME_NOUR, entryDate, Degree.PHD);

        Student student = new Student(null, "CalculateGrad",null, null, "Mohsen"
                , null, null, null, false, false, null
                , null, null, universityInfo);

        assertEquals(graduationDate,studentService.calculateGraduationDate(student));
    }
}
