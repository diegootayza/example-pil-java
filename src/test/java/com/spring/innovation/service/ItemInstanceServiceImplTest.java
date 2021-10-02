package com.spring.innovation.service;

import com.spring.innovation.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemInstanceServiceImplTest {
    @Autowired
    ItemInstanceServiceImpl itemInstanceService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    SubCategoryServiceImpl subCategoryService;
    @Autowired
    ItemInventoryServiceImpl itemInventoryService;
    @Autowired
    ItemInventoryEntryServiceImpl itemInventoryEntryService;
    @Autowired
    ItemServiceImpl itemService;

    @BeforeEach
    protected void insertData(){
        Category category = new Category();
        category.setCode("belleza");
        category.setName("Belleza");
        Category categorySave = this.categoryService.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(categorySave);
        subCategory.setCode("rimel");
        subCategory.setName("Rimel");
        this.subCategoryService.save(subCategory);

        Set<SubCategory> hashSet = category.getSubCategories();
        hashSet.add(subCategory);
        category.setSubCategories(hashSet);


        Item item = new Item();
        item.setCode("rimel x");
        item.setName("Rimel X de Y");
        item.setSubCategory(subCategory);
        this.itemService.save(item);

        Item saved = this.itemService.findByCode("rimel x");
    }

    ItemInstance createItemInstance(Item item) {
        ItemInstance iI = new ItemInstance();
        iI.setIdentifier("1234567890");
        iI.setItem(item);
        iI.setPrice(BigDecimal.TEN);
        ItemInstance itemInstance = this.itemInstanceService.save(iI);
        return itemInstance;
    }

//    @Test
//    protected void addItemInstanceNotCreated() {
//        Item saved = this.itemService.findByCode("rimel x");
//
//        this.itemInstanceService.buy("123456789", 10, BigDecimal.ONE, saved.getCode());
//        ItemInventory itemInventory = this.itemInventoryService.findByItemCode(saved.getCode());
//
//
//        assertTrue(itemInventory.getStockQuantity().compareTo(BigDecimal.TEN) == 0);
//        assertTrue(itemInventory.getTotalPrice().compareTo(BigDecimal.TEN) == 0);
//    }

    @Test
    protected void addItemInstanceCreated() {
        Item saved = this.itemService.findByCode("rimel x");
        ItemInstance itemInstance = this.createItemInstance(saved);

        this.itemInstanceService.buy("123456789", 10, BigDecimal.ONE, saved.getCode());
        ItemInventory itemInventory = this.itemInventoryService.findByItemCode(saved.getCode());

        assertTrue(itemInventory.getStockQuantity().compareTo(BigDecimal.TEN) == 0);
        assertTrue(itemInventory.getTotalPrice().compareTo(BigDecimal.TEN) == 0);
    }
}