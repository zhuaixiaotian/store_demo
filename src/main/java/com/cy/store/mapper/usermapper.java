package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


public interface usermapper {
    User findByUsername (@Param("username") String username);
    Integer insert(User user);
    User findByUid (@Param("uid") Integer uid);
    Integer updatePasswordbyUid (@Param("uid") Integer uid, @Param("password") String password, @Param("modifier") String modifier, @Param("modifiedDate") Date modifiedDate);
   void   updateinfobyuid(User user);

}
