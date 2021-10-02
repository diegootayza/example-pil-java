/**
 * @author: Alan Benavides
 */

package com.spring.innovation.repository;

import com.spring.innovation.model.ItemInventory;
import org.springframework.data.jpa.repository.Query;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory> {
    @Query("SELECT it FROM ItemInventory it WHERE it.item.code = ?1")
    ItemInventory findByItemCode(String code);
}