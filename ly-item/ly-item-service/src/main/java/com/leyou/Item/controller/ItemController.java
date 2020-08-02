package com.leyou.Item.controller;


import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.pojo.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityResult;

@RestController
@RequestMapping("item")
public class ItemController {

    @PostMapping
    public ResponseEntity saveItem(Item item){
        if(item.getPrice() == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            throw new LyException(ExceptionEnum.PRICE_NOT_NULL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
}
