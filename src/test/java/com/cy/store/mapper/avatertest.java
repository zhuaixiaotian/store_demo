package com.cy.store.mapper;

import com.cy.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class avatertest {
    @Autowired
    private usermapper usermapper;
    @Test
    public  void f()
    {

        Integer integer = usermapper.updateAvatarbyuid(9,"sty",new Date(),"wodosa");

        System.out.println(integer);


    }
}
