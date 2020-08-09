package com.leyou.Item.controller;

import com.leyou.Item.service.SpecifcationService;
import com.leyou.item.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecifcationController {

    @Autowired
    private SpecifcationService specifcationService;

    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> getSpecGroupByCid (@PathVariable("cid") Long cid){
        List<SpecGroup> specGroups = specifcationService.querSpecGroupByCid(cid);
        return ResponseEntity.ok(specGroups);
    }
}
