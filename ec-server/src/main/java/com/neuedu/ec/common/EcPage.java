package com.neuedu.ec.common;

import com.neuedu.ec.entity.Goods;
import lombok.Data;

import java.util.List;

@Data
public class EcPage {
    private Integer total;
    private Integer pagenum;
    private List<Goods> goods;
}
