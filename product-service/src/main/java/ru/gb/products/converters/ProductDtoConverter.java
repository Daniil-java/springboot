package ru.gb.products.converters;

import org.springframework.stereotype.Component;
import ru.gb.api.ProductDto;
import ru.gb.products.entities.Product;

@Component
public class ProductDtoConverter {

    public ProductDto entityToDto(Product c) {
        return new ProductDto(c.getId(), c.getTitle(), c.getCost(), c.getCategory().getTitle());
    }
}
