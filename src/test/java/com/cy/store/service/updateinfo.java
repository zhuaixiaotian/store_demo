package com.cy.store.service;

import com.alibaba.druid.filter.AutoLoad;
import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class updateinfo {
    @Autowired
    private  IUserService userService;
            @Test

    public void f()
            {
                User getbyuid = userService.getbyuid(9);
                System.out.println(getbyuid);

            }

    @Test

    public void q()
    {
        User user = new User();
        user.setPhone("1309012");
        user.setEmail("20202020");
        user.setGender(1);
        userService.changeinfo(user,9,"sty");

    }

}
