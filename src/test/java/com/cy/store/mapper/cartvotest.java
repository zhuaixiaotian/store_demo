package com.cy.store.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class cartvotest {
    @Autowired
    private  cartmapper cartmapper;
    @Test
    public  void findbyuid()
    {
        System.out.println(cartmapper.findbyuid(9).size());

    }
}
