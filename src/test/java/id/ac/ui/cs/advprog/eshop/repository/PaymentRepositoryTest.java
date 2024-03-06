package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> paymentList;
    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        Map<String,String> paymentData1= new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Map<String,String> paymentData2= new HashMap<>();
        paymentData2.put("address", "Jl Jayapura XV");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData2);

        paymentList = new ArrayList<>();
        paymentList.add(payment1);
        paymentList.add(payment2);
    }
    @Test
    void testSaveCreate(){
        Payment payment = paymentList.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }
//    @Test
//    void testSaveUpdate(){
//        Payment payment = paymentList.get(1);
//        Payment result = paymentRepository.save(payment);
//
//        Payment findResult = paymentRepository.findById(payment.getId());
//        assertEquals(payment.getId(), findResult.getId());
//        assertEquals(payment.getMethod(), findResult.getMethod());
//        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
//        assertEquals(payment.getStatus(), findResult.getStatus());
//    }
    @Test
    void testFindByIdIfIdFound () {
        for (Payment payment : paymentList) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(paymentList.get(1).getId());
        assertEquals(paymentList.get(1).getId(), findResult.getId());
        assertEquals(paymentList.get(1).getMethod(), findResult.getMethod());
        assertEquals(paymentList.get(1).getPaymentData(), findResult.getPaymentData());
        assertEquals(paymentList.get(1).getStatus(), findResult.getStatus());
    }
    @Test
    void testFindByIdIfIdNotFound () {
        for (Payment payment : paymentList) {
            paymentRepository.save(payment);
        }

        Order findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }
    @Test
    void testFindAll() {
        for (Payment payment : paymentList) {
            paymentRepository.save(payment);
        }

        Iterator<Payment> findResult = paymentRepository.findAll();
        assertTrue(findResult.hasNext());
        Payment savedPayment = findResult.next();
        assertEquals(paymentList.get(0).getId(), savedPayment.getId());
        savedPayment = findResult.next();
        assertEquals(paymentList.get(1).getId(), savedPayment.getId());
    }
}
