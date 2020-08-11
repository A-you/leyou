package com.leyou.Item.controller;

import com.leyou.Item.service.BrandService;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> getBrandByCid(@PathVariable("cid") Long cid){
        List<Brand> brands = brandService.queryBrandByCid(cid);
        return ResponseEntity.ok(brands);
    }

    /*增加品牌*/
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
