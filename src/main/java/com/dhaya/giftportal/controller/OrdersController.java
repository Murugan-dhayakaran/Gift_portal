package com.dhaya.giftportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dhaya.giftportal.model.Orders;
import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.model.User;
import com.dhaya.giftportal.service.OrdersService;
import com.dhaya.giftportal.service.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    @Autowired 
    private UserService userService;
    

    @PostMapping("/addOrders")
    public ResponseEntity<Orders> addOrders(@RequestBody Orders orders) {
        User user = userService.getUserById(orders.getUserId());
        Product Product = productService.getproductById(orders.getProductId());

        if (user == null || Product == null) {
            return ResponseEntity.notFound().build();
        }

        orders.setUser(user);
        orders.setProduct(Product);

        Orders savedOrders = ordersService.addOrders(orders);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }

    @GetMapping("/getOrdersById/{id}")
    public ResponseEntity<Orders> getOrdersById(@PathVariable Long id) {
        Orders orders = ordersService.getordersById(id);
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllorders();
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/deleteOrdersById/{id}")
    public ResponseEntity<String> deleteOrdersById(@PathVariable Long id) {
        boolean deleted = ordersService.deleteordersById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Orders successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PutMapping("/updateOrders/{id}")
    // public ResponseEntity<Orders> updateOrders(@PathVariable Long id, @RequestBody Orders Orders) {
    //     Orders updatedOrders = OrdersService.updateorders(id, Orders);
    //     if (updatedOrders != null) {
    //         return ResponseEntity.ok(updatedOrders);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

  
    @GetMapping("/orders/date")
    public ResponseEntity<List<Orders>> getOrdersByOrderDateBetween(
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate) {

        List<Orders> orders = ordersService.findOrdersByOrderDateBetween(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to fetch products by rating greater than a specified price
    @GetMapping("/products/rating")
    public ResponseEntity<List<Product>> getProductsByRatingGreaterThan(@RequestParam("price") int price) {
        List<Product> products = ordersService.findProductsByRatingGreaterThan(price);
        return ResponseEntity.ok(products);
    }
}

