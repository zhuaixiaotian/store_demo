package com.cy.store.controller;


import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.jsonresult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/address")
@RestController
public class addresscontroller extends  basecontroller{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("/insert")
    public jsonresult<Void>  addAddress (Address address, HttpSession session)
    {
        System.out.println(address);
        Integer uid=getuidsession(session);
        String username=getnamesession(session);
        addressService.addnewaddress(uid,username,address);
        return  new jsonresult<>(ok,"新增地址成功");

    }

    @GetMapping
    public  jsonresult<List<Address>>  getbyuid(HttpSession session)

    {
        Integer uid=getuidsession(session);
        List<Address> getbyuid = addressService.getbyuid(uid);
        return  new jsonresult<>(getbyuid,ok);

    }

    @GetMapping("/{aid}")  //修改默认地址 如果传入address参数 用putmapping
    public  jsonresult<Void> setdefault(@PathVariable Integer aid,HttpSession session)
    {
        String username=getnamesession(session);
        Integer uid=getuidsession(session);
        addressService.setDefault(aid,uid,username);
        return  new jsonresult<>(ok,"设置默认地址成功");


    }
    @DeleteMapping("/{aid}")
    public  jsonresult<Void> deletebyaid(@PathVariable Integer aid,HttpSession session)
    {
        String username=getnamesession(session);
        Integer uid=getuidsession(session);
        addressService.deletebyaid(aid, uid, username);
        return  new jsonresult<>(ok,"删除地址成功");

    }





}
