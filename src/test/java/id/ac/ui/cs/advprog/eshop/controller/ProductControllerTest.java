package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    Model model;
    @Mock
    ProductService service;
    @InjectMocks
    ProductController controller;
    List<Product> productList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productList = new ArrayList<>();
    }
    @Test
    void testCreateProductPage() {
        String viewName = controller.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }
    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).create(product);
    }
    @Test
    void testProductListPage() {
        String viewName = controller.productListPage(model);
        assertEquals("productList", viewName);

//        Product product = new Product();
//        productList.add(product);
//        verify(model).addAttribute(eq("products"), productList);
        verify(model).addAttribute(eq("products"), anyList());
    }
    @Test
    void testEditOrDeletePage_ifIndexFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productList.add(product);

        when(service.findIndex(any())).thenReturn(0);
        when(service.findAll()).thenReturn(productList);
        String viewName = controller.editOrDeletePage(product.getProductName(), model);
        assertEquals("editOrDelete", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
        verify(model).addAttribute(eq("index"), anyInt());
    }
    @Test
    void testEditOrDeletePage_ifIndexNotFound() {
        when(service.findIndex(any())).thenReturn(-1);
        String redirectView_viewName = controller.editOrDeletePage("Sampo Cap Bambang", model);
        assertNotEquals("editOrDelete", redirectView_viewName);
        assertEquals("redirect:list", redirectView_viewName);
    }
    @Test
    void testEditOrDeletePost_editAction() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productList.add(product);

        doAnswer(invocationOnMock -> {
            int indexArg = invocationOnMock.getArgument(0);
            Product productArg = invocationOnMock.getArgument(1);
            productList.set(indexArg, productArg);
            return productArg;
        }).when(service).save(anyInt(), any(Product.class));

        Product editedProduct = new Product();
        editedProduct.setProductId(product.getProductId());
        editedProduct.setProductName("Kecap Black Swan");
        editedProduct.setProductQuantity(50);
        int index = 0;
        String redirectViewName = controller.editOrDeletePost(index, "save", editedProduct, model);

        assertEquals("redirect:list", redirectViewName);
        verify(service).save(index, editedProduct);

        Product savedProduct = productList.get(index);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertNotEquals(product.getProductName(), savedProduct.getProductName());
        assertNotEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testEditOrDeletePost_deleteAction() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productList.add(product);

        doAnswer(invocationOnMock -> {
            int indexArg = invocationOnMock.getArgument(0);
            productList.remove(indexArg);
            return null;
        }).when(service).delete(anyInt());
        int index = 0;
        String redirectViewName = controller.editOrDeletePost(index, "delete", any(Product.class), model);
        verify(service).delete(index);
        assertEquals("redirect:list", redirectViewName);
        assertTrue(productList.isEmpty());
    }
}
