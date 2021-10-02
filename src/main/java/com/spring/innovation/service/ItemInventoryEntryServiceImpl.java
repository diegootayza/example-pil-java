/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.Item;
import com.spring.innovation.model.ItemInventory;
import com.spring.innovation.model.ItemInventoryEntry;
import com.spring.innovation.model.MovementType;
import com.spring.innovation.repository.ItemInventoryEntryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryEntryServiceImpl implements ItemInventoryEntryService {
    private final ItemInventoryEntryRepository repository;
    private final ItemServiceImpl itemService;
    private final ItemInventoryServiceImpl itemInventoryService;

    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository repository, ItemServiceImpl itemService, ItemInventoryServiceImpl itemInventoryService) {
        this.repository = repository;
        this.itemService = itemService;
        this.itemInventoryService = itemInventoryService;
    }

    public void register(String code, BigDecimal quantity, MovementType movementType) {
        ItemInventory itemInventory = this.itemInventoryService.findByItemCode(code);

        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setQuantity(quantity);
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setMovementType(movementType);

        this.repository.save(itemInventoryEntry);
    }
}