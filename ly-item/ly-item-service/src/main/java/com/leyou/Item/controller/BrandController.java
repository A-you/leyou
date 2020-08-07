package com.leyou.Item.controller;

import com.leyou.Item.service.BrandService;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResult<Brand>> getBrandByPage(@RequestParam(value = "page" , defaultValue = "1",required = false) Integer page,
                                                            @RequestParam(value = "rows",defaultValue = "5",required = false) Integer rows,
                                                            @RequestParam(value = "key",defaultValue = "",required = false) String key,
                                                            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                            @RequestParam(value = "desc", defaultValue = "false",required = false) Boolean desc
                               ){
        System.out.println("进来了");
        PageResult<Brand> brands = brandService.getBrandByPageMethod(page, rows, key, sortBy, desc);
        System.out.println("获取到的品牌"+brands);
        return ResponseEntity.ok(brands);
//        return ResponseEntity.ok(brands);

    }
}
