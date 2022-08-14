package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface cartmapper {
    Integer insert (Cart cart); //插入购物车
    Integer updatenumbycid (@Param("cid") Integer cid, @Param("num") Integer num, @Param
            ("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    Cart  findById(@Param("uid") Integer uid, @Param("pid") Integer pid);
}
