package com.sparta.springcore.service;

import com.sparta.springcore.models.Product;
import com.sparta.springcore.models.ProductMypriceRequestDto;
import com.sparta.springcore.models.ProductRepository;
import com.sparta.springcore.models.ProductRequestDto;

import java.sql.*;

import java.sql.SQLException;
import java.util.List;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }



    //업데이트 요청
    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);


        productRepository.createProduct(product);

        return product;
    }

    //즐겨찾는 상품 등록
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        int myprice = requestDto.getMyprice();
        productRepository.updateMyprice(id, myprice);

        return product;
    }

    //전체 상품 가져오기
    public List<Product> getProducts() throws SQLException {
        List<Product> products = productRepository.getProducts();

        return products;
    }
}

