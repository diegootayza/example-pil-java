/**
 * @author: Alan Benavides
 */

package com.spring.innovation.controller;

import com.spring.innovation.dto.DtoBase;
import com.spring.innovation.service.ItemInstanceServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items-instance")
public class ItemInstanceController<D extends DtoBase> {
    private ItemInstanceServiceImpl service;

    public ItemInstanceController(ItemInstanceServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    protected void buy(@RequestBody D element){
//        this.service.algo()
    }

    @PostMapping(value = "/sell/{id}")
    protected void sell(@RequestBody D element, @PathVariable Long id){
//        this.service.algo()
    }
}
