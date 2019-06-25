package com.springbootpractice.demomiaosha.dao.mapper;

import com.springbootpractice.demomiaosha.dao.model.ProductEntity;
import com.springbootpractice.demomiaosha.dao.model.ProductEntityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEntityMapper {
    long countByExample(ProductEntityCriteria example);

    int deleteByExample(ProductEntityCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    List<ProductEntity> selectByExample(ProductEntityCriteria example);

    ProductEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductEntity record, @Param("example") ProductEntityCriteria example);

    int updateByExample(@Param("record") ProductEntity record, @Param("example") ProductEntityCriteria example);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);
}