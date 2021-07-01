package com.neuedu.ec.service;

import com.neuedu.ec.controller.GoodsController;
import com.neuedu.ec.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
public interface IGoodsService extends IService<Goods> {

    GoodsController.EcPage search(String query, Integer cid, Integer pagenum, Integer pagesize);

    Goods detail(Integer goods_id);
}
