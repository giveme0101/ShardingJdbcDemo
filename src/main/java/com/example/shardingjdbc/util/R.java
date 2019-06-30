package com.example.shardingjdbc.util;

import lombok.*;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name R
 * @Date 2019/02/21 12:13
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class R {

    private String code;
    private String msg;
    private Object body;

    public R(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static R ok(Object body){
        R r = new R("200", "", body);
        return r;
    }

    public static R err(String msg){
        R r = new R("500", msg, null);
        return r;
    }

}
