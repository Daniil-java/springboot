package ru.gb.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.api.ProductDTO;
import ru.gb.products.converters.ProductDtoConverter;
import ru.gb.products.entities.Product;
import ru.gb.products.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductDtoConverter productDtoConverter;

    public List<ProductDTO> getList() {
        return productRepository.findAll().stream().map(x -> productDtoConverter.entityToDto(x)).collect(Collectors.toList());
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
