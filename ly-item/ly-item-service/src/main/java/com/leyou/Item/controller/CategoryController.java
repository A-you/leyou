package com.leyou.Item.controller;

import com.leyou.Item.service.CategoryService;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /***
     * 根据父节点查询分类列比饿哦
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> getCategoryListByPid(@RequestParam("pid") Long pid){
        System.out.println("进来了");
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        return ResponseEntity.ok(categories);
    }
}
