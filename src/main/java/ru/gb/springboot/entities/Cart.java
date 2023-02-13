package ru.gb.springboot.entities;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {

    private List<OrderItem> items;
    private Double totalPrice;

    public void add(Product product) {
        for (OrderItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.incrementQuantity();
                recalculate();
                return;
            }
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setItemPrice(product.getCost());
        orderItem.setQuantity(0L);
        orderItem.setId(0L);
        orderItem.setTotalPrice(0.0);
        items.add(orderItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0d;
    }

    private void recalculate() {
        totalPrice = 0d;
        items.forEach(i -> totalPrice += i.getItemPrice());
    }



}
