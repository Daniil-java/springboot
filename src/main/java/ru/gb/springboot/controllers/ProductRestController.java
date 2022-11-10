package ru.gb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.services.ProductService;

import java.util.List;

@RequestMapping("/rest/products")
@RestController
public class ProductRestController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getList() {
        return productService.getList();
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/products/delete/{id}")
    public void getProductById(@PathVariable Long id) {
        productService.delete(id);
    }
}
