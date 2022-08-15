package com.cy.store.controller;


import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.util.jsonresult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class usercontroller extends com.cy.store.controller.basecontroller {

    @Autowired
    private IUserService userService;

    @RequestMapping("/reg")
    public jsonresult<Void> register(User user)
    {


            userService.register(user);
        return  new com.cy.store.util.jsonresult<Void>(ok);

    }

    @RequestMapping("/login")
    public jsonresult<com.cy.store.entity.User> login(String username, String password, HttpSession session)

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
    public jsonresult<Void> changePassword(String oldPassword, String newPassword, HttpSession session)
    {
        String username = getnamesession(session);
        Integer uid =getuidsession(session);
        System.out.println(username);
        System.out.println(uid);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return  new com.cy.store.util.jsonresult<Void>(ok,"改密码成功");



    }
    @RequestMapping("/getbyuid")
    public jsonresult<com.cy.store.entity.User> getUserByUId(HttpSession ssession)
    {
        com.cy.store.entity.User getbyuid = userService.getbyuid(getuidsession(ssession));
        return  new com.cy.store.util.jsonresult<com.cy.store.entity.User>(getbyuid,200);

    }


    @RequestMapping("/changeinfo")
    public jsonresult<Void> changeInfo(HttpSession session , com.cy.store.entity.User user)

    {
        Integer uid = getuidsession(session);
        String username=getnamesession(session);
        userService.changeinfo(user,uid,username);
        System.out.println("修改信息成功");
        return  new com.cy.store.util.jsonresult<Void>(ok);


    }
    //头像大小10M
    public  static  final  Integer AVATAR_SIZE=10*1024*1024;
    // 头像类型
    public  static  final List<String>  list=new ArrayList<>();
    static {
        list.add("image/jpeg");
        list.add("image/png");
        list.add("image/bmp");
        list.add("image/gif");

    }

    @RequestMapping("/changeavatar")
    public  jsonresult<String> changeavatar(MultipartFile file,HttpSession session) {
        if (file.isEmpty())
            throw new FileEmptyException("文件为空");
        if (file.getSize() > AVATAR_SIZE)
            throw new FileSizeException("文件大小超出限制");
        String contentType = file.getContentType();
        if (!list.contains(contentType)) {
            System.out.println(contentType);
            throw new FileTypeException("文件类型不匹配");
        }
        String realPath = session.getServletContext().getRealPath("upload");
//String realPath="F:/ssm demo/store/src/main/resources/static/upload/";
        File dir = new File(realPath);
        if (!dir.exists())
            dir.mkdirs();
        String originalFilename = file.getOriginalFilename();

        int i = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(i);
        String filename= UUID.randomUUID().toString().toUpperCase()+substring;

        File localFile= new File(dir, filename);
        try {
            file.transferTo(localFile);
            Integer uid = getuidsession(session);
            String username=getnamesession(session);
            String avatar="/upload/"+filename;
//            String avatar=localFile.getAbsolutePath();
            System.out.println("/upload/"+filename);
            System.out.println(localFile.getAbsolutePath());
            userService.changeAvatar(uid,username,avatar);
            //头像在电脑上的位置

            return  new jsonresult<>(avatar,ok);
        }

        catch (FileStateException  e)
        {
            throw  new FileStateException("文件状态异常");
        }

        catch (IOException e) {
           throw  new FileUploadIOException("文件读写异常");
        }


    }

//        String parent =
//                session.getServletContext().getRealPath("/upload");
//        System.out.println(parent);//调试用
//
//        //File对象指向这个路径,通过判断File是否存在得到该路径是否存在
//        File dir = new File(parent);
//        if (!dir.exists()) {//检测目录是否存在
//            dir.mkdirs();//创建当前目录
//        }
//
//        //获取这个文件名称(文件名+后缀,如avatar01.png,不包含父目录结构)用UUID
//        // 工具生成一个新的字符串作为文件名(好处:避免了因文件名重复发生的覆盖)
//        String originalFilename = file.getOriginalFilename();
//        System.out.println("OriginalFilename=" + originalFilename);
//        int index = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(index);
//        //filename形如SAFS1-56JHIOHI-HIUGHUI-5565TYRF.png
//        String filename =
//                UUID.randomUUID().toString().toUpperCase() + suffix;
//
//        //在dir目录下创建filename文件(此时是空文件)
//        File dest = new File(dir, filename);
//
//        //java可以把一个文件的数据直接写到同类型的文件中,这里将参数file中的数据写入到空文件dest中
//        try {
//            file.transferTo(dest);//transferTo是一个封装的方法,用来将file文件中的数据写入到dest文件
//
//            /**
//             * 先捕获FileStateException再捕获IOException是
//             * 因为后者包含前者,如果先捕获IOException那么
//             * FileStateException就永远不可能会被捕获
//             */
//        } catch (FileStateException e) {
//            throw new FileStateException("文件状态异常");
//        } catch (IOException e) {
//            //这里不用打印e,而是用自己写的FileUploadIOException类并
//            // 抛出文件读写异常
//            throw new FileUploadIOException("文件读写异常");
//        }
//
//        Integer uid = getuidsession(session);
//        String username = getnamesession(session);
//        String avatar = "/upload/" + filename;
//        System.out.println(avatar);
//        userService.changeAvatar(uid, username, avatar);
//        //返回用户头像的路径给前端页面,将来用于头像展示使用
//        return new jsonresult<>(avatar, ok);
//


}
