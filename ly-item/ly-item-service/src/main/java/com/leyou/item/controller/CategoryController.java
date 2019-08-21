package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable(name="bid") Long bid){
        List<Category> categories = categoryService.queryByBrandId(bid);
        if(categories==null||categories.size()<=0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("list")
    public ResponseEntity<List<Category>> list(@RequestParam(name="pid") Long pid){
        List<Category> categories = categoryService.queryCategoryByPid(pid);
        if(categories==null||categories.size()<=0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categories);
    }
}
