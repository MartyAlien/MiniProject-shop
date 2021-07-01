package com.neuedu.entity;

import com.neuedu.ec.entity.Swiperdata;
import lombok.Data;

import java.util.List;

@Data
public class SwiperdataJson {
    private String msg;
    private int status;

    private List<Swiperdata> message;
}
