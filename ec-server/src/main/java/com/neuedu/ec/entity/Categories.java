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
 * 商品分类
 * @author 张金山
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "cat_id", type = IdType.AUTO)
    private Integer cat_id;

    /**
     * 分类名称
     */
    private String cat_name;

    /**
     * 图标
     */
    private String cat_icon;

    /**
     * 上级分类id
     */
    private Integer cat_pid;

    /**
     * 分类级别
     */
    private Integer cat_level;

    /**
     * 分类是否被删除
     */
    private Boolean cat_deleted;

    /**
     * 数据创建时间
     */
    private LocalDateTime createtime;


    /**
     * 下级分类
     */
    @TableField(exist = false)
    private List<Categories> children;

}
