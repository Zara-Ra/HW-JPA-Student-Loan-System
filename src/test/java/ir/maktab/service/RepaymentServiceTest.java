package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static ir.maktab.presentation.StudentLoanSystem.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepaymentServiceTest {
    private static Student student;
    private static final RepaymentService repaymentService = RepaymentService.getInstance();

    @BeforeAll
    static void signInStudent(){
        student = studentService.signIn("0441967205","aA1@zzzz").get();
    }
    @Test
    void getAllForPaymentTest(){
        Loan loan = loanService.getEducationLoan(student.getUniversityInfo().getDegree().toDegreeGroup());
        Payment payment = new Payment(student, loan, student.getUniversityInfo().getDegree());
        paymentService.registerPayment(payment);

        assertEquals(60,repaymentService.getAllForPayment(payment).size());
    }
}
