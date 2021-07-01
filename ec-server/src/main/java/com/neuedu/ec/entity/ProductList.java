package com.neuedu.ec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品分类列表
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_list")
public class ProductList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼层id
     */
    private Integer fid;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片路径
     */
    private String image_src;

    /**
     * 图片宽度
     */
    private String image_width;

    /**
     * 打开方式
     */
    private String open_type;

    /**
     * 跳转连接
     */
    private String navigator_url;

    /**
     * 创建时间
     */
    private LocalDateTime createtime2;


}
