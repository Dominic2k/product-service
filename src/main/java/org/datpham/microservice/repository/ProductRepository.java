package org.datpham.microservice.repository;

import org.datpham.microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByIdIn(List<String> ids);
    List<String> id (String id);
}
