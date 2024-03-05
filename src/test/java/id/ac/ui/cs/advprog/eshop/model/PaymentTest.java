package id.ac.ui.cs.advprog.eshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp(){
        paymentData = new HashMap<>();
    }
    @Test
    void testCreatePaymentSuccessStatus(){
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        
        assertEquals("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentInvalidMethod(){
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", "YEE", paymentData);
        });
    }
    @Test
    void testCreatePaymentIfMethodDataSyntaxNotMatch(){
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        });
    }
    @Test
    void testCreatePaymentVoucherInvalidData(){
        paymentData.put("voucherCode", "ESHOP1234ABC567");
        Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
        
        assertEquals("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentCODInvalidData(){
        paymentData.put("address", "Jl Jayapura XV");
        paymentData.put("deliveryFee", "");
        Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData);
        
        assertEquals("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }

    // setStatus feels not needed for now, going fully automated based on the payment method and data correctness
//    @Test
//    void testSetPaymentStatusValid(){
//        paymentData.put("voucherCode", "ESHOP1234ABC5678");
//        Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
//
//        payment.setStatus("SUCCESS");
//        assertEquals("SUCCESS", payment.getStatus());
//    }
//    @Test
//    void testSetPaymentStatusInvalid(){
//        paymentData.put("voucherCode", "ESHOP1234ABC5678");
//        Payment payment = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData);
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            payment.setStatus("YEE");
//        });
//    }
}
