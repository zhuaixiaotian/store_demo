package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface addressmapper {
    Integer insert (Address address);
    Integer countbyuid(@Param("uid") Integer uid);
    List<Address> selectbyuid(@Param("uid") Integer uid);
    Address findbyaid(@Param("aid") Integer aid);
    Integer updatenondefault(@Param("uid") Integer uid);
    Integer updatedefault (@Param("aid") Integer aid, @Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);
    Integer deletebyaid(@Param("aid") Integer aid);//根据aid删除地址
    Address findlastmodified (@Param("uid") Integer uid);










}
