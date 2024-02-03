package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public Product save(int index, Product product) {
        productRepository.save(index, product);
        return product;
    }

    @Override
    public void delete(int index) {
        productRepository.delete(index);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public int findIndex(String name) {
        int index = 0;
        for (Iterator<Product> product = productRepository.findAll(); product.hasNext(); ) {
            Product current = product.next();
            if (Objects.equals(current.getProductName(), name)) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
