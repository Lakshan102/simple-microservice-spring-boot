package com.example.inventory.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {}.getType());
    }
    public InventoryDTO addInventory(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO){
        inventoryRepository.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }
    public String deleteById(Integer id){
        inventoryRepository.deleteById(id);
        return "user deleted";
    }

    public InventoryDTO getInventoryById(Integer item_id){
        Inventory inventory=inventoryRepository.getInventoryById(item_id);
        return modelMapper.map(inventory,InventoryDTO.class);
    }
}
