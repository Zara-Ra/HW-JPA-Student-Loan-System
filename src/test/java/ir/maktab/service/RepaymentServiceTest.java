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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static ir.maktab.presentation.StudentLoanSystem.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepaymentServiceTest {
    private static Student student;
    private static final RepaymentService repaymentService = RepaymentService.getInstance();

    @BeforeAll
    static void setUpMockStudent(){
        AccountInfo accountInfo1 = new AccountInfo(null, "0441967205", "aA1@zzzz");

        LocalDateTime localDate1 = LocalDateTime.of(2021, 2, 26, 0, 0);
        Date entry1 = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo1 = new UniversityInfo(null, "810185202", "Sharif University"
                , UniversityType.CAMPUS, entry1, Degree.DISCONTINUOUS_MASTER);

        LocalDateTime localDate2 = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth1 = DateUtil.localDateTimeToDate(localDate2);

        student = new Student(null, "Ala", "Hosseini", "Zahra", "Esmaeil"
                , "12345", "0441967205", birth1, false, false, City.RASHT
                , null, accountInfo1, universityInfo1);

        studentService.singUp(student);

    }
    @Test
    void getAllForPaymentTest(){
        Loan loan = loanService.getEducationLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        paymentService.registerPayment(payment);

        assertEquals(60,repaymentService.getAllForPayment(payment).size());
    }
}
