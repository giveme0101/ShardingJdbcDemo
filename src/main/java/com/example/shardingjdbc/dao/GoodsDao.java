package com.example.shardingjdbc.dao;

import com.example.shardingjdbc.entity.Goods;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name GoodsDao
 * @Date 2019/02/21 11:57
 */
public interface GoodsDao {

    Goods getById(Long id);

    List<Goods> findAllGoods();

    boolean addGoods(Goods goods);

    boolean delGoods(Goods goods);
}
