package com.cy.store.service;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userlogintests {
    @Autowired
    private  IUserService userService;

    @Test
    public void f()
    {
        User zhuaixiaotian = userService.login("zhuaixiaotian", "123");
        System.out.println(zhuaixiaotian);


    }

}

