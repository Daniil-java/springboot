package ru.gb.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springboot.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
