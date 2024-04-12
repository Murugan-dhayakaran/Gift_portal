package com.dhaya.giftportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.service.UniversalService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UniversalController {

    @Autowired
    private com.dhaya.giftportal.service.UniversalService universalService;


    //pagination and sorting
    @GetMapping("/Product")
    public ResponseEntity<Page<Product>> getproduct(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "bookId") String sortBy) {

        Page<Product> productPage = universalService.getAllproductPaged(page, size, sortBy);

        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/sortedProduct")
    public ResponseEntity<List<Product>> getSortedProduct(@RequestParam String sortBy) {
        List<Product> sortedproduct = universalService.getAllproductSorted(sortBy);
        return ResponseEntity.ok(sortedproduct);
    }
}
