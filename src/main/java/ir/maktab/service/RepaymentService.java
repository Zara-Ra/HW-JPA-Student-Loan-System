package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.Repayment;
import ir.maktab.repository.RepaymentRepo;

import java.util.List;

public class RepaymentService {
    private static final RepaymentService repaymentService = new RepaymentService();

    private RepaymentService() {
    }

    public static RepaymentService getInstance() {
        return repaymentService;
    }

    private static final RepaymentRepo repaymentRepo = RepaymentRepo.getInstance();

    public List<Repayment> getAllForPayment(Payment payment) {
        return repaymentRepo.getAll(payment);
    }

    public void update(Repayment repayment) {
        repaymentRepo.update(repayment);
    }
}
