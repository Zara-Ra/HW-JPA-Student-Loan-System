package ir.maktab.service;

import ir.maktab.data.entity.user.Person;
import ir.maktab.data.entity.user.Student;
import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {
    private static final PaymentService paymentService = PaymentService.getInstance();
    private static final StudentService studentService = StudentService.getInstance();

    @Order(1)
    @Test
    void checkRegistrationDateTest() {
        NotInDateRangeException exception = assertThrows(NotInDateRangeException.class
                , () -> paymentService.checkRegistrationDate(DateUtil.TODAY_DATE));
        assertEquals("You Can Not Register For A Loan At This Date (Registration Time is First Week Of Aban And Last Week of Bahman)"
                , exception.getMessage());
    }
    @Order(2)
    @Test
    void checkRegistrationDateValidTest(){
        assertDoesNotThrow( () -> paymentService.checkRegistrationDate(DateUtil.getToday()));
    }

    @Order(3)
    @Test
    void invalidSpouseConditionsTest() {
        Student student = studentService.signIn("0081790171","aA1@zzzz").get();
        Person spouse = new Person("Zahra","Rahimi","0080218725");
        boolean hasCondition = paymentService.checkSpouseConditions(student, spouse);
        assertFalse(hasCondition);
    }
    @Order(4)
    @Test
    void validSpouseConditionsWithoutAnyPaymentTest() {
        Student student = studentService.signIn("0081790171","aA1@zzzz").get();
        Person spouse = new Person("Ala","Hosseini","0442521677");
        boolean hasCondition = paymentService.checkSpouseConditions(student, spouse);
        assertTrue(hasCondition);
    }
    @Order(5)
    @Test
    void validSpouseConditionsTestWithPaymentTest(){

    }
}
