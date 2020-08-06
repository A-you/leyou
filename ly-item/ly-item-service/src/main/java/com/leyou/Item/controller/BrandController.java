package com.leyou.Item.controller;

import com.leyou.Item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
public class BrandController {
//    /brand/page?key=&page=1&rows=5&sortBy=id&desc=false

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public void getBrandByPage(@RequestParam(value = "page" , defaultValue = "1") Integer page,
                               @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                               @RequestParam(value = "key",defaultValue = "") String key,
                               @RequestParam(value = "sortBy",defaultValue = "id") String sortBy,
                               @RequestParam(value = "desc", defaultValue = "false") Boolean desc
                               ){

        brandService.getBrandByPageMethod(page,rows,key,sortBy,desc);
    }
}
