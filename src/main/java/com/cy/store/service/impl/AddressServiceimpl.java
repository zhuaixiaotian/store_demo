package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.addressmapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceimpl implements IAddressService {
    @Autowired
    private com.cy.store.mapper.addressmapper addressmapper;
    @Value("${user.address.maxcount}")
    private  Integer maxcount;
    @Override
    public void addnewaddress(Integer uid, String username, Address address) {
        Integer countbyuid = addressmapper.countbyuid(uid);
        if (countbyuid>=maxcount)
            throw  new AddressCountLimitException("地址数量超过上限");
        System.out.println(address);
        address.setUid(uid);
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        Integer defaultcount =countbyuid==0?1:0;
        address.setIsDefault(defaultcount);
        Integer insert = addressmapper.insert(address);
        if (insert!=1)
        {
            throw  new InsertException("插入收货地址出错");

        }


    }

    @Override
    public List<Address> getbyuid(Integer uid) {
        List<Address> list= addressmapper.selectbyuid(uid);

        for ( Address address :list) {
            //address.setAid(null);
            address.setUid(null);
            address.setIsDefault(null);;
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return  list;



    }
    public  void checkaddress(Address address,Integer uid)
    {
        if (address==null)
            throw  new AddressNotFoundException("收货地址不存在");
        if(!address.getUid().equals(uid))
            throw  new AccessDeniedException("非法访问其他用户数据");

    }




    @Override
    public void setDefault(Integer aid, Integer uid, String username) {

        Address findbyaid = addressmapper.findbyaid(aid);
        checkaddress(findbyaid,uid);
        Integer updatenondefault = addressmapper.updatenondefault(uid);
        if (updatenondefault<1)
            throw  new UpdateException("更新默认地址异常");
        Integer updatedefault = addressmapper.updatedefault(aid, username, new Date());
        if (updatedefault!=1)
            throw  new UpdateException("更新默认地址异常");
    }

    @Override
    public void deletebyaid(Integer aid, Integer uid, String username) {

        Address address = addressmapper.findbyaid(aid);
        checkaddress(address,uid);

        Integer deletebyaid = addressmapper.deletebyaid(aid);
        if (deletebyaid!=1)
         throw  new DeleteException("删除地址出现异常");


        Integer countbyuid = addressmapper.countbyuid(uid);
        Integer  isDefault=address.getIsDefault();
        if (countbyuid==0||isDefault==0)
            return;//没有地址或删除的不是默认地址 直接返回
        //删除的是默认地址
        Address findlastmodified = addressmapper.findlastmodified(uid);
        Integer updatedefault = addressmapper.updatedefault(findlastmodified.getAid(), username, new Date());
        if (updatedefault!=1)
            throw  new UpdateException("更新默认地址异常");



    }

    @Override
    public Address getbyaid(Integer aid,Integer uid) {
        Address address = addressmapper.findbyaid(aid);
        if (address==null)
            throw   new AddressNotFoundException("地址不存在");
        if (!address.getUid().equals(uid))
            throw  new AccessDeniedException("地址非法访问");
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return  address;
    }


}
