package com.springbootpractice.demomiaosha.service;

import com.springbootpractice.demomiaosha.dao.mapper.ProductEntityMapper;
import com.springbootpractice.demomiaosha.dao.mapper.PurchaseRecordEntityMapper;
import com.springbootpractice.demomiaosha.dao.model.ProductEntity;
import com.springbootpractice.demomiaosha.dao.model.PurchaseRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年06月25日 17:12
 * @Copyright (c) carterbrother
 */
@Service
public class PurchaseService {

    @Autowired
    private ProductEntityMapper productEntityMapper;
    @Autowired
    private PurchaseRecordEntityMapper purchaseRecordEntityMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean purchaseNormal(Long userId,Long productId,Integer qty){

        Assert.isTrue(Objects.nonNull(userId) && userId>0 , "用户id必须大于0");
        Assert.isTrue(Objects.nonNull(productId) && productId>0 , "商品id必须大于0");
        Assert.isTrue(Objects.nonNull(qty) && qty>0 , "购买数量qty必须大于0");

        ProductEntity productEntity =  productEntityMapper.selectByPrimaryKey(productId);

        Assert.notNull(productEntity,String.format("产品%s不存在",productId));
        Assert.notNull(productEntity.getStoreQty(),String.format("产品%s的库存没有设置",productId));
        if (productEntity.getStoreQty() < qty){
            return false;
        }

        //扣减库存
        ProductEntity updateParam = new ProductEntity();
        updateParam.setId(productId);
        updateParam.setStoreQty(productEntity.getStoreQty()-1);
        productEntityMapper.updateByPrimaryKeySelective(updateParam);

        //插入购买记录
        PurchaseRecordEntity purchaseRecordEntity = new PurchaseRecordEntity();
        final long currentTimeMillis = System.currentTimeMillis();
        purchaseRecordEntity.setBuyTime(new Date(currentTimeMillis));
        purchaseRecordEntity.setMemo(String.format("购买:%s", productEntity.getProductName()));
        purchaseRecordEntity.setPrice(productEntity.getProductPrice());
        purchaseRecordEntity.setProductId(productId);
        purchaseRecordEntity.setQty(qty);
        purchaseRecordEntity.setTotalPrice(productEntity.getProductPrice().multiply(new BigDecimal(qty)));
        purchaseRecordEntity.setUserId(userId);

        purchaseRecordEntityMapper.insertSelective(purchaseRecordEntity);

        return true;

    }

    public ProductEntity getProductById(Long productId) {

        Assert.isTrue(Objects.nonNull(productId)&& productId>0 ,"产品ID必须大于0");
        return productEntityMapper.selectByPrimaryKey(productId);
    }
}
