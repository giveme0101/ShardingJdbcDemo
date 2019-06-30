package com.example.shardingjdbc.service;

import com.example.shardingjdbc.dao.GoodsDao;
import com.example.shardingjdbc.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserService
 * @Date 2019/02/21 12:07
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RedisService redisService;

    public Goods getGoods(Long id){

        return goodsDao.getById(id);
    }

    public List<Goods> getGoodsList(){

        return goodsDao.findAllGoods();
    }

    public boolean removeGoods(Long id){

        return goodsDao.delGoods(new Goods(){{
            setGoodsId(id);
        }});
    }

    public boolean addGoods(String name, Long type){

        return goodsDao.addGoods(new Goods(){{
            setGoodsId(redisService.getUUID());
            setGoodsName(name);
            setGoodsType(type);
        }});
    }

}
