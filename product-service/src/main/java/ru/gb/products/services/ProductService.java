package ru.gb.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.api.ProductDto;
import ru.gb.api.ResourceNotFoundException;
import ru.gb.products.converters.ProductDtoConverter;
import ru.gb.products.entities.Product;
import ru.gb.products.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;
    private final CategoryService categoryService;

    public List<ProductDto> getList() {
        return productRepository.findAll().stream().map(x -> productDtoConverter.entityToDto(x)).collect(Collectors.toList());
    }

    public List<ProductDto> getAllProductsWithFilter(Specification<ProductDto> productSpecs) {
        return (productRepository.findAll(productSpecs));
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void createNewProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getPrice());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + productDto.getCategoryTitle() + " не найдена")));
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
