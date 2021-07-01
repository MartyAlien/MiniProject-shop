package com.neuedu.ec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Swiperdata implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片路径
     */
    private String image_src;

    /**
     * 打开方式
     */
    private String open_type;

    /**
     * 商品id
     */
    private String goods_id;

    /**
     * 导航链接
     */
    private String navigator_url;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


}
