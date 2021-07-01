package com.neuedu.ec.controller;


import com.neuedu.ec.common.CommonResult;
import com.neuedu.ec.entity.Categories;
import com.neuedu.ec.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类
 */
@RestController
public class CategoriesController {

    @Autowired
    ICategoriesService categoriesService;

    /**
     * 商品分类
     * @return
     */
    @RequestMapping("/categories")
    public CommonResult<List<Categories>> categories(){
        return CommonResult.success(categoriesService.categories());
    }
}
