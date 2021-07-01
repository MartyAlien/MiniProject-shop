package com.neuedu.ec.service.impl;

import com.neuedu.ec.entity.Floor;
import com.neuedu.ec.mapper.FloorMapper;
import com.neuedu.ec.service.IFloorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuedu.ec.vo.FloorCopy;
import com.neuedu.ec.vo.FloorVO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 楼层 服务实现类
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements IFloorService {

    @Override
    public List<FloorVO> floordata() throws InvocationTargetException, IllegalAccessException {
        List<FloorVO> list = new ArrayList();
        List<Floor> floors = getBaseMapper().floordata();
        FloorVO vo = null;
        FloorCopy fc = null;
        for (Floor floor : floors) {
            vo = new FloorVO();
            fc = new FloorCopy();
            BeanUtils.copyProperties(fc,floor);
            vo.setFloor_title(fc);
            vo.setProduct_list(floor.getProduct_list());

            list.add(vo);
        }
        return list;
    }
}
