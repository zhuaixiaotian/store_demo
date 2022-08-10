package com.cy.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class updatepasswd {
    @Autowired
    private  IUserService userService;

    @Test
    public  void f()
    {
        userService.changePassword(9,"sty","123","123");


    }
    @Test
    public  void t()
    {


    }


}
