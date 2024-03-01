package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    Order updateStatus(String id, String value);

    Order findById(String id);

    List<Order> findAllByAuthor(String author);
}
