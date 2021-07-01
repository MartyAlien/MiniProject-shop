package com.neuedu.ec.mapper;

import com.neuedu.ec.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    Goods detail(Integer goods_id);
}
