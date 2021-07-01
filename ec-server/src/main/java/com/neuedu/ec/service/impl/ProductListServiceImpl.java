package com.neuedu.ec.service.impl;

import com.neuedu.ec.entity.ProductList;
import com.neuedu.ec.mapper.ProductListMapper;
import com.neuedu.ec.service.IProductListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类列表 服务实现类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Service
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements IProductListService {

}
