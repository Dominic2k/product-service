package org.datpham.microservice.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datpham.microservice.dto.BaseResponse;
import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.entity.Product;
import org.datpham.microservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<BaseResponse<Product>> create (@RequestBody @Validated CreateProductReq createProductReq){
        return ResponseEntity.ok(new BaseResponse<>(productService.create(createProductReq), "Create product successfully"));
    }
}
