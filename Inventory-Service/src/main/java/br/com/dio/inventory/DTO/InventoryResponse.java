package br.com.dio.inventory.DTO;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer quantity
) {
}
