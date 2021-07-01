package com.neuedu.ec.service.impl;

import com.neuedu.ec.entity.Orders;
import com.neuedu.ec.mapper.OrdersMapper;
import com.neuedu.ec.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
