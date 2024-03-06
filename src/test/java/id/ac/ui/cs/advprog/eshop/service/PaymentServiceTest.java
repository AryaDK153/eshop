package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Map<Payment, Order>> payments;
    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("0fd4b6f3-33fd-4948-84a1-06c7d2b58ef8", PaymentMethod.VOUCHER_CODE.getValue(), paymentData1);
        Map<String, String> paymentData2 = new HashMap<>();
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

        payments = new ArrayList<>();
        payments.add(pair1);
        payments.add(pair2);
    }
}
