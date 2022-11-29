package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.repository.PaymentRepo;

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
}
