/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.Item;
import com.spring.innovation.model.ItemInstance;
import com.spring.innovation.model.ItemInventory;
import com.spring.innovation.model.MovementType;
import com.spring.innovation.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInstanceServiceImpl implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemInventoryServiceImpl itemInventoryService;
    private final ItemInventoryEntryServiceImpl itemInventoryEntryService;
    private final ItemServiceImpl itemService;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository,
                                   ItemInventoryServiceImpl itemInventoryService,
                                   ItemInventoryEntryServiceImpl itemInventoryEntryService,
                                   ItemServiceImpl itemService) {
        this.repository = repository;
        this.itemInventoryService = itemInventoryService;
        this.itemInventoryEntryService = itemInventoryEntryService;
        this.itemService = itemService;
    }

    public ItemInstance buy(String sku, int quantity, BigDecimal price, String codeItem) {
        ItemInstance itemInstance = this.repository.findByIdentifier(sku);
        BigDecimal quantityB = new BigDecimal(quantity);
        Item item = this.itemService.findByCode(codeItem);

        if (itemInstance == null) {
            itemInstance = new ItemInstance();
            itemInstance.setItem(item);
            itemInstance.setIdentifier(sku);
            itemInstance.setPrice(price);
            this.save(itemInstance);
        }

        ItemInventory itemInventory= this.itemInventoryService.findByItemCode(itemInstance.getItem().getCode());
        if(itemInventory == null) {
            this.itemInventoryService.createItemInventoryEmpty(item);
        }

        this.itemInventoryService.transaction(codeItem, itemInstance.getPrice(), quantityB);
        this.itemInventoryEntryService.register(codeItem, quantityB.multiply(price), MovementType.BUY);

        return itemInstance;
    }

    public ItemInstance sell(String sku, int quantity, BigDecimal price, String codeItem) {
        ItemInstance itemInstance = this.repository.findByIdentifier(sku);
        BigDecimal quantityB = new BigDecimal(quantity);

        this.itemInventoryService.transaction(codeItem, itemInstance.getPrice().negate(), quantityB.negate());
        this.itemInventoryEntryService.register(codeItem, quantityB.multiply(price), MovementType.SALE);

        return itemInstance;
    }

    public ItemInstance remove(String sku, int quantity, BigDecimal price, String codeItem) {
        ItemInstance itemInstance = this.repository.findByIdentifier(sku);
        BigDecimal quantityB = new BigDecimal(quantity);

        this.itemInventoryService.transaction(codeItem, itemInstance.getPrice().negate(), quantityB.negate());
        this.itemInventoryEntryService.register(codeItem, BigDecimal.ZERO, MovementType.REMOVED);

        return itemInstance;
    }

    public ItemInstance save(ItemInstance itemInstance) {
        ItemInstance saved = this.repository.save(itemInstance);
        return saved;
    }
}