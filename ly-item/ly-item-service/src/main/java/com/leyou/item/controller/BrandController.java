package com.leyou.item.controller;

import com.leyou.common.vo.ApiResult;
import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "20") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
    ) {
        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);
        return ResponseEntity.ok(result);
    }

    @GetMapping("get")
    public ResponseEntity<Brand> getById(
            @RequestParam(value = "id", required = true) Integer id

    ) {
        Brand brand = this.brandService.getById(id);
        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(brand);
    }

    @PostMapping("update")
    public ResponseEntity<Boolean> update(Brand brand) {
        boolean result = this.brandService.update(brand);
        if (result) {
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("add")
    public ApiResult<Void> add(Brand brand,@RequestParam("cid") Long cid) {
        this.brandService.add(brand,cid);
        return ApiResult.ok();
    }
}
