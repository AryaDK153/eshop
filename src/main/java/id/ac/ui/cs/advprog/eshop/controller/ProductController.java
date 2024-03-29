package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    private static final String SHOW_LIST = "redirect:list";

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return SHOW_LIST;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allproducts = service.findAll();
        model.addAttribute("products", allproducts);
        return "productList";
    }

    @GetMapping("/edit-or-delete")
    public String editOrDeletePage(@RequestParam String name, Model model) {
        int index = service.findIndex(name);
        if (index != -1) {
            List<Product> allproducts = service.findAll();
            Product product = allproducts.get(index);
            model.addAttribute("product", product);
            model.addAttribute("index", index);
            return "editOrDelete";
        }
        return SHOW_LIST;
    }

    @PostMapping("/edit-or-delete")
    public String editOrDeletePost(@RequestParam int index, @RequestParam("save-or-delete") String saveOrDelete, @ModelAttribute Product product, Model model) {
        if (Objects.equals(saveOrDelete, "save")) {
            service.save(index, product);
        } else if (Objects.equals(saveOrDelete, "delete")) {
            service.delete(index);
        }
        return SHOW_LIST;
    }
}
