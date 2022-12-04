package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.repository.PaymentRepo;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;

import java.util.Date;
import java.util.List;

import static ir.maktab.presentation.StudentLoanSystem.loanService;

public class PaymentService {
    private static final PaymentService paymentService = new PaymentService();

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        return paymentService;
    }

    private final PaymentRepo paymentRepo = PaymentRepo.getInstance();
    public void registerPayment(Payment payment){
        paymentRepo.save(payment);
    }
    public void checkRegistrationDate(Date date) {
        if (!DateUtil.isInRegistrationRange(date))
            throw new NotInDateRangeException("You Can Not Register For A Loan At This Date (Registration Time is First Week " +
                    "Of Aban And Last Week of Bahman)");
    }
    public boolean checkSpouseConditions(Student student, Person person){
        List<Payment> listOfPayments = paymentRepo.findByNationalNumber(person);
        if(listOfPayments.size() == 0)
            return true;
        return listOfPayments.stream().anyMatch(p -> {
            return !(p.getLoan() instanceof HousingLoan);
        });
       //todo if spouse has previous housing payments doesn't give payment to student,it doesnt check the date
    }

    public static void main(String[] args) {
        Person spouse = new Person("Zahra","Rahimi","0080218725");
        boolean b = paymentService.checkSpouseConditions(null, spouse);
        System.out.println("hi");
    }
}
