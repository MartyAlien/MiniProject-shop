package com.neuedu.ec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neuedu.ec.entity.Categories;
import com.neuedu.ec.mapper.CategoriesMapper;
import com.neuedu.ec.service.ICategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements ICategoriesService {

    /**
     * 查询分类
     * @return
     */
    @Override
    public List<Categories> categories() {
        QueryWrapper<Categories> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cat_level","0");
        List<Categories> list = getBaseMapper().selectList(queryWrapper);

        getChildren(list);
        return list;
    }

    /**
     * 获取下级分裂
     * @param parentList
     */
    private void getChildren(List<Categories> parentList) {
        for (Categories categories : parentList) {
            QueryWrapper<Categories> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cat_pid",categories.getCat_id());
            List<Categories> children = getBaseMapper().selectList(queryWrapper);
            getChildren(children);
            categories.setChildren(children);
        }
    }
}
