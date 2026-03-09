package br.com.dio.inventory.DTO;

public record InventoryRequest (
        Long productId,
        Integer quantity
) {
}
