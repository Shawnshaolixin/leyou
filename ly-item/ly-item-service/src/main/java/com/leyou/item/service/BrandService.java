package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    public PageResult<Brand> queryBrandByPageAndSort(
            Integer page, Integer rows, String sortBy, Boolean desc, String key
    ) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key.toUpperCase());
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        //  Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        List<Brand> brands = brandMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(brands)) {
            throw new LyException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        PageInfo<Brand> info = new PageInfo<>(brands);
        // 返回结果

        return new PageResult<>(info.getTotal(), brands);
    }

    /**
     * 根据Id获取品牌
     *
     * @param id
     * @return
     */
    public Brand getById(Integer id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新品牌
     *
     * @param brand
     * @return
     */
    public boolean update(Brand brand) {
        int i = brandMapper.updateByPrimaryKey(brand);
        return i > 0;
    }

    /**
     * 新增品牌
     * @param brand
     * @param categoryId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Brand brand, Long categoryId) {
        brand.setId(null);
        int insert = brandMapper.insert(brand);
        brandMapper.insert_brand_category(categoryId, brand.getId());
        return insert > 0;
    }

}
