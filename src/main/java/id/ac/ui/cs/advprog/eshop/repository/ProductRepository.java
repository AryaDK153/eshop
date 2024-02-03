package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product save(int index, Product product) {
        productData.set(index, product);
        return product;
    }
    public void delete(int index) {
        productData.remove(index);
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

}
