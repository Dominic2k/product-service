package org.datpham.microservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.dto.request.LockProductItem;
import org.datpham.microservice.dto.request.LockProductReq;
import org.datpham.microservice.dto.request.ProductFilter;
import org.datpham.microservice.entity.Product;
import org.datpham.microservice.exception.ApplicationException;
import org.datpham.microservice.mapper.ProductMapper;
import org.datpham.microservice.repository.CategoryRepository;
import org.datpham.microservice.repository.ProductRepository;
import org.datpham.microservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        creatingProduct.setDeleted(false);
//        creatingProduct.setCreatedDate(Instant.now());
//        creatingProduct.setUpdatedDate(Instant.now());
//        creatingProduct.setCreatedBy("System");
//        creatingProduct.setUpdatedBy("System");
        return productRepository.save(creatingProduct);
    }

    @Override
    public List<Product> search  (ProductFilter productFilter){
        return productRepository.findByIdIn(productFilter.getIds());
    }

    @Override
    @Transactional
    public void lock(LockProductReq lockProductReq) {
        List<LockProductItem> items = lockProductReq.getItems();

        var productIdQuantityMap = items.stream()
                .collect(Collectors.toMap(LockProductItem::getId, LockProductItem::getQuantity));

        List<Product> products = productRepository.findByIdIn(new ArrayList<>(productIdQuantityMap.keySet()));
        // todo adding validation
        products.forEach(product -> {
            product.setStock(product.getStock() - productIdQuantityMap.get(product.getId()));
        });

        productRepository.saveAll(products);
    }
}
