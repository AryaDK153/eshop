package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Map<Payment, Order>> paymentList;
    @BeforeEach
    void setUp(){
        paymentRepository = new PaymentRepository();

        Map<String,String> paymentData1= new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Map<String,String> paymentData2= new HashMap<>();
        paymentData2.put("address", "Jl Jayapura XV");
        paymentData2.put("deliveryFee", "10000");
        Payment payment2 = new Payment("b3a5d496-7e2f-476e-bcd9-f95a1d305c4a", PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentData2);

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        Map<Payment, Order> pair1 = new HashMap<>();
        pair1.put(payment1, order);
        Map<Payment, Order> pair2 = new HashMap<>();
        pair2.put(payment2, order);

        paymentList = new ArrayList<>();
        paymentList.add(pair1);
        paymentList.add(pair2);
    }
    @Test
    void testSaveCreate(){
        Map<Payment, Order> pair = paymentList.get(1);
        Map<Payment, Order> result = paymentRepository.save(pair);

        Payment payment = result.keySet().stream().toList().get(0);
        Map<Payment, Order> findResult = paymentRepository.findById(payment.getId());
        Payment resultPayment = findResult.keySet().stream().toList().get(0);
        assertEquals(payment.getId(), resultPayment.getId());
        assertEquals(payment.getMethod(), resultPayment.getMethod());
        assertEquals(payment.getPaymentData(), resultPayment.getPaymentData());
        assertEquals(payment.getStatus(), resultPayment.getStatus());
        Order resultOrder = findResult.get(resultPayment);
        Order orderComparer = pair.get(payment);
        assertEquals(orderComparer.getId(), resultOrder.getId());
        assertEquals(orderComparer.getProducts(), resultOrder.getProducts());
        assertEquals(orderComparer.getOrderTime(), resultOrder.getOrderTime());
        assertEquals(orderComparer.getAuthor(), resultOrder.getAuthor());
        assertEquals(orderComparer.getStatus(), resultOrder.getStatus());
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
        for (Map<Payment, Order> pair : paymentList) {
            paymentRepository.save(pair);
        }

        Payment payment = paymentList.get(1).keySet().stream().toList().get(0);
        Map<Payment, Order> findResult = paymentRepository.findById(payment.getId());
        Payment resultPayment = findResult.keySet().stream().toList().get(0);
        assertEquals(payment.getId(), resultPayment.getId());
        assertEquals(payment.getMethod(), resultPayment.getMethod());
        assertEquals(payment.getPaymentData(), resultPayment.getPaymentData());
        assertEquals(payment.getStatus(), resultPayment.getStatus());
        Order resultOrder = findResult.get(resultPayment);
        Order orderComparer = paymentList.get(1).get(payment);
        assertEquals(orderComparer.getId(), resultOrder.getId());
        assertEquals(orderComparer.getProducts(), resultOrder.getProducts());
        assertEquals(orderComparer.getOrderTime(), resultOrder.getOrderTime());
        assertEquals(orderComparer.getAuthor(), resultOrder.getAuthor());
        assertEquals(orderComparer.getStatus(), resultOrder.getStatus());
    }
    @Test
    void testFindByIdIfIdNotFound () {
        for (Map<Payment, Order> pair : paymentList) {
            paymentRepository.save(pair);
        }

        Map<Payment, Order> findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }
    @Test
    void testFindAll() {
        for (Map<Payment, Order> pair : paymentList) {
            paymentRepository.save(pair);
        }

        Iterator<Map<Payment, Order>> findResult = paymentRepository.findAll();
        assertTrue(findResult.hasNext());
        Payment savedPayment = findResult.next().keySet().stream().toList().get(0);
        assertEquals(paymentList.get(0).keySet().stream().toList().get(0).getId(), savedPayment.getId());
        savedPayment = findResult.next().keySet().stream().toList().get(0);
        assertEquals(paymentList.get(1).keySet().stream().toList().get(0).getId(), savedPayment.getId());
    }
}
