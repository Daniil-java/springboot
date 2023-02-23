package ru.gb.api;

public class ProductDTO {

    private Long id;
    private String title;

    private Double cost;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public ProductDTO(Long id, String title, Double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
