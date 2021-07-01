package com.neuedu.entity;

import com.neuedu.ec.entity.Catitems;
import com.neuedu.ec.entity.Floor;
import lombok.Data;

import java.util.List;

@Data
public class FloordataJson {
    private String msg;
    private int status;

    private List<Floor> message;
}
