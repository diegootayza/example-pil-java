/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.Item;
import com.spring.innovation.model.ItemInventory;
import com.spring.innovation.repository.ItemInventoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryServiceImpl implements ItemInventoryService {
    private final ItemInventoryRepository repository;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository) {
        this.repository = repository;
    }

    public ItemInventory findByItemCode(String code){
        ItemInventory itemInventory = this.repository.findByItemCode(code);
        return itemInventory;
    }

    public void transaction(String code, BigDecimal unitPrice, BigDecimal quantity) {
        ItemInventory itemInventory = this.findByItemCode(code);
        BigDecimal totalPrice = itemInventory.getTotalPrice().add(unitPrice.multiply(quantity));
        itemInventory.setTotalPrice(totalPrice);
        itemInventory.setStockQuantity(itemInventory.getStockQuantity().add(quantity));

        this.repository.save(itemInventory);
    }

    public ItemInventory createItemInventoryEmpty(Item item) {
        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setTotalPrice(BigDecimal.ZERO);
        itemInventory.setStockQuantity(BigDecimal.ZERO);
        itemInventory.setLowerBoundThreshold(BigDecimal.TEN);
        itemInventory.setUpperBoundThreshold(new BigDecimal("100"));
        ItemInventory saved = this.repository.save(itemInventory);

        return saved;
    }
}