package ir.maktab.service;

import ir.maktab.data.entity.Payment;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceTest {
    private static final PaymentService paymentService = PaymentService.getInstance();
    @Order(1)
    @Test
    public void
}
