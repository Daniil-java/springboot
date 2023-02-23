package ru.gb.products.converters;

import org.springframework.stereotype.Component;
import ru.gb.api.ProductDTO;
import ru.gb.products.entities.Product;

import javax.persistence.Column;

@Component
public class ProductDtoConverter {

    public ProductDTO entityToDto(Product c) {
        return new ProductDTO(c.getId(), c.getTitle(), c.getCost());
    }
}
