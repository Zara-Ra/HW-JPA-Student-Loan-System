package ir.maktab.service;

import ir.maktab.data.entity.loans.EducationLoan;
import ir.maktab.data.entity.loans.HousingLoan;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.loans.TuitionLoan;
import ir.maktab.data.enums.CityType;
import ir.maktab.data.enums.DegreeGroup;
import ir.maktab.data.enums.RepayType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanServiceTest {

    private static final Loan[] loans = new Loan[9];
    private static final LoanService loanService = LoanService.getInstance();

    public static Loan[] loanData() {
        loans[0] = new EducationLoan(RepayType.EACH_SEMESTER, 1900000d, DegreeGroup.GROUP1);
        loans[1] = new EducationLoan(RepayType.EACH_SEMESTER, 2250000d, DegreeGroup.GROUP2);
        loans[2] = new EducationLoan(RepayType.EACH_SEMESTER, 2600000d, DegreeGroup.GROUP3);

        loans[3] = new HousingLoan(RepayType.EACH_GRADE, 32000000d, CityType.CAPITAL);
        loans[4] = new HousingLoan(RepayType.EACH_GRADE, 26000000d, CityType.METROPOLIS);
        loans[5] = new HousingLoan(RepayType.EACH_GRADE, 19500000d, CityType.OTHER);

        loans[6] = new TuitionLoan(RepayType.EACH_SEMESTER, 1300000d, DegreeGroup.GROUP1);
        loans[7] = new TuitionLoan(RepayType.EACH_SEMESTER, 2600000d, DegreeGroup.GROUP2);
        loans[8] = new TuitionLoan(RepayType.EACH_SEMESTER, 6500000d, DegreeGroup.GROUP3);
        return loans;
    }

    @BeforeAll
    static void setUp() {
    }

    @ParameterizedTest
    @MethodSource(value = "loanData")
    @Order(1)
    void addLoan(Loan loan) {
        loanService.addLoan(loan);
    }

    @Order(2)
    @Test
    void getEducationLoanTest(){
        assertEquals(loans[0],loanService.getEducationLoan(DegreeGroup.GROUP1));
        assertEquals(loans[1],loanService.getEducationLoan(DegreeGroup.GROUP2));
        assertEquals(loans[2],loanService.getEducationLoan(DegreeGroup.GROUP3));
    }
    @Order(3)
    @Test
    void getTuitionLoanTest(){
        assertEquals(loans[6],loanService.getTuitionLoan(DegreeGroup.GROUP1));
        assertEquals(loans[7],loanService.getTuitionLoan(DegreeGroup.GROUP2));
        assertEquals(loans[8],loanService.getTuitionLoan(DegreeGroup.GROUP3));
    }
    @Order(4)
    @Test
    void getHousingLoanTest(){
        assertEquals(loans[3],loanService.getHousingLoan(CityType.CAPITAL));
        assertEquals(loans[4],loanService.getHousingLoan(CityType.METROPOLIS));
        assertEquals(loans[5],loanService.getHousingLoan(CityType.OTHER));
    }
}