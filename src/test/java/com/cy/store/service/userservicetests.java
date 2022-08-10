package com.cy.store.service;


import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userservicetests {
    @Autowired
    private IUserService userService;

    @Test
    public void f()
    {
        try {
            User user = new User();

            user.setUsername("sty111");
            user.setPassword("123");
            userService.register(user);
            System.out.println("register ok");
        } catch (ServiceException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }


    }
}
