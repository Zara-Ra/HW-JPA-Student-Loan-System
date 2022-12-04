package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.Repayment;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.repository.PaymentRepo;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;

import java.util.Date;
import java.util.List;

public class PaymentService {
    private static final int REPAY_YEARS = 5;
    private static final double INTEREST_RATE = 0.04;
    private static final int MONTHS_OF_YEAR = 12;
    private static final PaymentService paymentService = new PaymentService();

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        return paymentService;
    }

    private final PaymentRepo paymentRepo = PaymentRepo.getInstance();
    private final StudentService studentService = StudentService.getInstance();

    public void registerPayment(Payment payment) {
        double totalAmount = payment.getLoan().getAmount();
        double firstYearRepaymentAmount = firstYearRepaymentAmount(totalAmount, REPAY_YEARS, INTEREST_RATE);
        int repayNum = 0;
        Date repayDate = studentService.calculateGraduationDate(payment.getStudent());
        for (int i = 0; i < REPAY_YEARS; i++) {
            double eachMonthRepaymentAmount = firstYearRepaymentAmount * Math.pow(2, i);
            for (int j = 0; j < MONTHS_OF_YEAR; j++) {
                if (i != 0 || j != 0) {
                    repayDate = DateUtil.addMonthToDate(repayDate);
                }
                Repayment repayment = new Repayment(null, payment, ++repayNum, eachMonthRepaymentAmount, repayDate,
                        false);
                payment.getRepaymentList().add(repayment);
            }
        }
        paymentRepo.save(payment);
    }

    private double firstYearRepaymentAmount(double totalLoanAmount, int repayYears, double interestRate) {
        double totalRepayAmount = totalLoanAmount + totalLoanAmount * interestRate;
        int numberOfMonths = 0;
        for (int i = 1; i <= repayYears; i++) {
            numberOfMonths += i * MONTHS_OF_YEAR;
        }
        return totalRepayAmount / numberOfMonths;
    }

    public void checkRegistrationDate(Date date) {
        if (!DateUtil.isInRegistrationRange(date))
            throw new NotInDateRangeException("You Can Not Register For A Loan At This Date (Registration Time is First Week " +
                    "Of Aban And Last Week of Bahman)");
    }

    public boolean checkSpouseConditions(Student student, Person person) {
        List<Payment> listOfPayments = paymentRepo.findByNationalNumber(person);
        if (listOfPayments.size() == 0)
            return true;
        return listOfPayments.stream().anyMatch(p -> {
            return !(p.getLoan() instanceof HousingLoan);
        });
        //todo if spouse has previous housing payments doesn't give payment to student,it doesnt check the date
    }
}
