package ru.gb.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.springboot.entities.Cart;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.services.CartService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public Map<Product, Long> getList() {
        Cart cart = new Cart();
        cart.setId(1l);
        return cartService.getCartList(cart);
    }

    @PostMapping
    public Map<Product, Long> addProduct(@RequestBody Cart cart, List<Product> products) {
        return cartService.addProductToCart(cart, products);
    }

    @PutMapping
    //Изменяет показатель количества
    public Map<Product, Long> updateCart(@RequestBody Cart cart, List<Product> products) {
        return cartService.updateCart(cart, products);
    }

    @DeleteMapping()
    public Map<Product, Long> getProductById(@RequestBody Cart cart, List<Product> products) {
        return cartService.deleteProductFromCart(cart, products);
    }

}
