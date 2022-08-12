package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.jsonresult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class usercontroller extends basecontroller {

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    public jsonresult<Void> register(User user)
    {


            userService.register(user);
        return  new jsonresult<Void>(ok);

    }

    @RequestMapping("/login")
    public jsonresult<User> login(String username, String password, HttpSession session)

    {
        System.out.println(username);
        User login = userService.login(username, password);
        System.out.println("登陆成功");
        jsonresult<User> userjsonresult = new jsonresult<>();
        userjsonresult.setData(login);
        userjsonresult.setState(ok);
        userjsonresult.setMsg("登陆成功");
        session.setAttribute("uid",login.getUid());
        session.setAttribute("username",login.getUsername());
        return  userjsonresult;
    }


    @RequestMapping("/change_password")
    public  jsonresult<Void>  changePassword(String oldPassword,String newPassword,HttpSession session)
    {
        String username = getnamesession(session);
        Integer uid =getuidsession(session);
        System.out.println(username);
        System.out.println(uid);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return  new jsonresult<Void>(ok,"改密码成功");



    }
    @RequestMapping("/getbyuid")
    public  jsonresult<User> getUserByUId(HttpSession ssession)
    {
        User getbyuid = userService.getbyuid(getuidsession(ssession));
        return  new jsonresult<User>(getbyuid,200);

    }


    @RequestMapping("/changeinfo")
    public  jsonresult<Void> changeInfo(HttpSession session ,User user)

    {
        Integer uid = getuidsession(session);
        String username=getnamesession(session);
        userService.changeinfo(user,uid,username);
        return  new jsonresult<Void>(ok);


    }


}
