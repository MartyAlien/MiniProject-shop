package com.neuedu.ec.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.ec.common.CommonResult;
import com.neuedu.ec.common.EcPage;
import com.neuedu.ec.entity.Goods;
import com.neuedu.ec.service.IGoodsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品管理
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EcPage {
        private long total; //总条数
        private Integer pagenum;//总页数
        private List<Goods> goods;//商品列表
    }



    /**
     * 商品列表搜索
     * @param query 关键字
     * @param cid 分类
     * @param pagenum 页码
     * @param pagesize 页容量
     * @return
     */
    @RequestMapping("/search")
    public CommonResult<EcPage> search(String query,
                                       Integer cid,
                                       @RequestParam(required = true,value = "pagenum",defaultValue = "1") Integer pagenum,
                                       @RequestParam(required = true,value = "pagesize",defaultValue = "20") Integer pagesize){
        EcPage page = goodsService.search(query,cid,pagenum,pagesize);
        return CommonResult.success(page);
    }

    /**
     *  商品详情
     * @param goods_id 商品id
     * @return
     */
    @RequestMapping("/detail")
    public CommonResult<Goods> detail(  Integer goods_id) throws Exception {
        if(goods_id == null){
            throw new Exception("商品id不能为空");
        }
        Goods goods = goodsService.detail(goods_id);
        return CommonResult.success(goods);
    }







}
