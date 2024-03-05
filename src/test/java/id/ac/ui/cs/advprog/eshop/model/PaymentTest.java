package id.ac.ui.cs.advprog.eshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Product> products;
    private Order order;
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat");

    }
    @Test
    void testCreatePaymentSuccessStatus(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order.getId(), "VOUCHER_CODE", paymentData);
        
        assertEquals(order.getId(), payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testCreatePaymentInvalidMethod(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(order.getId(), "YEE", paymentData);
        });
    }
    @Test
    void testCreatePaymentVoucherInvalidData(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567");
        Payment payment = new Payment(order.getId(), "VOUCHER_CODE", paymentData);
        
        assertEquals(order.getId(), payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testCreatePaymentCODInvalidData(){
        paymentData = new HashMap<>();
        paymentData.put("address", "Jl Jayapura XV");
        paymentData.put("deliveryFee", "");
        Payment payment = new Payment(order.getId(), "CASH_ON_DELIVERY", paymentData);
        
        assertEquals(order.getId(), payment.getId());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testSetPaymentStatusValid(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order.getId(), "VOUCHER_CODE", paymentData);

        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
    @Test
    void testSetPaymentStatusInvalid(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order.getId(), "VOUCHER_CODE", paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("YEE");
        });
        
    }
}
