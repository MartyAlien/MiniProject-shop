package com.neuedu.ec.vo;

import com.neuedu.ec.entity.Floor;
import com.neuedu.ec.entity.ProductList;
import lombok.Data;

import java.util.List;

@Data
public class FloorVO {


    private FloorCopy floor_title;
    private List<ProductList> product_list;

}
