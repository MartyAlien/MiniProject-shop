package com.neuedu.ec.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_number", type = IdType.AUTO)
    private Integer order_number;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 订单总价格
     */
    private BigDecimal order_price;

    /**
     * 收货地址
     */
    private String consignee_addr;

    /**
     * 订单支付方式
     */
    private String order_pay;

    /**
     * 订单支付状态
     */
    private String pay_status;

    /**
     * 订单生成时间
     */
    private LocalDateTime createtime;


}
