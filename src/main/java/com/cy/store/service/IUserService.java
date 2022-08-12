package com.cy.store.service;


import com.cy.store.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService {
    void  register(User user);
    User  login(String username,String passwd);
    void changePassword(Integer uid,String username ,String oldpassword,String newPassword);
    User getbyuid (Integer uid);
    void changeinfo(User user,Integer uid ,String username);

}
