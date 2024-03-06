package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    public Payment save(Payment payment) { return null;
    }

    public Payment findById(String id) { return null;
    }

    public Iterator<Payment> findAll() { return null;
    }
}
