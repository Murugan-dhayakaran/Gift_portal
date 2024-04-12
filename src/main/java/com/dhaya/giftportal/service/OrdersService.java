package com.dhaya.giftportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhaya.giftportal.model.Orders;
import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.repository.OrdersRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;


    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public Orders getordersById(Long id) {
        Optional<Orders> optionalorders = ordersRepository.findById(id);
        return optionalorders.orElse(null);
    }

    public List<Orders> getAllorders() {
        return ordersRepository.findAll();
    }

    public boolean deleteordersById(Long id) {
        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // public Orders updateorders(Long id, Orders updatedorders) {
    //     Optional<Orders> optionalorders = ordersRepository.findById(id);
    //     if (optionalorders.isPresent()) {
    //         Orders orders = optionalorders.get();
    //         orders.setordersData(updatedorders.getorderData());
    //         orders.setordersDate(updatedorders.getorderDate());
    //         return ordersRepository.save(orders);
    //     }
    //     return null;
    // }



     // Method to find orders by order date between two dates
     public List<Orders> findOrdersByOrderDateBetween(Date startDate, Date endDate) {
        return ordersRepository.findOrdersByOrderDateBetween(startDate, endDate);
    }

    // Method to find products by rating greater than a specified price
    public List<Product> findProductsByRatingGreaterThan(int price) {
        // Assuming you want to use the commented-out query
        // Uncomment and modify the query as needed
        // return ordersRepository.findproductsByRatingGreaterThan(price);
        return null; // Replace with actual implementation
    }
}
