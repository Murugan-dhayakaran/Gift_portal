package com.dhaya.giftportal.repository;

import com.dhaya.giftportal.model.Product;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsByPriceGreaterThan(@Param("price") int price);

    // Custom query to find products by manufacture year (assuming year is a String attribute)
    @Query("SELECT p FROM Product p WHERE p.manufacture = :year")
    List<Product> findProductsByManufactureYear(@Param("year") String year);
    // @Query("SELECT b FROM Price b WHERE b.price = :price")
    // List<Product> findproductsByYear(@Param("price") String price);

}
