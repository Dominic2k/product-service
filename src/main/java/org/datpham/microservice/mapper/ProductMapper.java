package org.datpham.microservice.mapper;

import org.datpham.microservice.dto.request.CreateProductReq;
import org.datpham.microservice.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromCreateRequest(CreateProductReq createProductReq);
}
