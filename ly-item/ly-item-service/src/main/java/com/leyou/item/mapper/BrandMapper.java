package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper  extends Mapper<Brand> {
    /**
     * 根据category id删除中间表相关数据
     * @param cid
     */
    @Delete("insert into (category_id,brand_id) values ( #{cid},#{bid}")
    boolean insert_brand_category(@Param("cid") Long cid,@Param("bid") Long bid);
}
