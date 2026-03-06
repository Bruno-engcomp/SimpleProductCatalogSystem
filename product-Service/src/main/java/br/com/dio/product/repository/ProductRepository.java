package br.com.dio.product.repository;

import br.com.dio.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
