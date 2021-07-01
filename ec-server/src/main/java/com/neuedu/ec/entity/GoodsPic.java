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
 * 商品图片
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("goods_pic")
public class GoodsPic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "pics_id", type = IdType.AUTO)
    private Integer pics_id;

    /**
     * 商品ID
     */
    private Integer goods_id;

    /**
     * 大图
     */
    private String pics_big;

    /**
     * 中图
     */
    private String pics_mid;

    /**
     * 小图
     */
    private String pics_sma;

    /**
     * 大图路径
     */
    private String pics_big_url;

    /**
     * 中图路径
     */
    private String pics_mid_url;

    /**
     * 小图路径
     */
    private String pics_sma_url;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
