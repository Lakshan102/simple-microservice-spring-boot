package com.example.inventory.controller;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getInventory")
    public List<InventoryDTO> getInventory() {
        return inventoryService.getAllInventories();
    }
    @PostMapping("/addInventory")
    public InventoryDTO addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventory(inventoryDTO);
    }
    @PutMapping("/updateInventory")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.updateInventory(inventoryDTO);
    }
    @DeleteMapping("/deleteInventory/{id}")
    public String deleteInventory(@PathVariable Integer id){
        return inventoryService.deleteById(id);
    }

    @GetMapping("/getInventoriesById/{item_id}")
    public InventoryDTO getInventoryById(@PathVariable Integer item_id){
        return inventoryService.getInventoryById(item_id);
    }
}
