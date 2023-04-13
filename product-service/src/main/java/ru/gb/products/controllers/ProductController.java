package ru.gb.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.ProductDto;
import ru.gb.api.ResourceNotFoundException;
import ru.gb.products.entities.Product;
import ru.gb.products.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getList();
    }

    @GetMapping("/products/{id}")
    public String getProductById(Model model, @PathVariable Long id) {
        model.addAttribute("product", getProductByIdCheck(id));
        return "product_info";
    }

    private Product getProductByIdCheck(Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("PRODUCT NOT FOUND! ID: " + id));
    }

    @GetMapping("/products/create")
    public String createProduct() {
        return "create_product";
    }

    @PostMapping("/products/create")
    public String addProduct(@ModelAttribute("product") Product product) {
        System.out.println(product.toString());
        productService.addProduct(product);
        return "redirect:/products/showProducts";
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }


}
