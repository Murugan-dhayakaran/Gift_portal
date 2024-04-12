package com.dhaya.giftportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dhaya.giftportal.model.Product;
import com.dhaya.giftportal.repository.ProductRepository;

import java.util.List;


@Service
public class UniversalService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllproductSorted(String sortBy) {
        return productRepository.findAll(Sort.by(sortBy));
    }

    public Page<Product> getAllproductPaged(int page, int size, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageRequest);
    }

}
