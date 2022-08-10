package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.jsonresult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class basecontroller {

    public  static  final  Integer ok=200;
    @ExceptionHandler (ServiceException.class)
    public jsonresult<Void> handleException(Throwable e)
    {
        jsonresult<Void>  result =new jsonresult<>();
        if( e instanceof UsernameDuplicatedException)
        {
            System.out.println(e.getMessage());
            result.setState(4000);
            result.setMsg(e.getMessage());
        }
        else  if (e instanceof InsertException)
        {
            System.out.println(e.getMessage());
            result.setState(5000);
            result.setMsg(e.getMessage());

        }


        else  if (e instanceof UsernameNotFoundException){

            System.out.println(e.getMessage());
            result.setState(5002);//数据不存在
            result.setMsg(e.getMessage());

        }

        else if (e instanceof PasswordNotMatchException)
        {
            System.out.println(e.getMessage());
            result.setState(5001); //密码错误
            result.setMsg(e.getMessage());
        }

        else if (e instanceof UpdateException)
        {
            System.out.println(e.getMessage());
            result.setState(5003);  //更新异常
            result.setMsg(e.getMessage());
        }





        return  result;
    }

    public  final  Integer getuidsession(HttpSession session)

    {
        return  Integer.parseInt(session.getAttribute("uid").toString());


    }

    public  final  String getnamesession(HttpSession session)
    {

        return  session.getAttribute("username").toString();
    }



}
