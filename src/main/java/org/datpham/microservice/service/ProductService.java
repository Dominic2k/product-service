package org.datpham.microservice.service;

import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.dto.request.LockProductReq;
import org.datpham.microservice.dto.request.ProductFilter;
import org.datpham.microservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product create (CreateProductReq createProductReq);
    List<Product> search (ProductFilter productFilter);
    void lock(LockProductReq lockProductReq);
}
