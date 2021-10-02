/**
 * @author: Alan Benavides
 */

package com.spring.innovation.repository;

import com.spring.innovation.model.ItemInstance;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {
    ItemInstance findByIdentifier(String sku);
}