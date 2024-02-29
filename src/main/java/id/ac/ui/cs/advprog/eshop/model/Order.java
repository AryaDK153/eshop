package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class Order {
    private String id;
    private List<Product> products;
    private Long orderTime;
    private String author;
    @Setter
    private String status;

    public Order(String id, List<Product> products, Long orderTime, String author) {

    }
    public Order(String id, List<Product> products, Long orderTime, String author, String status) {

    }
}
