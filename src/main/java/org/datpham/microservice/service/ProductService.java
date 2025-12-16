package org.datpham.microservice.service;

import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.entity.Product;

public interface ProductService {
    Product create (CreateProductReq createProductReq);
}
