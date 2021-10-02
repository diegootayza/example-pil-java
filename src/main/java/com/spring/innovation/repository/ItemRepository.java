/**
 * @author: Alan Benavides
 */

package com.spring.innovation.repository;

import com.spring.innovation.model.Item;

public interface ItemRepository extends GenericRepository<Item> {
    Item findByCode(String code);
}