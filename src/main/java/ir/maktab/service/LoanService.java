package ir.maktab.service;

import ir.maktab.data.entity.loans.Loan;
import ir.maktab.repository.LoanRepo;

public class LoanService {
    private static final LoanService loanService = new LoanService();

    private LoanService() {
    }

    public static LoanService getInstance() {
        return loanService;
    }

    private final LoanRepo loanRepo = LoanRepo.getInstance();

    public void addLoan(Loan loan) {
        loanRepo.save(loan);
    }

}
