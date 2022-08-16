package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.jsonresult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class basecontroller {

    public  static  final  Integer ok=200;
    @ExceptionHandler ({ServiceException.class,FileUploadException.class})

    //捕获service层跑上来的异常和 头像传输到controller层的异常
    public jsonresult<Void> handleException(Throwable e)
    {
        jsonresult<Void>  result =new jsonresult<>();
        if( e instanceof UsernameDuplicatedException)
        {

            result.setState(4000);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        }

        else  if (e instanceof InsertException)
        {
            System.out.println(e.getMessage());
            result.setState(4000);  //注册失败
            result.setMsg(e.getMessage());

        }
        else if (e instanceof PasswordNotMatchException)
        {
            System.out.println(e.getMessage());
            result.setState(4001); //密码错误
            result.setMsg(e.getMessage());
        }

        else  if (e instanceof UsernameNotFoundException){

            System.out.println(e.getMessage());
            result.setState(4002);//数据不存在
            result.setMsg(e.getMessage());

        }


        else if (e instanceof UpdateException)
        {
            System.out.println(e.getMessage());
            result.setState(4003);  //更新异常
            result.setMsg(e.getMessage());
        }


        else if (e instanceof FileEmptyException) {
            result.setState(5000);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        } else if (e instanceof FileSizeException) {
            result.setState(5001);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        } else if (e instanceof FileTypeException) {
            result.setState(5002);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        } else if (e instanceof FileStateException) {
            result.setState(5003);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        } else if (e instanceof FileUploadIOException) {
            result.setState(5004);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());
        }
        else  if (e instanceof AddressCountLimitException )
        {
            result.setState(6000);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());

        }

        else  if (e instanceof AddressNotFoundException )
        {
            result.setState(6001);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());

        }
        else  if (e instanceof AccessDeniedException )
        {
            result.setState(6002);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());

        }
        else  if (e instanceof DeleteException )
        {
            result.setState(6003);
            System.out.println(e.getMessage());
            result.setMsg(e.getMessage());

        }
        else if (e instanceof ProductNotFoundException)

            {
                result.setState(7000);
                System.out.println(e.getMessage());
                result.setMsg(e.getMessage());

            }

        else if (e instanceof CartNotFoundException)

        {
            result.setState(7001);
            System.out.println(e.getMessage());
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
