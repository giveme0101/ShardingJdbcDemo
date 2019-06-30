package com.example.shardingjdbc.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name Goods
 * @Date 2019/02/21 11:55
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {

    private Long goodsId;
    private String goodsName;
    private Long goodsType;

}
