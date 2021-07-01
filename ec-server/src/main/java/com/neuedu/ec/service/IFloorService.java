package com.neuedu.ec.service;

import com.neuedu.ec.entity.Floor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neuedu.ec.vo.FloorVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 楼层 服务类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
public interface IFloorService extends IService<Floor> {
    List<FloorVO>   floordata() throws InvocationTargetException, IllegalAccessException;
}
