package ru.gb.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.api.ProductDTO;
import ru.gb.api.ResourceNotFoundException;
import ru.gb.products.entities.Product;
import ru.gb.products.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/v1/app")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

//    @GetMapping("/products")
//    public String getList(Model model) {
//        model.addAttribute("products", productService.getList());
//        return "products";
//    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
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

    //CRUD
//    получение товара по id [ GET .../app/products/{id} ]
//    получение всех товаров [ GET .../app/products ]
//    создание нового товара [ POST .../app/products ]
//    удаление товара по id.[ GET .../app/products/delete/{id} ]


}
