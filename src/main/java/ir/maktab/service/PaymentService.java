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
    private static final double ANNUAL_INCREASE = 0.2;
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
        double principal = payment.getLoan().getAmount();
        double firstYearRepaymentAmount = firstYearRepaymentAmount(principal, REPAY_YEARS, INTEREST_RATE,ANNUAL_INCREASE);
        int repayNum = 0;
        Date repayDate = studentService.calculateGraduationDate(payment.getStudent());
        for (int i = 0; i < REPAY_YEARS; i++) {
            double eachMonthRepaymentAmount = firstYearRepaymentAmount * Math.pow(ANNUAL_INCREASE+1, i);
            for (int j = 0; j < MONTHS_OF_YEAR; j++) {
                if (i != 0 || j != 0) {
                    repayDate = DateUtil.addMonthToDate(repayDate);
                }
                Repayment repayment = new Repayment(null, payment, ++repayNum, eachMonthRepaymentAmount, repayDate,
                        false);
                payment.getRepaymentList().add(repayment);
            }
        }
        payment.setPaidDate(DateUtil.getToday());
        paymentRepo.save(payment);
        payment.getStudent().getPaymentList().add(payment);
    }

    private double firstYearRepaymentAmount(double principal,int repayYears,double interestRate,double annualIncrease){
        double totalRepay = principal + principal * interestRate;
        double numberOfMonths = MONTHS_OF_YEAR;
        double sum = numberOfMonths;
        for (int i = 1; i <repayYears ; i++) {
            double temp = numberOfMonths * annualIncrease;
            numberOfMonths += temp;
            sum += numberOfMonths;
        }
        return totalRepay / sum;
    }
    public void checkRegistrationDate(Date date) {
        if (!DateUtil.isInRegistrationRange(date))
            throw new NotInDateRangeException("You Can Not Register For A Loan At This Date (Registration Time is First Week " +
                    "Of Aban And Last Week of Bahman)");
    }

    public boolean checkSpouseConditions(Student student, Person spouse) {
        List<Payment> listOfPayments = paymentRepo.findByNationalNumber(spouse);
        if (listOfPayments.size() == 0)
            return true;
        long count = listOfPayments.stream().filter(p -> (p.getLoan() instanceof HousingLoan)).count();
        return count == 0;
        //todo if spouse has previous housing payments doesn't give payment to student,it doesnt check the date
    }
}
