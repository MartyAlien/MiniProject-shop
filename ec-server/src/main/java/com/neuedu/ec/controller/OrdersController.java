package com.neuedu.ec.controller;


import com.neuedu.ec.common.CommonResult;
import com.neuedu.ec.entity.Orders;
import com.neuedu.ec.service.IOrdersService;
import com.neuedu.ec.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/auth/order")
public class OrdersController {
    @Autowired
    IOrdersService ordersService;

    /**
     * 创建订单
     * @param token 调用wxlogin接口登录成功后服务器返回的令牌
     * @param orderVo
     * @return
     */
    @RequestMapping("/create")
    public CommonResult<Orders> create(@RequestHeader("token") String token , @RequestBody OrderVo orderVo) throws InvocationTargetException, IllegalAccessException {
        System.out.println("token="+token);
        System.out.println("OrderVo="+orderVo);
        //Orders orders = ordersService.create(token,orderVo);
        return CommonResult.success("ok");
    }}
