package br.com.dio.inventory.service;

import br.com.dio.inventory.DTO.InventoryRequest;
import br.com.dio.inventory.DTO.InventoryResponse;
import br.com.dio.inventory.model.Inventory;
import br.com.dio.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse inventoryCreate(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory(inventoryRequest.productId(), //Criacao de novo inventario, primeiro ele chama o requestDTO e passa os parametros para o inventaroi
                inventoryRequest.quantity());

        Inventory savedInventory = inventoryRepository.save(inventory); // Salva o inventario

        return new InventoryResponse(
                savedInventory.getId(),
                savedInventory.getProductId(),
                savedInventory.getQuantity());
    }

    public InventoryResponse inventoryRead(Long id)
    {
        Inventory inventory = inventoryRepository.findById(id). // Puxa o inventario pelo id
                orElseThrow(() -> new RuntimeException("Inventário com id" + id + " não encontrado"));
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    public List<InventoryResponse> inventoryReadAll()
    {
        List<Inventory> inventories = inventoryRepository.findAll(); // Puxa uma lista de todos os inventarios
        return inventories.stream().map(inventory -> new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        )).toList();
    }

    @Transactional
    public InventoryResponse increaseStock(Long id, Integer quantity)
    {
        Inventory inventory = inventoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Inventário com id " + id + " não encontrado")); // Adiciona estoque
        inventory.setQuantity(inventory.getQuantity() + quantity);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return new InventoryResponse(
                savedInventory.getId(),
                savedInventory.getProductId(),
                savedInventory.getQuantity()
        );
    }

    @Transactional
    public InventoryResponse decreseStock(Long id, Integer quantity)
    {
        Inventory inventory = inventoryRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Inventário com id " + id + " não encontrado")); // Diminui estoque
        if(inventory.getQuantity() < quantity)
            throw new RuntimeException("Estoque insuficiente. Disponível: " + inventory.getQuantity());

        inventory.setQuantity(inventory.getQuantity() - quantity);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return new InventoryResponse(
                savedInventory.getId(),
                savedInventory.getProductId(),
                savedInventory.getQuantity()
        );
    }

    public void inventoryDelete(Long id)
    {
        Inventory inventory = inventoryRepository.findById(id) // Deleta algum inventario, com base no id informado
                .orElseThrow(() -> new RuntimeException("Inventário com id " + id + " não encontrado"));
        inventoryRepository.delete(inventory);
    }

}
