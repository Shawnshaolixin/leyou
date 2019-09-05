package com.leyou.item.controller;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ApiResult;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import com.leyou.item.vo.CategoryTreeData;
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
        //如果pid的值为-1那么需要获取数据库中最后一条数据
        if (pid == -1){
            List<Category> last = this.categoryService.queryLast();
            return ResponseEntity.ok(last);
        }
        else {
            List<Category> list = this.categoryService.queryCategoryByPid(pid);
            if (list == null) {
                //没有找到返回404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            //找到返回200
            return ResponseEntity.ok(list);
        }
    }
    @GetMapping("tree")
    public ApiResult<List<CategoryTreeData>> getAll(){
        List<CategoryTreeData> categoryTreeData = categoryService.queryTreeData((long) 0);
        return ApiResult.ok(categoryTreeData);
    }
    @PostMapping("add")
    public ResponseEntity<Boolean> add(Category category
    ){
        System.out.println("控制器==="+category.toString());
        if(category.getId()>0){
            categoryService.update(category);
        }else {
            Boolean add = categoryService.add(category);
            if(add){
                return ResponseEntity.ok(add);
            }
        }

        throw new LyException(ExceptionEnum.CATEGORY_ADD_FAILED);
    }
}
