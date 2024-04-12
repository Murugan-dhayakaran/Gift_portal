package com.dhaya.giftportal.repository;



import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dhaya.giftportal.model.Orders;
import com.dhaya.giftportal.model.Product;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    // @Query("SELECT r.product FROM Orders r WHERE r.price > :price")
    // List<Product> findproductsByRatingGreaterThan(@Param("price") int price);
    @Query("SELECT o FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Orders> findOrdersByOrderDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
