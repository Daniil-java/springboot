package ru.gb.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springboot.entities.Cart;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.repositories.CartRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Map<Product, Long> getCartList(Cart cart) {
        Cart newCart = cartRepository.findById(cart.getId()).get();
        Map<Product, Long> products = newCart.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return products;
    }

    @Transactional
    public Map<Product, Long> addProductToCart(Cart cart, List<Product> products) {
        products.stream().forEach(x -> cart.addProduct(x));
        cartRepository.save(cart);
        return getCartList(cart);
    }

    @Transactional
    public Map<Product, Long> deleteProductFromCart(Cart cart, List<Product> products) {
        products.stream().forEach(x -> products.remove(x));
        cartRepository.save(cart);
        return getCartList(cart);
    }

    @Transactional
    public Map<Product, Long> updateCart(Cart cart, List<Product> products) {
        cart.getProducts().clear();
        products.stream().forEach(x -> cart.addProduct(x));
        cartRepository.save(cart);
        return getCartList(cart);
    }
}
