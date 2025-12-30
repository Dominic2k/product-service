package org.datpham.microservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datpham.microservice.dto.BaseResponse;
import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.dto.request.LockProductReq;
import org.datpham.microservice.dto.request.ProductFilter;
import org.datpham.microservice.entity.Product;
import org.datpham.microservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("search")
    public ResponseEntity<BaseResponse<List<Product>>> search (@RequestBody ProductFilter productFilter){
        List<Product> products = productService.search(productFilter);
        return ResponseEntity.ok(new BaseResponse<>(products, "Search product successfully"));
    }

    @PutMapping("/lock")
    public ResponseEntity<BaseResponse<Boolean>> create(@RequestBody @Valid LockProductReq lockProductReq) {
        productService.lock(lockProductReq);
        return ResponseEntity.ok(new BaseResponse<>(true, "success"));
    }
}
