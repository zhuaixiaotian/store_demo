package com.cy.store.mapper;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class usermappertests {
    @Autowired
    private  usermapper usermapper;

    @Test
    public  void  f()
    {
        User user = new User();
        user.setUsername("sty");
        user.setPassword("123");
        Integer insert = usermapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public  void t()
    {
        User sty = usermapper.findByUsername("sty");
        System.out.println(sty);

    }

    @Test
    public  void q()
    {
        System.out.println(usermapper.findByUid(10));


    }






}
