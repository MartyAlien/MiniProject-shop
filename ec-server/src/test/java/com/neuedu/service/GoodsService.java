package com.neuedu.service;

import com.neuedu.ec.ServerApp;
import com.neuedu.ec.entity.Goods;
import com.neuedu.ec.service.IGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ServerApp.class)
public class GoodsService {
    @Autowired
    IGoodsService goodsService;

    @Test
    public void test(){

        List<Goods> list = goodsService.list();
        System.out.println(list);
        for (Goods goods : list) {
            System.out.println(goods);
        }
    }
}
