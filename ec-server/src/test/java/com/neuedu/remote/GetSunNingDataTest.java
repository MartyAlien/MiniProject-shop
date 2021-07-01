package com.neuedu.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neuedu.ec.ServerApp;
import com.neuedu.ec.entity.*;
import com.neuedu.ec.service.*;
import com.neuedu.entity.CatitemsJson;
import com.neuedu.entity.FloordataJson;
import com.neuedu.entity.SwiperdataJson;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import sun.awt.image.ImageWatched;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ServerApp.class)
public class GetSunNingDataTest {

    @Autowired
    ISwiperdataService swiperdataService;
    @Autowired
    ICatitemsService catitemsService;
    @Autowired
    IFloorService floorService;
    @Autowired
    IProductListService productListService;
    @Autowired
    ICategoriesService categoriesService;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IGoodsPicService goodsPicService;
    @Autowired
    IGoodsAttrsService goodsAttrsService;



    RestTemplate restTemplate = new RestTemplate();

    String api = "https://api-hmugo-web.itheima.net/api/public/v1";

    @Test
    public void getWwiperdata(){
        SwiperdataJson swiperdata = restTemplate.getForObject(api+"/home/swiperdata", SwiperdataJson.class);

        List<Swiperdata> list = swiperdata.getMessage();
        boolean success = swiperdataService.saveBatch(list);
        System.out.println(success);
    }

    @Test
    public void getCatitems(){
        CatitemsJson catitemsJson = restTemplate.getForObject(api+"/home/catitems", CatitemsJson.class);

        List<Catitems> list = catitemsJson.getMessage();
        boolean success = catitemsService.saveBatch(list);
        System.out.println(success);
    }

    /**
     * 重置mysql id自增
     * DELETE FROM FLOOR;
     * ALTER TABLE FLOOR AUTO_INCREMENT= 1;
     */
    @Test
    public void getFloor() throws InvocationTargetException, IllegalAccessException {
        Map map = restTemplate.getForObject(api+"/home/floordata", Map.class);

        ArrayList<LinkedHashMap> obj = (ArrayList) map.get("message");
        for (LinkedHashMap item : obj) {
            LinkedHashMap floor_title = (LinkedHashMap) item.get("floor_title");

            Floor floor = new Floor();
            floor.setName((String) floor_title.get("name"));
            floor.setImage_src((String) floor_title.get("image_src"));

            boolean success = floorService.save(floor);
            System.out.println(floor.getId());
            ArrayList<LinkedHashMap> product_list = (ArrayList) item.get("product_list");
            List pls = new ArrayList();
            ProductList pl = null;
            for (LinkedHashMap productMap : product_list) {
                pl = new ProductList();
                pl.setFid(floor.getId());
                try {
                    BeanUtils.copyProperties(pl, productMap);
                }catch (Exception e){
                    throw  e;
                }
                pls.add(pl);
            }
            productListService.saveBatch(pls);
        }

    }




    @Test
    public void getCategories() throws InvocationTargetException, IllegalAccessException {
        Map map = restTemplate.getForObject(api+"/categories", Map.class);

        ArrayList<LinkedHashMap> obj = (ArrayList) map.get("message");
        for (LinkedHashMap item : obj) {
            Categories categories = new Categories();
            BeanUtils.copyProperties(categories,item);
            categoriesService.save(categories);
            if(item.get("children")!=null){
                getCategoriesChildren((List) item.get("children"));
            }
        }

    }

    private void getCategoriesChildren(List<LinkedHashMap> children) throws InvocationTargetException, IllegalAccessException {
        for (LinkedHashMap child : children) {
            Categories categories = new Categories();
            BeanUtils.copyProperties(categories,child);
            categoriesService.save(categories);
            if(child.get("children")!=null){
                getCategoriesChildren((List) child.get("children"));
            }
        }

    }



    @Test
    public void getGoods() throws InvocationTargetException, IllegalAccessException {
        for (int i = 1; i <= 576; i++) {
            Map map = restTemplate.getForObject(api+"/goods/search?pagesize=100&&pagenum="+i, Map.class);

            ArrayList<LinkedHashMap> goodses = (ArrayList) ((LinkedHashMap) map.get("message")).get("goods");

            List list = new ArrayList();
            for (LinkedHashMap goodMap : goodses) {
                Goods goods = new Goods();
                BeanUtils.copyProperties(goods,goodMap);
                list.add(goods);
            }
            goodsService.saveBatch(list);
        }


    }


    @Test
    public void getGoodsDetail() throws InvocationTargetException, IllegalAccessException {
        int end = 575;
//        int end = 1;
        for (int i = 0; i < end; i++) {
            QueryWrapper queryC = new QueryWrapper();
            queryC.ge("goods_id",i*100+1);
            queryC.le("goods_id",(i+1)*100);

            List<Goods> goodsList = goodsService.list(queryC);
            List list = new ArrayList();
            for (Goods goods : goodsList) {
                Map map = restTemplate.getForObject(api+"/goods/detail?goods_id="+goods.getGoods_id(), Map.class);
                LinkedHashMap goodsDetailMap = (LinkedHashMap) map.get("message");

                String goods_introduce = (String) goodsDetailMap.get("goods_introduce");
                int hot_mumber = (int) goodsDetailMap.get("hot_mumber");
                boolean is_promote = (boolean) goodsDetailMap.get("is_promote");
                String goods_cat = (String) goodsDetailMap.get("goods_cat");


                goods.setGoods_introduce(goods_introduce);
                goods.setHot_mumber(hot_mumber);
                goods.setIs_promote(is_promote);
                goods.setGoods_cat(goods_cat);
                list.add(goods);

                ArrayList<LinkedHashMap> picsList = (ArrayList) goodsDetailMap.get("pics");
                ArrayList goodsPicsList = new ArrayList();
                GoodsPic goodsPic;
                for (LinkedHashMap picMap : picsList) {
                    goodsPic = new GoodsPic();
                    goodsPic.setGoods_id(goods.getGoods_id());
                    BeanUtils.copyProperties(goodsPic,picMap);
                    goodsPicsList.add(goodsPic);
                }
                goodsPicService.saveBatch(goodsPicsList);//保存商品图片信息


                ArrayList<LinkedHashMap> attrsMapList = (ArrayList) goodsDetailMap.get("attrs");
                ArrayList attrsList = new ArrayList();
                GoodsAttrs goodsAttrs;
                for (LinkedHashMap atrrsMap : attrsMapList) {
                    goodsAttrs = new GoodsAttrs();
                    goodsAttrs.setGoods_id(goods.getGoods_id());
                    BeanUtils.copyProperties(goodsAttrs,atrrsMap);
                    attrsList.add(goodsAttrs);
                }
                goodsAttrsService.saveBatch(attrsList);//保存商品属性信息

            }
            //批量更新
            goodsService.updateBatchById(list);
            System.out.println("更新商品共575页,当前页号:"+i);
        }


    }


}
