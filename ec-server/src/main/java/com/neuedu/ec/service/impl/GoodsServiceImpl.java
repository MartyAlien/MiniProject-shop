package com.neuedu.ec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.ec.controller.GoodsController;
import com.neuedu.ec.entity.Goods;
import com.neuedu.ec.mapper.GoodsMapper;
import com.neuedu.ec.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public GoodsController.EcPage search(String query, Integer cid, Integer pagenum, Integer pagesize) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(query)){
            queryWrapper.like("goods_name",query);
        }
        if(cid != null){
            queryWrapper.eq("cat_id",cid);
        }

        Page<Goods> page = this.page(new Page<Goods>(pagenum,pagesize), queryWrapper);

        GoodsController.EcPage ecPage = new GoodsController.EcPage();

        ecPage.setGoods(page.getRecords());
        ecPage.setPagenum(pagenum);
        ecPage.setTotal(page.getTotal());

        return ecPage;
    }

    @Override
    public Goods detail(Integer goods_id) {
        return getBaseMapper().detail(goods_id);
    }
}
