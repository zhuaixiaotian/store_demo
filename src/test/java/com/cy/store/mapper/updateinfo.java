package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.PanelUI;

@SpringBootTest
public class updateinfo {
    @Autowired
    private  usermapper usermapper;
    @Test
    public  void  f()
    {
        User user = new User();
        user.setUid(9);
        user.setGender(null);
        user.setPhone("1231231");
        user.setEmail("");
        usermapper.updateinfobyuid(user);


    }




}
