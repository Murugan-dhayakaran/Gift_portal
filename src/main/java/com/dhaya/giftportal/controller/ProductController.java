package com.dhaya.giftportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private com.dhaya.giftportal.service.ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product Product) {
        Product savedProduct = productService.addproduct(Product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product Product = productService.getproductById(id);
        if (Product != null) {
            return ResponseEntity.ok(Product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> Product = productService.getAllproduct();
        return ResponseEntity.ok(Product);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Product successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct!= null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @Query
    @GetMapping("/products/year/{year}")
    public ResponseEntity<List<Product>> getProductsByManufactureYear(@PathVariable String year) {
        List<Product> products = productService.findProductsByManufactureYear(year);
        return ResponseEntity.ok(products);
    }

    //    @GetMapping("/Product/rating/{rating}")
    //    public ResponseEntity<List<Product>> getProductByRatingGreaterThan(@PathVariable float rating) {
    //        List<Product> Product = ProductService.findProductByRatingGreaterThan(rating);
    //        return ResponseEntity.ok(Product);
    //    }
   
    //    @GetMapping("/Product/year/{year}")
    //    public ResponseEntity<List<Product>> getProductByYear(@PathVariable String year) {
    //        List<Product> Product = ProductService.findProductByYear(year);
    //        return ResponseEntity.ok(Product);
    //    }
}
