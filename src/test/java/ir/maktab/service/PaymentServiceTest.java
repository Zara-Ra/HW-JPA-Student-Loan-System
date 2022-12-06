package ir.maktab.service;

import ir.maktab.util.date.DateUtil;
import ir.maktab.util.exceptions.NotInDateRangeException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {
    private static final PaymentService paymentService = PaymentService.getInstance();

    @Test
    void checkRegistrationDateTest() {
        NotInDateRangeException exception = assertThrows(NotInDateRangeException.class
                , () -> paymentService.checkRegistrationDate(DateUtil.TODAY_DATE));
        assertEquals("You Can Not Register For A Loan At This Date (Registration Time is First Week Of Aban And Last Week of Bahman)"
                , exception.getMessage());
    }

    @Test
    void checkSpouseConditionsTest() {

    }
}
