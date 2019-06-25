package com.springbootpractice.demomiaosha.web;

import com.springbootpractice.demomiaosha.bean.PurchaseRequestParam;
import com.springbootpractice.demomiaosha.bean.ResponseBean;
import com.springbootpractice.demomiaosha.dao.model.ProductEntity;
import com.springbootpractice.demomiaosha.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description Rest接口
 * @date 2019年06月25日 17:25
 * @Copyright (c) carterbrother
 */
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(path = "/purchase")
    public ResponseBean purchase(@RequestBody PurchaseRequestParam param){
        boolean success = purchaseService.purchaseNormal(param.getUserId(),param.getProductId(),param.getQty());

        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(success);
        responseBean.setMessage(success?"秒杀成功":"秒杀失败");

        responseBean.setData(System.currentTimeMillis());

        return responseBean;

    }

    @GetMapping(path = "/product/{productId}")
    public ProductEntity getProductById(@PathVariable("productId") Long productId){
        return  purchaseService.getProductById(productId);
    }



}
