package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.usermapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceimpl implements IUserService {
    @Autowired
    private usermapper usermapper;



    // 加密算法
    public  static  String getmd5passwd(String passwd,String salt)
    {
        for (int i = 0; i < 3; i++) {
            String s = DigestUtils.md5DigestAsHex((salt + passwd + salt).getBytes());
            passwd=s.toUpperCase();

        }
        return  passwd;

    }




    @Override
    public void register(User user) {
        String username=user.getUsername();
        User byUsername = usermapper.findByUsername(user.getUsername());
        if(byUsername!=null)
        {
            throw  new UsernameDuplicatedException("用户名已被注册");//4000
        }
        /*
        md5加密密码  (串+密码+串)====md5
        串===盐值
         */
        //随机生成盐值字符CHUAN
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password =getmd5passwd(user.getPassword(),salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setModifiedUser(username);
        user.setCreatedUser(username);
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);


        Integer insert = usermapper.insert(user);
        if (insert!=1){
            throw  new InsertException("注册产生错误"); //5000
        }


    }




    @Override
    public User   login(String username,String passwd) {
        User ByUsername = usermapper.findByUsername(username);
        if (ByUsername==null||ByUsername.getIsDelete()==1)
        {
            throw new UsernameNotFoundException("用户数据不存在");//5002
        }
        String salt=ByUsername.getSalt();
        String password = ByUsername.getPassword();
        String newPassword = getmd5passwd(passwd, salt);
        if (!newPassword.equals(password)){
            throw  new PasswordNotMatchException("用户密码错误"); //5001
        }

        /*
        减少前后端传输数据量
        只传输关键数据

         */

        User user1 = new User();
        user1.setUsername(username);
        user1.setUid(ByUsername.getUid());
        user1.setAvatar(ByUsername.getAvatar());

        return  user1;



    }



    @Override
    public void changePassword(Integer uid, String username, String oldpassword, String newPassword) {
        User result = usermapper.findByUid(uid);
        if (result==null||result.getIsDelete()==1)

        {
            throw new UsernameNotFoundException("用户数据不存在");//5002
        }


        String salt=result.getSalt();
        String password = result.getPassword();
        String md5password = getmd5passwd(oldpassword, salt);
        if (!md5password.equals(password)){
            throw  new PasswordNotMatchException("用户密码错误"); //5001
        }

     String   newmd5Password=getmd5passwd(newPassword, result.getSalt());
        Integer integer = usermapper.updatePasswordbyUid(uid, newmd5Password, username, new Date());
        if (integer!=1){

            throw  new UpdateException("更新数据错误");

        }

    }
}
