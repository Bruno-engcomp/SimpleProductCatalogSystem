package br.com.dio.inventory.controller;


import br.com.dio.inventory.DTO.InventoryRequest;
import br.com.dio.inventory.DTO.InventoryResponse;
import br.com.dio.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> postInventory (@RequestBody InventoryRequest inventoryRequest)
    {
        InventoryResponse inventoryResponse = inventoryService.inventoryCreate(inventoryRequest);
        return new ResponseEntity<>(inventoryResponse, HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<InventoryResponse> getInventory (@PathVariable Long id)
    {
        InventoryResponse inventoryResponse = inventoryService.inventoryRead(id);
        return ResponseEntity.ok(inventoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllIinventory ()
    {
        List<InventoryResponse> allInventories = inventoryService.inventoryReadAll();
        return ResponseEntity.ok(allInventories);
    }

    // Endpoint para aumentar estoque
    // Ex: PATCH /api/inventory/1/increase?quantity=10
    @PatchMapping("/{id}/increase")
    public ResponseEntity<InventoryResponse> increase(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.increaseStock(id, quantity));
    }

    // Endpoint para diminuir estoque
    // Ex: PATCH /api/inventory/1/decrease?quantity=5
    @PatchMapping("/{id}/decrease")
    public ResponseEntity<InventoryResponse> decrease(@PathVariable Long id, @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.decreseStock(id, quantity));
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteInventory (@PathVariable Long id)
    {
        inventoryService.inventoryDelete(id);
        return ResponseEntity.noContent().build();
    }
}
