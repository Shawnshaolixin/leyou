package com.leyou.item.controller;

import com.leyou.common.vo.ApiResult;
import com.leyou.common.vo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    /**
     * 分页查询SPU
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    public ApiResult<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable") Boolean saleable,
            @RequestParam(value = "key", required = false) String key) {
        // 分页查询spu信息
        PageResult<SpuBo> result = this.goodsService.querySpuByPageAndSort(page, rows,saleable, key);
        if (result == null || result.getItems().size() == 0) {
            return ApiResult.error("没有查询到数据");
        }
        return ApiResult.ok(result);
    }

    @GetMapping("query")
    @ResponseBody
    public ApiResult<PageResult<SpuBo>> query(){
        return ApiResult.error("没有获取到数据");
    }
}

