package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface cartmapper

{
    Cart findById(@Param("uid") Integer uid, @Param("pid") Integer pid);
    Integer updatenumbycid(@Param("cid") Integer cid, @Param("num") Integer num, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);
    Integer insert(Cart cart);
    List<CartVO> findbyuid(@Param("uid") Integer uid);
}
