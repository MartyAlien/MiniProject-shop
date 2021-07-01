package com.neuedu.ec.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: tanyajun
 * @CreateTime: 2021/4/22
 * @Blog: https://tanyajun.top
 */
@Data
public class OrderVo {
    /**
     * 订单总价格
     */
    private BigDecimal order_price;
    /**
     * 收货地址
     */
    private String consignee_addr;

    /**
     * 商品信息
     */
    private GoodsVo[] goods;

}
