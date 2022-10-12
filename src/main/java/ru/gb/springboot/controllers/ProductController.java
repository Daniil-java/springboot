package ru.gb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/showProducts")
    public String getList(Model model) {
        model.addAttribute("products", productService.getList());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "product_info";
    }

    @GetMapping("/create")
    public String createProduct() {
        return "create_product";
    }

    @PostMapping("/create")
    public String addProduct(@ModelAttribute("product") Product product) {
        System.out.println(product.toString());
        productService.addProduct(product);
        return "redirect:/products/showProducts";
    }
}
