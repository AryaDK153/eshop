package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
//    @Mock
    Product product;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    List<Product> productList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
    }
    @Test
    void testCreateProduct() {
//        doAnswer(invocationOnMock -> {
//            Product productArg = invocationOnMock.getArgument(0);
//            productList.add(productArg);
//            return productArg;
//        }).when(productRepository).create(any());

        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
//        when(productRepository.findAll()).thenReturn(productList.iterator());

//        assertEquals(createdProduct, productService.findAll().get(0));
    }
    @Test
    void testUpdateProduct() {
//        doAnswer(invocationOnMock -> {
//            Product productArg = invocationOnMock.getArgument(0);
//            productList.add(productArg);
//            return productArg;
//        }).when(productRepository).create(any());
//        productService.create(product);
        productList.add(product);

        // The unit was made to only save changes
        // finding the product as well as its index and changing the values, was done in controller
        when(productRepository.findAll()).thenReturn(productList.iterator());
        Product retrievedProduct = productService.findAll().get(0);

        Product updatedProduct = new Product();
        updatedProduct.setProductId(retrievedProduct.getProductId());
        updatedProduct.setProductName("Sampo Cap Belobog");
        updatedProduct.setProductQuantity(retrievedProduct.getProductQuantity());

        doAnswer(invocationOnMock -> {
            int indexArg = invocationOnMock.getArgument(0);
            Product productArg = invocationOnMock.getArgument(1);

            productList.set(indexArg, productArg);
            return productArg;
        }).when(productRepository).save(anyInt(), any(Product.class));
        productService.save(0, updatedProduct);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        retrievedProduct = productService.findAll().get(0);
        assertEquals(updatedProduct.getProductName(), retrievedProduct.getProductName());
        assertNotEquals("Sampo Cap Bambang", retrievedProduct.getProductName());
    }
    @Test
    void testDelete() {
//        doAnswer(invocationOnMock -> {
//            Product productArg = invocationOnMock.getArgument(0);
//            productList.add(productArg);
//            return productArg;
//        }).when(productRepository).create(any());
//        productService.create(product);
        productList.add(product);

        // The unit was made to only delete
        // finding the product as well as its index, was done in controller
        doAnswer(invocationOnMock -> {
            int indexArg = invocationOnMock.getArgument(0);

            productList.remove(indexArg);
            return null;
        }).when(productRepository).delete(anyInt());
        productService.delete(0);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        assertTrue(productService.findAll().isEmpty());
    }
    @Test
    void testFindIndex_isFound() {
//        doAnswer(invocationOnMock -> {
//            Product productArg = invocationOnMock.getArgument(0);
//            productList.add(productArg);
//            return productArg;
//        }).when(productRepository).create(any());
//        productService.create(product);
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        int foundIndex = productService.findIndex(product.getProductName());
        assertEquals(0, foundIndex);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        assertFalse(productService.findAll().isEmpty());
    }
    @Test
    void testFindIndex_isNotFound() {
//        doAnswer(invocationOnMock -> {
//            Product productArg = invocationOnMock.getArgument(0);
//            productList.add(productArg);
//            return productArg;
//        }).when(productRepository).create(any());
//        productService.create(product);
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        int foundIndex = productService.findIndex("Sampo Cap Penacony");
        assertEquals(-1, foundIndex);

        when(productRepository.findAll()).thenReturn(productList.iterator());
        assertFalse(productService.findAll().isEmpty());
    }
}
