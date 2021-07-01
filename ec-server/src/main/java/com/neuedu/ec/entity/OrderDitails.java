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
 * 订单详情
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_ditails")
public class OrderDitails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 详情id
     */
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Integer detail_id;

    /**
     * 商品ID
     */
    private Integer goods_id;

    /**
     * 订单id
     */
    private Integer order_id;

    /**
     * 购买数量
     */
    private Integer goods_number;

    /**
     * 数据创建时间
     */
    private LocalDateTime createtime;


}
