package com.dhaya.giftportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.repository.OrdersRepository;
import com.dhaya.giftportal.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private OrdersRepository ordersRepository;

    public Product addproduct(Product product) {
        return productRepository.save(product);
    }

    public Product getproductById(Long id) {
        Optional<Product> optionalproduct = productRepository.findById(id);
        return optionalproduct.orElse(null);
    }

    public List<Product> getAllproduct() {
        return productRepository.findAll();
    }

    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductName(updatedProduct.getProductName());
            product.setProductId(updatedProduct.getProductId());
            product.setPrice(updatedProduct.getPrice());
            // book.setManufacture(updatedProduct.getmanufacture());
            return productRepository.save(product);
        }
        return null;
    }

    public Page<Product> getAllProductPaged(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageRequest);
    }

    public List<Product> getAllProductSorted(String sortBy) {
        return productRepository.findAll(Sort.by(sortBy));
    }

    public List<Product> findProductsByManufactureYear(String year) {
        return entityManager.createQuery(
            "SELECT p FROM Product p WHERE p.manufacture = :year", Product.class)
            .setParameter("year", year)
            .getResultList();
    // public List<Product> findProductByRatingGreaterThan(float rating) {
    //     return ordersRepository.findproductsByRatingGreaterThan(rating);
    // }

    // public List<Product> findProductByYear(String year) {
    //     return productRepository.findproductsByYear(year);
    // }
    }
}

