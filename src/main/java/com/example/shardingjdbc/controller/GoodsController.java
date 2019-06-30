package com.example.shardingjdbc.controller;

import com.example.shardingjdbc.util.R;
import com.example.shardingjdbc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name UserController
 * @Date 2019/02/21 12:11
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ResponseBody
    @GetMapping(value = "{id}")
    public Object id(@PathVariable(name = "id") Long id){

        return R.ok(goodsService.getGoods(id));
    }

    @ResponseBody
    @GetMapping(value = "list")
    public Object all(){

        return R.ok(goodsService.getGoodsList());
    }

    @ResponseBody
    @DeleteMapping(value = "{id}")
    public Object del(@PathVariable(name = "id") Long id){

        return R.ok(goodsService.removeGoods(id));
    }

    @ResponseBody
    @PostMapping(value = "/save/{name}/{type}")
    public Object del(@PathVariable(name = "name") String name
            , @PathVariable(name = "type") Long type){

        return R.ok(goodsService.addGoods(name, type));
    }

}
