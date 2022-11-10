package ru.gb.springboot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springboot.entities.User;
import ru.gb.springboot.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ProductsSecurityController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String usecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('READ_PRODUCTS')")
    public String productsPage() {
        return "PRODUCTS";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public String usersPage() {
        return "USERS";
    }


    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
