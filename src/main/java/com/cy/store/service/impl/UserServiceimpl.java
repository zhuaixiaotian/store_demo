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
    private com.cy.store.mapper.usermapper usermapper;



    // 加密算法
    public  static  String getmd5passwd(String passwd,String salt)
    {
        for (int i = 0; i < 3; i++) {
            String s = DigestUtils.md5DigestAsHex((salt + passwd + salt).getBytes());
            passwd=s.toUpperCase();

        }
        return  passwd;

    }


    //注册
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
        checkUser(ByUsername);
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
        checkUser(result);

        String salt=result.getSalt();
        String password = result.getPassword();
        String md5password = getmd5passwd(oldpassword, salt);
        if (!md5password.equals(password)){
            throw  new PasswordNotMatchException("用户密码错误"); //5001
        }

     String   newmd5Password=getmd5passwd(newPassword, result.getSalt());
        Integer integer = usermapper.updatePasswordbyUid(uid, newmd5Password, username, new Date());
        if (integer!=1){

            throw  new UpdateException("更新密码错误");

        }
        System.out.println("密码修改成功1");

    }

    public  void  checkUser(User user)
    {
        if (user==null||user.getIsDelete()==1)
        {
            throw  new UsernameNotFoundException("用户数据不存在");
        }


    }


    @Override
    public User getbyuid(Integer uid) {
        User byUid = usermapper.findByUid(uid);
      checkUser(byUid);
        User user = new User();
        user.setUsername(byUid.getUsername());
        user.setEmail(byUid.getEmail());
        user.setGender(byUid.getGender());
        user.setPhone(byUid.getPhone());
        System.out.println("查找成功");
        return  user;
    }

    @Override
    public void changeinfo(User user,Integer uid ,String username) {
        User result = usermapper.findByUid(uid);
       checkUser(result);
       user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer row = usermapper.updateinfobyuid(user);
        if (row!=1)
        {
            throw  new UpdateException("更新信息错误");

        }
        System.out.println("更新信息成功");



    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User user = usermapper.findByUid(uid);
        checkUser(user);
        Integer integer = usermapper.updateAvatarbyuid(uid, username, new Date(), avatar);
        if (integer!=1)
        {

            throw  new UpdateException("修改头像失败");
        }


    }
}
