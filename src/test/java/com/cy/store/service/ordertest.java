package com.cy.store.service;

import com.cy.store.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.pattern.PathPattern;

@SpringBootTest
public class ordertest {
    @Autowired
    private  IOderService orderService;
    @Test
    public  void  f()
    {
        Integer []cids={24,25};
        Order sty = orderService.create(29, 9, cids, "sty");
        System.out.println(sty);


    }

}
