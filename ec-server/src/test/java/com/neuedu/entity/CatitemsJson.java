package com.neuedu.entity;

import com.neuedu.ec.entity.Catitems;
import com.neuedu.ec.entity.Swiperdata;
import lombok.Data;

import java.util.List;

@Data
public class CatitemsJson {
    private String msg;
    private int status;

    private List<Catitems> message;
}
