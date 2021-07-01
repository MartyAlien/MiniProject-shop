package com.neuedu.ec.mapper;

import com.neuedu.ec.entity.Floor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 楼层 Mapper 接口
 * </p>
 *
 * @author 张金山
 * @since 2021-04-12
 */
public interface FloorMapper extends BaseMapper<Floor> {

    List<Floor> floordata();
}
