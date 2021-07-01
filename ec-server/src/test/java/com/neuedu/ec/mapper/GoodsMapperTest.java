package com.neuedu.ec.mapper;

import com.neuedu.ec.ServerApp;
import com.neuedu.ec.entity.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ServerApp.class)
class GoodsMapperTest {

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void detail() {

        Goods goods = goodsMapper.detail(1000);
        System.out.println(goods);
        System.out.println(goods);
        System.out.println(goods);
    }
}