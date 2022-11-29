package ir.maktab.service;

import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.data.enums.RepayType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LoanServiceTest {

    private static final Loan[] loans = new Loan[9];
    private static final LoanService loanService = LoanService.getInstance();

    public static Loan[] loanData() {
        loans[1] = new EducationLoan(RepayType.EACH_SEMESTER,1900000d, DegreeGroup.GROUP1);

        return loans;
    }

    @BeforeAll
    @ParameterizedTest
    @MethodSource(value = "loanData")
    void setUp(Loan loan) {
        loanService.addLoan(loan);
    }

    @Test
    void addLoan() {
    }
}