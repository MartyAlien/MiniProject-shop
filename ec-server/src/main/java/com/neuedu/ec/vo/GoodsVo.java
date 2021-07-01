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
public class GoodsVo {
    /**
     * 商品id
     */
    private String goods_id;
    /**
     * 	购买的数量
     */
    private Integer goods_number;
    /**
     * 单价
     */
    private BigDecimal goods_price;
}
