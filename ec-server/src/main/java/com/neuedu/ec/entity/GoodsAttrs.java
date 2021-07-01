package com.neuedu.ec.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品属性
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_attrs")
public class GoodsAttrs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Integer attr_id;

    /**
     * 商品ID
     */
    private Integer goods_id;

    /**
     * 属性名称
     */
    private String attr_name;

    /**
     * 属性属性值
     */
    private String attr_value;

    /**
     * 属性价格
     */
    private Integer add_price;

    /**
     * 属性多值
     */
    private String atrr_vals;

    /**
     * attr_sel
     */
    private String attr_sel;

    /**
     * attr_write
     */
    private String attr_write;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
