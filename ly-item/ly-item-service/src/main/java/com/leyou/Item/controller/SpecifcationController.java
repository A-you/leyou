package com.leyou.Item.controller;

import com.leyou.Item.service.SpecifcationService;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> getParamsByGigCid(@RequestParam(value = "gid",required = false) Long gid,
                                                             @RequestParam(value = "cid",required = false) Long cid,
                                                             @RequestParam(value = "searching",required = false) Boolean searching
                                                             ){
        return ResponseEntity.ok(specifcationService.getParamsList(gid,cid,searching));
    }

    @PostMapping("param")
    public ResponseEntity createParam(@RequestBody  SpecParam specParam){
        specifcationService.createParam(specParam);
        return ResponseEntity.ok().build();
    }
}
