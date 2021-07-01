package com.neuedu.ec.common;

import lombok.Getter;

@Getter
public class CommonResult<T>  {
    private Meta meta;

    private T message;

    public CommonResult(Meta meta) {
        this(meta,null);
    }

    public CommonResult(Meta meta, T message) {
        this.meta = meta;
        this.message = message;
    }

    public CommonResult(ResultCode resultCode) {
        this(resultCode,null);
    }

    public CommonResult(ResultCode resultCode,T message) {
        this(new Meta(resultCode.getCode(),resultCode.getMessage()),message);
    }

    public static CommonResult nohandler() {
        return new CommonResult(ResultCode.NOHANDLER);
    }
    public static CommonResult success(Object obj) {
        return new CommonResult(ResultCode.SUCCESS ,obj);
    }
    public static CommonResult failed() {
        return new CommonResult(ResultCode.FAILED);
    }
    public static CommonResult failed(String msg) {
        return new CommonResult(new Meta(ResultCode.FAILED.getCode(),msg));
    }
    public static CommonResult notoken() {
        return new CommonResult(ResultCode.NOTOKEN);
    }
    public static CommonResult nopremiss() {
        return new CommonResult(ResultCode.NOPERMISS);
    }
}
