package com.leyou.item.vo;

import lombok.Data;

import java.util.List;
@Data
public class CategoryTreeData {
    private Long id;
    private String name;
    private List<CategoryTreeData> children;
}
