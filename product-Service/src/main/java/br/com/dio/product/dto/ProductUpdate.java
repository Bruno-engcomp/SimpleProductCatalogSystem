package br.com.dio.product.dto;

import java.math.BigDecimal;

public record ProductUpdate(
        String name,
        String description,
        BigDecimal price
) {
}
