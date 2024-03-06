package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepository {
    private List<Map<Payment, Order>> paymentData = new ArrayList<>();
    public Map<Payment, Order> save(Map<Payment, Order> pair) {
        paymentData.add(pair);
        return pair;
    }

    public Map<Payment, Order> findById(String id) {
        for (Map<Payment, Order> pair : paymentData) {
            Payment payment = pair.keySet().stream().toList().get(0);
            if (payment.getId() == id) {
                return pair;
            }
        }
        return null;
    }

    public Iterator<Map<Payment, Order>> findAll() {
        return paymentData.iterator();
    }
}
