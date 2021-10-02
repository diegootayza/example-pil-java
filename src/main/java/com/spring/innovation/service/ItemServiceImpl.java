/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.Item;
import com.spring.innovation.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    public Item save(Item item){
        Item itemRegister = this.repository.save(item);
        return itemRegister;
    }

    public Item findByCode(String code) {
        Item saved = this.repository.findByCode(code);
        return saved;
    }
}