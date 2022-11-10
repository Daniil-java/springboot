package ru.gb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/app")
public class ProductListController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getList(Model model) {
        model.addAttribute("products", productService.getList());
        return "product_list";
    }

    @GetMapping("/products/delete/{id}")
    public String getProductById(Model model, @PathVariable Long id) {
        productService.delete(id);
        model.addAttribute("products", productService.getList());
        return "product_list";
    }

}
