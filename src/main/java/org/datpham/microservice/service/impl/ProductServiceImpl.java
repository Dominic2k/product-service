package org.datpham.microservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.entity.Product;
import org.datpham.microservice.exception.ApplicationException;
import org.datpham.microservice.mapper.ProductMapper;
import org.datpham.microservice.repository.CategoryRepository;
import org.datpham.microservice.repository.ProductRepository;
import org.datpham.microservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create (CreateProductReq createProductReq) {
        var existedCategoryOptional = categoryRepository.findById(createProductReq.getCategoryId());
        if(existedCategoryOptional.isEmpty()) {
            throw new ApplicationException("Category not found");
        }
        Product creatingProduct = productMapper.fromCreateRequest(createProductReq);
        return productRepository.save(creatingProduct);
    }
}
