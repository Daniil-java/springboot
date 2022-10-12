package ru.gb.springboot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;

}
