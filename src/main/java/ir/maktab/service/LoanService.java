package ir.maktab.service;

import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.loans.TuitionLoan;
import ir.maktab.data.enums.CityType;
import ir.maktab.data.enums.DegreeGroup;
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

    public EducationLoan getEducationLoan(DegreeGroup degreeGroup) {
        return loanRepo.getEducationLoanByDegree(degreeGroup);
    }

    public TuitionLoan getTuitionLoan(DegreeGroup degreeGroup) {
        return loanRepo.getTuitionLoanByDegree(degreeGroup);
    }

    public HousingLoan getHousingLoan(CityType cityType) {
        return loanRepo.getHouseLoanByCity(cityType);
    }

}
