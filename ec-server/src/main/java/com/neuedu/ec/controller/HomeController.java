package com.neuedu.ec.controller;

import com.neuedu.ec.common.CommonResult;
import com.neuedu.ec.entity.Catitems;
import com.neuedu.ec.entity.Floor;
import com.neuedu.ec.entity.Swiperdata;
import com.neuedu.ec.service.ICatitemsService;
import com.neuedu.ec.service.IFloorService;
import com.neuedu.ec.service.ISwiperdataService;
import com.neuedu.ec.vo.FloorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 首页
 */
@RequestMapping("/home")
@RestController
public class HomeController {

    @Autowired
    ISwiperdataService swiperdataService;
    @Autowired
    ICatitemsService catitemsService;
    @Autowired
    IFloorService floorService;

    /**
     * 轮播图
     * @return
     */
    @RequestMapping("/swiperdata")
    public CommonResult<List<Swiperdata>> swiperdata(){
        List<Swiperdata> list = swiperdataService.list();
        return CommonResult.success(list);
    }
    /**
     * 导航
     * @return
     */
    @RequestMapping("/catitems")
    public CommonResult<List<Catitems>> catitems(){
        List<Catitems> list = catitemsService.list();
        return CommonResult.success(list);
    }
    /**
     * 楼层
     * @return
     */
    @RequestMapping("/floordata")
    public CommonResult<List<FloorVO>> floordata() throws InvocationTargetException, IllegalAccessException {
        List<FloorVO> list = floorService.floordata();
        return CommonResult.success(list);
    }
}
