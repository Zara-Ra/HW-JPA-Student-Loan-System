package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import ir.maktab.data.entity.loans.Loan;
import ir.maktab.data.entity.user.AccountInfo;
import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.data.entity.user.UniversityInfo;
import ir.maktab.data.enums.City;
import ir.maktab.data.enums.Degree;
import ir.maktab.data.enums.UniversityType;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Date;

import static ir.maktab.presentation.StudentLoanSystem.loanService;
import static ir.maktab.presentation.StudentLoanSystem.studentService;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {
    private static final PaymentService paymentService = PaymentService.getInstance();

    @BeforeAll
    public static void setUpMockStudents() {
        AccountInfo accountInfo1 = new AccountInfo(null, "0081790171", "aA1@zzzz");

        LocalDateTime localDate1 = LocalDateTime.of(2021, 2, 26, 0, 0);
        Date entry1 = DateUtil.localDateTimeToDate(localDate1);

        UniversityInfo universityInfo1 = new UniversityInfo(null, "810185195", "Sharif University"
                , UniversityType.CAMPUS, entry1, Degree.DISCONTINUOUS_MASTER);

        LocalDateTime localDate2 = LocalDateTime.of(1987, 9, 29, 0, 0);
        Date birth1 = DateUtil.localDateTimeToDate(localDate2);

        Student student1 = new Student(null, "Mohammad", "Hosseini", "Maryam", "Mohsen"
                , "12345", "0081790171", birth1, false, false, City.RASHT
                , null, accountInfo1, universityInfo1);

        studentService.singUp(student1);

        AccountInfo accountInfo2 = new AccountInfo(null, "0442521677", "aA1@zzzz");

        LocalDateTime localDate3 = LocalDateTime.of(2021, 2, 26, 0, 0);
        Date entry2 = DateUtil.localDateTimeToDate(localDate3);

        UniversityInfo universityInfo2 = new UniversityInfo(null, "810185199", "Sharif University"
                , UniversityType.CAMPUS, entry2, Degree.DISCONTINUOUS_MASTER);

        LocalDateTime localDate4 = LocalDateTime.of(2000, 9, 29, 0, 0);
        Date birth2 = DateUtil.localDateTimeToDate(localDate4);

        Student student2 = new Student(null, "Amin", "Hosseini", "Zahra", "Esmaeil"
                , "12346", "0442521677", birth2, false, false, City.OTHER
                , null, accountInfo2, universityInfo2);

        studentService.singUp(student2);

        AccountInfo accountInfo3 = new AccountInfo(null, "0442521685", "aA1@zzzz");

        LocalDateTime localDate5 = LocalDateTime.of(2021, 2, 26, 0, 0);
        Date entry3 = DateUtil.localDateTimeToDate(localDate5);

        UniversityInfo universityInfo3 = new UniversityInfo(null, "810185205", "Sharif University"
                , UniversityType.CAMPUS, entry3, Degree.DISCONTINUOUS_MASTER);

        LocalDateTime localDate6 = LocalDateTime.of(2000, 9, 29, 0, 0);
        Date birth3 = DateUtil.localDateTimeToDate(localDate6);

        Student student3 = new Student(null, "Ali", "Hosseini", "Zahra", "Esmaeil"
                , "12346", "0442521685", birth3, false, false, City.OTHER
                , null, accountInfo3, universityInfo3);

        studentService.singUp(student3);
    }
    @Order(1)
    @Test
    void invalidcheckRegistrationDateTest() {
        NotInDateRangeException exception = assertThrows(NotInDateRangeException.class
                , () -> paymentService.checkRegistrationDate(DateUtil.TODAY_DATE));
        assertEquals("You Can Not Register For A Loan At This Date (Registration Time is First Week Of Aban And Last Week of Bahman)"
                , exception.getMessage());
    }

    @Order(2)
    @Test
    void validRegistrationDateTest() {
        assertDoesNotThrow(() -> paymentService.checkRegistrationDate(DateUtil.getToday()));
    }

    @Order(3)
    @Test
    void invalidSpouseConditionsTest() {
        Student spouse = studentService.signIn("0442521677", "aA1@zzzz").get();

        Loan loan = loanService.getHousingLoan(spouse.getCity().type);
        Payment payment = new Payment(spouse, loan, spouse.getUniversityInfo().getDegree());
        paymentService.registerPayment(payment);

        Student student = studentService.signIn("0081790171", "aA1@zzzz").get();
        boolean hasCondition = paymentService.checkSpouseConditions(student, spouse);
        assertFalse(hasCondition);
    }

    @Order(4)
    @Test
    void validSpouseConditionsTest() {
        Student student = studentService.signIn("0081790171", "aA1@zzzz").get();
        Person spouse = new Person("Ali", "Hosseini", "0442521685");
        boolean hasCondition = paymentService.checkSpouseConditions(student, spouse);
        assertTrue(hasCondition);
    }
}
