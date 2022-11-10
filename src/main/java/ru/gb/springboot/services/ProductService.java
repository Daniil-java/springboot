package ru.gb.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.springboot.entities.Product;
import ru.gb.springboot.repositories.ProductDao;
import ru.gb.springboot.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    private ProductDao productDao;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getList() {
//        return productRepository.findAll();
        return productDao.findAll();
    }

    public Product getProductById(long id) {
        return productDao.findById(id);
//        return productRepository.findById(id).get();
    }

    public void addProduct(Product product) {
//        productRepository.save(product);
        productDao.saveOrUpdate(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
