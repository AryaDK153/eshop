package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product save(int index, Product product);
    public void delete(int index);
    public List<Product> findAll();
    public int findIndex(String name);
}
