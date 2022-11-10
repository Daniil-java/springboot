package ru.gb.springboot.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.gb.springboot.entities.Product;

import java.util.List;


@Repository
public class ProductDao {
    //CRUD

    private final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    private Session session;

    public Product findById(Long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        System.out.println(product);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Product> findAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from products", Product.class).list();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    public void deleteById(Long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }

    public Product saveOrUpdate(Product product) {
        session = sessionFactory.getCurrentSession();
        Product savedProduct = new Product();
        session.beginTransaction();
        session.save(product);
        savedProduct = session.get(Product.class, product.getId());
        session.getTransaction().commit();
        session.close();
        return savedProduct;
    }
}
