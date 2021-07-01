package com.neuedu.ec.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "goods_id", type = IdType.INPUT)
    private Integer goods_id;

    /**
     * 分类id
     */
    private Integer cat_id;

    /**
     * 商品名称
     */
    private String goods_name;

    /**
     * 商品价格
     */
    private BigDecimal goods_price;

    /**
     * 商品数量
     */
    private Integer goods_number;

    /**
     * 商品重量
     */
    private Integer goods_weight;

    /**
     * 商品大图标
     */
    private String goods_big_logo;

    /**
     * 商品小图标
     */
    private String goods_small_logo;

    /**
     * 商品介绍
     */
    private String goods_introduce;

    /**
     * 商品添加时间
     */
    private Long add_time;

    /**
     * 商品更新时间
     */
    private Long upd_time;

    /**
     * 热门商品数
     */
    private Integer hot_mumber;

    /**
     * 所属一级分类
     */
    private Integer cat_one_id;

    /**
     * 所属二级分类
     */
    private Integer cat_two_id;

    /**
     * 所属三级分类
     */
    private Integer cat_three_id;

    /**
     * 是否删除
     */
    private String is_del;

    /**
     * 是否促销
     */
    private Boolean is_promote;

    /**
     * 商品状态
     */
    private Integer goods_state;

    /**
     * 商品分类
     */
    private String goods_cat;

    /**
     * 删除时间
     */
    private LocalDateTime delete_time;

    /**
     * 数据创建时间
     */
    private LocalDateTime createtime;

    /**
     * 图片列表
     */
    @TableField(exist = false)
    private List<GoodsPic> pics;

    /**
     * 属性列表
     */
    @TableField(exist = false)
    private List<GoodsAttrs> attrs;

}
