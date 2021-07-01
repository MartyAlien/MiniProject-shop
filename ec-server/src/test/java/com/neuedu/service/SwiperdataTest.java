package com.neuedu.service;

import com.neuedu.ec.ServerApp;
import com.neuedu.ec.entity.Swiperdata;
import com.neuedu.ec.service.ISwiperdataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = ServerApp.class)
public class SwiperdataTest {

    @Resource
    ISwiperdataService swiperdataService;


    @Test
    public void test() {
        List<Swiperdata> list = swiperdataService.list();
        System.out.println("查询条数:" + list.size());
        for (Swiperdata swiperdata : list) {
            System.out.println(swiperdata);
        }
    }

}
