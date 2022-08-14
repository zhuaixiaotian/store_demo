package com.cy.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class avater {

    @Autowired
    private  IUserService userService;

    @Test
    public  void f()
    {
        userService.changeAvatar(9,"sty","wdojsd.jpg");


    }
}
