package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  ExceptionEnum { //
    PRICE_NOT_NULL(400,"价格不能为空");
    //等价于 public static final PRICE_NOT_NULL=new (400,"价格不能为空")
    ;
    private int code;
    private String msg;
}
