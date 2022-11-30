package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.repository.PaymentRepo;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;

import java.util.Date;

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
}
