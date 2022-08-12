package com.cy.store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class usercontroller extends com.cy.store.controller.basecontroller {

    @Autowired
    private com.cy.store.service.IUserService userService;

    @RequestMapping("/reg")
    public com.cy.store.util.jsonresult<Void> register(com.cy.store.entity.User user)
    {


            userService.register(user);
        return  new com.cy.store.util.jsonresult<Void>(ok);

    }

    @RequestMapping("/login")
    public com.cy.store.util.jsonresult<com.cy.store.entity.User> login(String username, String password, HttpSession session)

    {
        System.out.println(username);
        com.cy.store.entity.User login = userService.login(username, password);
        System.out.println("登陆成功");
        com.cy.store.util.jsonresult<com.cy.store.entity.User> userjsonresult = new com.cy.store.util.jsonresult<>();
        userjsonresult.setData(login);
        userjsonresult.setState(ok);
        userjsonresult.setMsg("登陆成功");
        session.setAttribute("uid",login.getUid());
        session.setAttribute("username",login.getUsername());
        return  userjsonresult;
    }


    @RequestMapping("/change_password")
    public com.cy.store.util.jsonresult<Void> changePassword(String oldPassword, String newPassword, HttpSession session)
    {
        String username = getnamesession(session);
        Integer uid =getuidsession(session);
        System.out.println(username);
        System.out.println(uid);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return  new com.cy.store.util.jsonresult<Void>(ok,"改密码成功");



    }
    @RequestMapping("/getbyuid")
    public com.cy.store.util.jsonresult<com.cy.store.entity.User> getUserByUId(HttpSession ssession)
    {
        com.cy.store.entity.User getbyuid = userService.getbyuid(getuidsession(ssession));
        return  new com.cy.store.util.jsonresult<com.cy.store.entity.User>(getbyuid,200);

    }


    @RequestMapping("/changeinfo")
    public com.cy.store.util.jsonresult<Void> changeInfo(HttpSession session , com.cy.store.entity.User user)

    {
        Integer uid = getuidsession(session);
        String username=getnamesession(session);
        userService.changeinfo(user,uid,username);
        return  new com.cy.store.util.jsonresult<Void>(ok);


    }


}
