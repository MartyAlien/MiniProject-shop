package com.neuedu.ec.service;

import com.neuedu.ec.entity.Categories;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
public interface ICategoriesService extends IService<Categories> {

    List<Categories> categories();
}
