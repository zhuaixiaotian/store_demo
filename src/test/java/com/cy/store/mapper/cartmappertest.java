package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class cartmappertest {
    @Autowired
    private cartmapper cartmapper;
    @Test
    public  void insert()
    {

        Cart cart = new Cart();
        cart.setUid(9);
        cart.setPid(10000001);
        cart.setNum(2);
        cart.setPrice(100000L);
        cartmapper.insert(cart);

    }
    @Test
    public  void update()
    {
        cartmapper.updatenumbycid(1,4,"sty",new Date());

    }
    @Test
    public  void find()
    {
        System.out.println(cartmapper.findById(9, 10000001));


    }



}
