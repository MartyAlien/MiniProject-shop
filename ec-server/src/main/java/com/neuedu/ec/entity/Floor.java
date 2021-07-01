package com.neuedu.ec.entity;

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
 * 楼层
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 一级分类名称
     */
    private String name;

    /**
     * 一级分类图片
     */
    private String image_src;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


    @TableField(exist = false)
    private List<ProductList> product_list;





}
