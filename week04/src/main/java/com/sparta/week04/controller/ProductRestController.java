package com.sparta.week04.controller;

import com.sparta.week04.models.Product;

import com.sparta.week04.models.ProductMypriceRequestDto;
import com.sparta.week04.models.ProductRepository;

import com.sparta.week04.models.ProductRequestDto;
import com.sparta.week04.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductRestController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto){

        Product product = new Product(requestDto);
        productRepository.save(product);// 바로 return값으로 넘기면 com.sparta.week04.models.Product@31e94bbe식으로 넘어가서 오류남
        return product;
    }
    @PutMapping("/api/products/{targetId}")
    public Long updateProduct(@PathVariable Long targetId,@RequestBody ProductMypriceRequestDto requestDto){
        return productService.update(targetId,requestDto);
    }
}