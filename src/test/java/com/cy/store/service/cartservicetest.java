package com.cy.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class cartservicetest {
    @Autowired
    private  ICartService icartService;

    @Test
    public  void insert()
    {
        icartService.addnewcart(9,10000001,10,"sty");


    }

}
